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

@WebServlet("/salario")
public class SalarioEmpleadoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> errores = new ArrayList<>();
        String dni =  request.getParameter("dni");
        boolean isValido = comprobarDni(dni);

        if (isValido) {
            try {
                EmpleadoModelo empleado = new EmpleadoDAO().getEmpleadoByDni(dni);

                if (empleado != null) {
                    Double sueldo = new NominaDAO().recogerSueldoEmpleado(empleado);

                    if (sueldo != null) {
                        if (sueldo.equals(Nomina.sueldo(empleado))) {
                            request.setAttribute("sueldo", sueldo);
                        } else {
                            request.setAttribute("aviso","El sueldo recogido no está actualizado");
                        }
                    } else {
                        errores.add("El sueldo recogido es nulo");
                    }
                } else {
                    errores.add("No existe nómina para ese empleado");
                }

            } catch (FormatoDniException | EmpleadoDataException | FormatoSexoException e) {
                System.out.println("Error al obtener el empleado: " + e.getMessage());
            }
        } else {
            errores.add("Debe introducir un dni válido");
        }

        request.setAttribute("errores", errores);
        request.getRequestDispatcher("views/sueldoEmpleadoDni.jsp").forward(request, response);
    }

    private boolean comprobarDni(String dni) {
        return dni != null && !dni.trim().isBlank() && dni.matches("^[0-9]{7,8}[A-Z]$");
    }
}
