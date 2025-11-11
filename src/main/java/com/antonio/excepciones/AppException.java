package com.antonio.excepciones;

/**
 * Excepción base de la aplicación.
 * <p>
 * Sirve como clase padre para el resto de excepciones personalizadas.
 */
public class AppException extends Exception {

    private static final long serialVersionUID = 6304498872162097291L;

    /**
     * Crea una excepción sin mensaje.
     */
    public AppException() {
        super();
    }

    /**
     * Crea una excepción con un mensaje.
     *
     * @param message descripción del error
     */
    public AppException(String message) {
        super(message);
    }
}
