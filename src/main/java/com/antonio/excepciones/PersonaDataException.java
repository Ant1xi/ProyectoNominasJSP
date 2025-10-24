package com.antonio.excepciones;

/**
 * Excepción que se lanza cuando los datos de una persona (nombre, dni o sexo)
 * no son válidos.
 */
public class PersonaDataException extends AppException {

	private static final long serialVersionUID = 8663765672262392139L;

	/** Crea la excepción sin mensaje. */
	public PersonaDataException() {
		super();
	}

	/**
	 * Crea la excepción con un mensaje descriptivo.
	 *
	 * @param message descripción del error
	 */
	public PersonaDataException(String message) {
		super(message);
	}
}
