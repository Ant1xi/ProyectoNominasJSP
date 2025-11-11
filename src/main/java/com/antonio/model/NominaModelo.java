package com.antonio.model;

import com.antonio.excepciones.FormatoDniException;
import com.antonio.excepciones.NominaDataException;

/**
 * Aunque ya tenemos una clase Nomina creada desde la parte 1, yo he creado
 * esta clase para representar el modelo de la tabla 'Nominas' de la BBDD
 */

public class NominaModelo {

    private Integer idNomina;
    private String dni;
    private Double sueldo;

    public NominaModelo(Integer idNomina, String dni, Double sueldo) throws NominaDataException, FormatoDniException {

        if (idNomina == null) {
            throw new NominaDataException("La id de la Nómina no puede ser nula");
        }

        comprobarDni(dni);
        comprobarSueldo(sueldo);

        this.idNomina = idNomina;
        this.dni = dni;
        this.sueldo = sueldo;
    }

    public NominaModelo(String dni, Double sueldo) throws FormatoDniException, NominaDataException {

        comprobarDni(dni);
        comprobarSueldo(sueldo);

        this.dni = dni;
        this.sueldo = sueldo;
    }

    public Integer getIdNomina() {
        return idNomina;
    }

    public String getDni() {
        return dni;
    }

    public Double getSueldo() {
        return sueldo;
    }

    @Override
    public String toString() {
        return "NominaModelo [idNomina=" + idNomina + ", dni=" + dni + ", sueldo=" + sueldo + "]";
    }

    public void comprobarDni(String dni) throws FormatoDniException {

        if (dni == null || dni.isBlank()) {
            throw new FormatoDniException("El DNI es obligatorio");
        }

        if (!dni.matches("^\\d{8}[A-Z]$")) {
            throw new FormatoDniException(
                    "Formato del DNI incorrecto: Deben ser 8 números seguidos de una letra en mayúscula");
        }

    }

    public void comprobarSueldo(Double sueldo) throws NominaDataException {

        if (sueldo == null) {
            throw new NominaDataException("El sueldo no puede ser un valor nulo.");
        }

        if (sueldo < 0) {
            throw new NominaDataException("El sueldo no puede ser un valor negativo.");
        }
    }
}
