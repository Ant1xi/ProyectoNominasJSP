package com.antonio.conexion;

import java.sql.*;

public final class DataBaseConnection {
    private static final String URL  = "jdbc:mariadb://localhost:3306/gestor_nominas";
    private static final String USER = "nominas_user";
    private static final String PASSWORD = "1234";

    static {
        System.out.println("[DB] Cargando driver MariaDB...");
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("[DB] Driver MariaDB cargado OK");
        } catch (ClassNotFoundException e) {
            System.out.println("[DB] **NO ENCONTRADO** org.mariadb.jdbc.Driver");
            e.printStackTrace();
        }

        // Lista los drivers registrados
        System.out.println("[DB] Drivers registrados:");
        java.util.Enumeration<Driver> drs = DriverManager.getDrivers();
        while (drs.hasMoreElements()) {
            Driver d = drs.nextElement();
            System.out.println("   - " + d.getClass().getName());
        }
    }

    private DataBaseConnection() {}

    public static Connection getConnection() throws SQLException {
        System.out.println("[DB] Abriendo conexión a " + URL);
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

