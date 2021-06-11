package Logica.exceptions;

public class PersistenciaException extends Exception {
	
	public PersistenciaException()
	{
		super("error respaldar");
	}
	public PersistenciaException(String msj)
	{
		super(msj);
	}

}
