package com.antonio.excepciones;

/**
 * Excepción que se lanza cuando el DNI no cumple el formato correcto (8 números
 * y una letra mayúscula).
 */
public class FormatoDniException extends AppException {

    private static final long serialVersionUID = -5922904352365931716L;

    /**
     * Crea la excepción sin mensaje.
     */
    public FormatoDniException() {
        super();
    }

    /**
     * Crea la excepción con un mensaje descriptivo.
     *
     * @param message descripción del error
     */
    public FormatoDniException(String message) {
        super(message);
    }
}
