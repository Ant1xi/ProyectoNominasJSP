package com.antonio.controller;

import com.antonio.dao.EmpleadoDAO;
import com.antonio.dao.NominaDAO;
import com.antonio.excepciones.EmpleadoDataException;
import com.antonio.excepciones.FormatoDniException;
import com.antonio.excepciones.FormatoSexoException;
import com.antonio.laboral.Nomina;
import com.antonio.model.EmpleadoModelo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/empleados/*")
public class EmpleadoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String path = req.getPathInfo(); // puede ser null
        if (path == null || "/".equals(path)) {
            listar(req, resp);
        } else if ("/consultar".equals(path)) {
            req.getRequestDispatcher("/WEB-INF/views/empleados/sueldoEmpleadoDni.jsp").forward(req, resp);
        } else if ("/buscar".equals(path)) {
            req.getRequestDispatcher("/WEB-INF/views/empleados/modificarEmpleados.jsp").forward(req, resp);
        } else if ("/editar".equals(path)) {
            mostrarFormularioEdicion(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String path = req.getPathInfo();
        if ("/consultar".equals(path)) {
            procesarConsultaSueldo(req, resp);
        } else if ("/buscarByDato".equals(path)) {
            buscar(req, resp);
        } else if ("/guardar".equals(path)) {
            // aquí iría la lógica de persistencia de cambios del empleado
            resp.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
        } else {
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
    }

    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<EmpleadoModelo> lista = new EmpleadoDAO().getAll();
            request.setAttribute("empleadosRecogidos", lista);
            request.getRequestDispatcher("/WEB-INF/views/empleados/listadoEmpleados.jsp")
                    .forward(request, response);
        } catch (FormatoDniException | EmpleadoDataException | FormatoSexoException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error/error.jsp").forward(request, response);
        }
    }

    private void procesarConsultaSueldo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> errores = new ArrayList<>();
        String dni = request.getParameter("dni");

        if (!comprobarDni(dni)) {
            errores.add("Debe introducir un DNI válido (7-8 dígitos y letra mayúscula).");
        } else {
            try {
                EmpleadoModelo emp = new EmpleadoDAO().getEmpleadoByDni(dni.toUpperCase());
                if (emp == null) {
                    errores.add("No existe empleado con ese DNI.");
                } else {
                    Double sueldo = new NominaDAO().recogerSueldoEmpleado(emp);
                    if (sueldo == null) {
                        errores.add("No hay nómina registrada para ese empleado.");
                    } else {
                        request.setAttribute("empleado", emp);
                        request.setAttribute("sueldo", sueldo);
                        double calculado = Nomina.sueldo(emp);
                        if (!sueldo.equals(calculado)) {
                            request.setAttribute("aviso", "¡Advertencia! El sueldo guardado no coincide con el calculado.");
                        }
                    }
                }
            } catch (FormatoDniException | EmpleadoDataException | FormatoSexoException e) {
                errores.add("Error al obtener datos: " + e.getMessage());
            }
        }

        request.setAttribute("errores", errores);
        request.getRequestDispatcher("/WEB-INF/views/empleados/sueldoEmpleadoDni.jsp").forward(request, response);
    }

    private void buscar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String> errores = new ArrayList<>();

        String dni = recortarONulo(request.getParameter("dni"));
        String nombre = recortarONulo(request.getParameter("nombre"));
        String sexo = recortarONulo(request.getParameter("sexo"));
        String categoria = recortarONulo(request.getParameter("categoria"));
        String anyos = recortarONulo(request.getParameter("anyos"));

        if (dni != null) dni = dni.toUpperCase();
        if (sexo != null) sexo = sexo.toUpperCase();
        if ("null".equalsIgnoreCase(sexo)) sexo = null;
        if ("null".equalsIgnoreCase(categoria)) categoria = null;

        Integer cat = aEnteroONulo(categoria);
        Integer yrs = aEnteroONulo(anyos);

        StringBuilder sql = new StringBuilder("SELECT * FROM Empleados");
        boolean hayCondicion = false;

        if (dni != null) {
            sql.append(hayCondicion ? " AND " : " WHERE ")
                    .append("dni = '").append(escapar(dni)).append("'");
            hayCondicion = true;
        }
        if (nombre != null) {
            sql.append(hayCondicion ? " AND " : " WHERE ")
                    .append("UPPER(nombre) LIKE '%").append(escapar(nombre.toUpperCase())).append("%'");
            hayCondicion = true;
        }
        if (sexo != null) {
            sql.append(hayCondicion ? " AND " : " WHERE ")
                    .append("sexo = '").append(escapar(sexo)).append("'");
            hayCondicion = true;
        }
        if (cat != null) {
            sql.append(hayCondicion ? " AND " : " WHERE ")
                    .append("categoria = ").append(cat);
            hayCondicion = true;
        }
        if (yrs != null) {
            sql.append(hayCondicion ? " AND " : " WHERE ")
                    .append("anyos = ").append(yrs);
            hayCondicion = true;
        }

        try {
            List<EmpleadoModelo> lista = new EmpleadoDAO().buscarPorCampos(sql.toString());
            request.setAttribute("empleadosRecogidos", lista);
        } catch (FormatoDniException | EmpleadoDataException | FormatoSexoException e) {
            errores.add("Error al obtener datos: " + e.getMessage());
        }

        // Mantén los filtros para repintarlos en la vista
        request.setAttribute("dni", dni);
        request.setAttribute("nombre", nombre);
        request.setAttribute("sexo", sexo);
        request.setAttribute("categoria", cat);
        request.setAttribute("anyos", yrs);

        // Asegura forward SIEMPRE (con o sin errores)
        request.setAttribute("errores", errores);
        request.getRequestDispatcher("/WEB-INF/views/empleados/modificarEmpleados.jsp").forward(request, response);
    }

    private void mostrarFormularioEdicion(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String dni = req.getParameter("dni");
        if (dni == null || dni.isBlank()) {
            resp.sendRedirect(req.getContextPath() + "/empleados/buscar");
            return;
        }

        try {
            EmpleadoModelo empleado = new EmpleadoDAO().getEmpleadoByDni(dni.toUpperCase());
            if (empleado == null) {
                req.setAttribute("errores", java.util.List.of("No existe empleado con DNI " + dni));
            } else {
                req.setAttribute("empleadoSeleccionado", empleado);
            }
        } catch (FormatoDniException | EmpleadoDataException | FormatoSexoException ex) {
            req.setAttribute("errores", java.util.List.of(ex.getMessage()));
        }

        req.getRequestDispatcher("/WEB-INF/views/empleados/modificarEmpleados.jsp").forward(req, resp);
    }

    /*---------------------Métodos de ayuda---------------------*/

    private boolean comprobarDni(String dni) {
        return dni != null && !dni.isBlank() && dni.matches("^[0-9]{7,8}[A-Z]$");
    }

    private String recortarONulo(String v) {
        if (v == null) return null;
        v = v.trim();
        return v.isEmpty() ? null : v;
    }

    private Integer aEnteroONulo(String v) {
        try {
            return (v == null) ? null : Integer.valueOf(v);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private String escapar(String v) {
        return v.replace("'", "''");
    }
}
