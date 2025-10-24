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


@WebServlet("/empleados")
public class EmpleadoServlet extends HttpServlet {

    /**
     *
     * @param request   an {@link HttpServletRequest} object that
     *                  contains the request the client has made
     *                  of the servlet
     *
     * @param response  an {@link HttpServletResponse} object that
     *                  contains the response the servlet sends
     *                  to the client
     *
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<EmpleadoModelo> listaEmpleados = new EmpleadoDAO().getAll();
            request.setAttribute("empleadosRecogidos", listaEmpleados);

            request.getRequestDispatcher("/views/listadoEmpleados.jsp").forward(request, response);

        } catch (FormatoDniException | EmpleadoDataException | FormatoSexoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

