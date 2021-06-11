package Logica.exceptions;

public class FechaIncorrectaException extends Exception{
	
	public FechaIncorrectaException()
	{
		super("Error: La fecha ingresada es incorrecta.");
	}
	public FechaIncorrectaException(String msj)
	{
		super(msj);
	}
}
