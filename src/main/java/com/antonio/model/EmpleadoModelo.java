package com.antonio.model;


import com.antonio.excepciones.EmpleadoDataException;
import com.antonio.excepciones.FormatoDniException;
import com.antonio.excepciones.FormatoSexoException;

/**
 * Aunque ya tenemos una clase Empleado creada desde la parte 1, yo he creado
 * esta clase para representar el modelo de la tabla 'Empleados' de la BBDD
 */

public class EmpleadoModelo {

    private Integer id;
    private String dni;
    private String nombre;
    private Character sexo;
    private Integer categoria;
    private Integer anyos;

    public EmpleadoModelo(Integer id, String dni, String nombre, Character sexo, Integer categoria, Integer anyos)
            throws EmpleadoDataException, FormatoDniException, FormatoSexoException {

        if (id == null) {
            throw new EmpleadoDataException("La id no puede ser un valor nulo");
        }

        comprobarDni(dni);
        comprobarNombre(nombre);
        comprobarSexo(sexo);
        comprobarCategoria(categoria);
        comprobarAnyos(anyos);

        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.sexo = sexo;
        this.categoria = categoria;
        this.anyos = anyos;
    }

    public EmpleadoModelo(String dni, String nombre, Character sexo, Integer categoria, Integer anyos)
            throws FormatoDniException, EmpleadoDataException, FormatoSexoException {

        comprobarDni(dni);
        comprobarNombre(nombre);
        comprobarSexo(sexo);
        comprobarCategoria(categoria);
        comprobarAnyos(anyos);

        this.dni = dni;
        this.nombre = nombre;
        this.sexo = sexo;
        this.categoria = categoria;
        this.anyos = anyos;
    }

    public Integer getId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public Character getSexo() {
        return sexo;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public Integer getAnyos() {
        return anyos;
    }

    @Override
    public String toString() {
        return nombre + " | " + dni + " | " + sexo + " | Categoría: " + categoria + " | Años: " + anyos;
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

    public void comprobarNombre(String nombre) throws EmpleadoDataException {

        if (nombre == null || nombre.isBlank()) {
            throw new EmpleadoDataException("El nombre es un campo obligatorio.");
        }

        if (!nombre.matches("^[A-ZÁÉÍÓÚÑ][a-zñáéíóú]+(?: [A-ZÁÉÍÓÚÑ][a-zñáéíóú]+)*$")) {
            throw new EmpleadoDataException(
                    "El nombre debe comenzar por mayúscula y seguir con minúsculas (se admiten nombres compuestos).");
        }
    }

    public void comprobarSexo(Character sexo) throws FormatoSexoException, EmpleadoDataException {

        if (sexo == null || Character.isWhitespace(sexo)) {
            throw new EmpleadoDataException("El seco es un campo obligatorio.");
        }

        if (sexo != 'M' && sexo != 'F') {
            throw new FormatoSexoException("El sexo debe ser 'M' (hombre) o 'F' (mujer).");
        }
    }

    public void comprobarCategoria(Integer categoria) throws EmpleadoDataException {

        if (categoria == null) {
            throw new EmpleadoDataException("La categoría no puede ser un valor nulo");
        }

        if (categoria < 1 || categoria > 10) {
            throw new EmpleadoDataException("La categoría debe estar entre 1 y 10");
        }
    }

    public void comprobarAnyos(Integer anyos) throws EmpleadoDataException {
        if (anyos == null) {
            throw new EmpleadoDataException("Los años no pueden ser un valor nulo");
        }
        if (anyos < 0 || anyos > 99) {
            throw new EmpleadoDataException("Los años no pueden ser negativos ni excesivos");
        }
    }

}
