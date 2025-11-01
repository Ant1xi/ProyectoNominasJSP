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

@WebServlet("/empleados/*")
public class EmpleadoController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path == null || "/".equals(path)) {
            listar(request, response);
        } else if ("/buscar".equals(path)) {
            //mostrarFormularioBusqueda(request, response);
        } else if ("/editar".equals(path)) {
            //mostrarFormularioEdicion(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    public void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<EmpleadoModelo> listaEmpleados = new EmpleadoDAO().getAll();
            request.setAttribute("empleadosRecogidos", listaEmpleados);
            request.getRequestDispatcher("/views/empleados/listadoEmpleados.jsp").forward(request, response);

        } catch (FormatoDniException | EmpleadoDataException | FormatoSexoException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/views/error/error.jsp").forward(request, response);
            System.out.println("Error: " + e.getMessage());
        }
    }
}
