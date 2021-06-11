package Logica.exceptions;

public class PersistenciaException extends Exception {
	
	public PersistenciaException()
	{
		super("Error al respaldar");
	}
	public PersistenciaException(String msj)
	{
		super(msj);
	}

}
