package com.antonio.controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/modificar")
public class ModificarDatosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

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

    /**
     *
     * @param campoRecogido
     * @return
     */
    private String trimOrNull(String campoRecogido) {
        if  (campoRecogido == null || campoRecogido.isBlank()) {
            return null;
        } else {
            return campoRecogido.trim();
        }
    }
}
