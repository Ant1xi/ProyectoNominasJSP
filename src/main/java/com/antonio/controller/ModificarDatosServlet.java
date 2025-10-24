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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/modificar")
public class ModificarDatosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dni = trimOrNull(request.getParameter("dni"));
        String nombre = trimOrNull(request.getParameter("nombre"));
        String sexo = trimOrNull(request.getParameter("sexo"));
        String categoria = trimOrNull(request.getParameter("categoria"));
        String anyos = trimOrNull(request.getParameter("anyos"));

        /*String consulta = "SELECT * FROM Empleados WHERE";

        try {
            List<EmpleadoModelo> listaEmpleados = new EmpleadoDAO().buscarPorCampos(consulta);
        } catch (FormatoDniException | EmpleadoDataException | FormatoSexoException e) {
            throw new RuntimeException(e);
        }*/
    }

    private String trimOrNull(String campoRecogido) {
        if  (campoRecogido == null || campoRecogido.isBlank()) {
            return null;
        } else {
            return campoRecogido.trim();
        }
    }
}
