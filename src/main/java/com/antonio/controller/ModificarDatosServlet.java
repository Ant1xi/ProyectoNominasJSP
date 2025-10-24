package com.antonio.controller;

import com.antonio.dao.EmpleadoDAO;
import com.antonio.excepciones.EmpleadoDataException;
import com.antonio.excepciones.FormatoDniException;
import com.antonio.excepciones.FormatoSexoException;
import com.antonio.model.EmpleadoModelo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/modificar")
public class ModificarDatosServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String dni       = recortarONulo(request.getParameter("dni"));
        String nombre    = recortarONulo(request.getParameter("nombre"));
        String sexo      = recortarONulo(request.getParameter("sexo"));
        String categoria = recortarONulo(request.getParameter("categoria"));
        String anyos     = recortarONulo(request.getParameter("anyos"));

        if (dni != null)  dni = dni.toUpperCase();
        if (sexo != null) sexo = sexo.toUpperCase();
        if ("null".equalsIgnoreCase(sexo))      sexo = null;
        if ("null".equalsIgnoreCase(categoria)) categoria = null;

        Integer cat = aEnteroONulo(categoria);
        Integer yrs = aEnteroONulo(anyos);


        StringBuilder sql = new StringBuilder("SELECT * FROM Empleados");
        boolean hayCondicion = false;

        if (dni != null) {
            sql.append(hayCondicion ? " AND " : " WHERE ");
            sql.append("dni = '").append(escapar(dni)).append("'");
            hayCondicion = true;
        }
        if (nombre != null) {
            sql.append(hayCondicion ? " AND " : " WHERE ");
            sql.append("UPPER(nombre) LIKE '%").append(escapar(nombre.toUpperCase())).append("%'");
            hayCondicion = true;
        }
        if (sexo != null) {
            sql.append(hayCondicion ? " AND " : " WHERE ");
            sql.append("sexo = '").append(escapar(sexo)).append("'");
            hayCondicion = true;
        }
        if (cat != null) {
            sql.append(hayCondicion ? " AND " : " WHERE ");
            sql.append("categoria = ").append(cat);
            hayCondicion = true;
        }
        if (yrs != null) {
            sql.append(hayCondicion ? " AND " : " WHERE ");
            sql.append("anyos = ").append(yrs);
            hayCondicion = true;
        }

        try {
            List<EmpleadoModelo> lista = new EmpleadoDAO().buscarPorCampos(sql.toString());

            request.setAttribute("empleadosRecogidos", lista);
            request.setAttribute("dni", dni);
            request.setAttribute("nombre", nombre);
            request.setAttribute("sexo", sexo);
            request.setAttribute("categoria", cat);
            request.setAttribute("anyos", yrs);

            request.getRequestDispatcher("/views/modificarEmpleados.jsp").forward(request, response);
        } catch (FormatoDniException | EmpleadoDataException | FormatoSexoException e) {
            throw new ServletException(e);
        }
    }


    private String recortarONulo(String v) {
        if (v == null) return null;
        v = v.trim();
        return v.isEmpty() ? null : v;
    }

    private Integer aEnteroONulo(String v) {
        try { return (v == null) ? null : Integer.valueOf(v); }
        catch (NumberFormatException e) { return null; }
    }

    private String escapar(String v) {
        return v.replace("'", "''");
    }
}


