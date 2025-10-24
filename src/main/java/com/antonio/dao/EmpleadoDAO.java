package com.antonio.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.antonio.conexion.DataBaseConnection;
import com.antonio.excepciones.EmpleadoDataException;
import com.antonio.excepciones.FormatoDniException;
import com.antonio.excepciones.FormatoSexoException;
import com.antonio.laboral.Nomina;
import com.antonio.model.EmpleadoModelo;


public class EmpleadoDAO {

    public List<EmpleadoModelo> getAll() throws FormatoDniException, EmpleadoDataException, FormatoSexoException {
        List<EmpleadoModelo> listaEmpleados = new ArrayList<EmpleadoModelo>();
        String sql = "SELECT * FROM Empleados";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                Character sexo = rs.getString("sexo").trim().charAt(0);
                Integer categoria = rs.getInt("categoria");
                Integer anyos = rs.getInt("anyos");

                listaEmpleados.add(new EmpleadoModelo(dni, nombre, sexo, categoria, anyos));
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener empleados: " + e.getMessage());
            e.printStackTrace();
        }

        return listaEmpleados;
    }

    public EmpleadoModelo getEmpleadoByDni(String dniEntrada)
            throws FormatoDniException, EmpleadoDataException, FormatoSexoException {
        String sql = "SELECT * FROM Empleados WHERE dni = ?";
        EmpleadoModelo e = null;
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, dniEntrada);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                Character sexo = rs.getString("sexo").trim().charAt(0);
                Integer categoria = rs.getInt("categoria");
                Integer anyos = rs.getInt("anyos");

                e = new EmpleadoModelo(dni, nombre, sexo, categoria, anyos);
            }

        } catch (SQLException ex) {
            System.out.println("Error al obtener empleados: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }

        return e;

    }

    public int actualizarDatosEmpleado(EmpleadoModelo e, String dni) {
        String sql = "UPDATE Empleados SET nombre = ?, sexo = ?, categoria = ?, anyos = ? WHERE dni = ?";
        int filas = 0;

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, e.getNombre());
            pstmt.setString(2, String.valueOf(e.getSexo()));
            pstmt.setInt(3, e.getCategoria());
            pstmt.setInt(4, e.getAnyos());
            pstmt.setString(5, dni);

            filas = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error al actualizar empleado: " + ex.getMessage());
            ex.printStackTrace();
        }

        return filas;
    }

    public boolean actualizarCategoria(String dni, Integer categoria) {
        String sql = "UPDATE Empleados SET categoria = ? WHERE dni = ?";
        boolean isActualizado = false;

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, categoria);
            pstmt.setString(2, dni);

            int filas = pstmt.executeUpdate();

            if (filas > 0) {
                isActualizado = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar categoría: " + e.getMessage());
            e.printStackTrace();
        }

        return isActualizado;
    }

    public boolean actualizarAnyos(String dni, Integer anyos) {
        String sql = "UPDATE Empleados SET anyos = ? WHERE dni = ?";
        boolean isActualizado = false;

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, anyos);
            pstmt.setString(2, dni);

            int filas = pstmt.executeUpdate();

            if (filas > 0) {
                isActualizado = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar anyos: " + e.getMessage());
            e.printStackTrace();
        }

        return isActualizado;
    }

    public boolean incrementarAnyos(String dni) {
        String sql = "UPDATE Empleados SET anyos = anyos + 1 WHERE dni = ?";
        boolean isActualizado = false;

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, dni);

            int filas = pstmt.executeUpdate();

            if (filas > 0) {
                isActualizado = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al incrementar anyos: " + e.getMessage());
            e.printStackTrace();
        }

        return isActualizado;
    }

    public int altaEmpleado(EmpleadoModelo e) {
        String sql = "INSERT INTO Empleados(dni, nombre, sexo, categoria, anyos) VALUES (?, ?, ?, ?, ?);";
        int filas = 0;

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, e.getDni());
            pstmt.setString(2, e.getNombre());
            pstmt.setString(3, String.valueOf(e.getSexo()));
            pstmt.setInt(4, e.getCategoria());
            pstmt.setInt(5, e.getAnyos());

            filas = pstmt.executeUpdate();

            boolean isNominaCreada = new NominaDAO().crearNominaEmpleado(e.getDni(), Nomina.sueldo(e));

            if (isNominaCreada == true) {
                System.out.println("Empleado creado correctamente con su nómina añadida");
            }

        } catch (SQLException ex) {
            System.out.println("Error al insertar empleado: " + ex.getMessage());
            ex.printStackTrace();
        }

        return filas;
    }

    public int altaEmpleado(List<EmpleadoModelo> listaEmpleados) {
        int empleadosCreados = 0;

        for (EmpleadoModelo empleadoModelo : listaEmpleados) {
            int filas = altaEmpleado(empleadoModelo);
            empleadosCreados += filas;
        }

        return empleadosCreados;
    }

    public int actualizarEmpleadoYNomina(EmpleadoModelo e) {
        int actualizados = 0;


        return actualizados;
    }

    public List<EmpleadoModelo> buscarPorCampos(String consulta) throws FormatoDniException, EmpleadoDataException, FormatoSexoException {
        List<EmpleadoModelo> listaEmpleados = new ArrayList<>();

        try (Connection conn = DataBaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(consulta);) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                Character sexo = rs.getString("sexo").trim().charAt(0);
                Integer categoria = rs.getInt("categoria");
                Integer anyos = rs.getInt("anyos");

                listaEmpleados.add(new EmpleadoModelo(dni, nombre, sexo, categoria, anyos));
            }

        } catch (SQLException e) {
            System.out.println("Error al recoger empleados: " + e.getMessage());
            e.printStackTrace();
        }

        return listaEmpleados;
    }

}
