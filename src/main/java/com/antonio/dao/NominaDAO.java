package com.antonio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.antonio.conexion.DataBaseConnection;
import com.antonio.laboral.Nomina;
import com.antonio.model.EmpleadoModelo;

public class NominaDAO {

    public boolean crearNominaEmpleado(String dni, Double sueldo) {

        String sql = "INSERT INTO Nominas (dni, sueldo) VALUES (?, ?)";
        boolean isCreada = false;

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, dni);
            pstmt.setDouble(2, sueldo);

            int filas = pstmt.executeUpdate();

            if (filas > 0) {
                isCreada = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al crear nómina: " + e.getMessage());
            e.printStackTrace();
        }

        return isCreada;
    }

    public int actualizarSueldoEmpleado(EmpleadoModelo empleado) {
        String sql = "UPDATE Nominas SET sueldo = ? WHERE dni = ?";
        Double sueldo = 0.0;
        int filas = 0;

        if (empleado != null) {
            sueldo = Nomina.sueldo(empleado);
        }

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, sueldo);
            pstmt.setString(2, empleado.getDni());

            filas = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al actualizar sueldo: " + e.getMessage());
            e.printStackTrace();
        }

        return filas;
    }

    public Double recogerSueldoEmpleado(EmpleadoModelo empleado) {
        String sql = "SELECT sueldo FROM Nominas WHERE dni = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, empleado.getDni());

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    double val = rs.getDouble(1);

                    return rs.wasNull() ? null : val;

                } else {
                    return null; // esto significa que no existe nómina para ese DNI
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al recoger sueldo: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }



}
