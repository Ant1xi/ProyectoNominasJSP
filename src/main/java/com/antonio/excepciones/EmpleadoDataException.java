package com.antonio.excepciones;

/**
 * Excepción que se lanza cuando los datos de un empleado (categoría o años
 * trabajados) no son válidos.
 */
public class EmpleadoDataException extends AppException {

	private static final long serialVersionUID = 6082015341492874936L;

	/** Crea la excepción sin mensaje. */
	public EmpleadoDataException() {
		super();
	}

	/**
	 * Crea la excepción con un mensaje descriptivo.
	 *
	 * @param message descripción del error
	 */
	public EmpleadoDataException(String message) {
		super(message);
	}
}
