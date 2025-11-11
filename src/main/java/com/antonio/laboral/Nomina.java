package com.antonio.laboral;


import com.antonio.model.EmpleadoModelo;

public class Nomina {

    private static final int[] SUELDO_BASE = {50000, 70000, 90000, 110000, 130000, 150000, 170000, 190000, 210000,
            230000};

    public Nomina() {

    }

    public static Double sueldo(EmpleadoModelo e) {
        if (e == null)
            return 0.0;

        Integer sueldoBase = calcularSueldoBase(e);
        int anyos = (e.getAnyos() == null ? 0 : e.getAnyos());

        return sueldoBase + 5000.0 * anyos;
    }

    public static int calcularSueldoBase(EmpleadoModelo e) {


        int sueldoBase = 0;
        Integer categoria = e.getCategoria();

        if (categoria != null) {
            sueldoBase = SUELDO_BASE[categoria - 1];
        }

        return sueldoBase;
    }
}
