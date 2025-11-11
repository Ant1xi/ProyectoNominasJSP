package com.antonio.excepciones;

/**
 * Excepción que se lanza cuando el sexo no cumple el formato esperado (solo se
 * admiten 'M' o 'F').
 */
public class FormatoSexoException extends AppException {

    private static final long serialVersionUID = 6866417473569278315L;

    /**
     * Crea la excepción sin mensaje.
     */
    public FormatoSexoException() {
        super();
    }

    /**
     * Crea la excepción con un mensaje descriptivo.
     *
     * @param message descripción del error
     */
    public FormatoSexoException(String message) {
        super(message);
    }
}
