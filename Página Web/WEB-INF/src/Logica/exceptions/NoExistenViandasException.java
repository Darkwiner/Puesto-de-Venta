package Logica.exceptions;

public class NoExistenViandasException extends Exception{
	public NoExistenViandasException()
	{
		super("Error: No existen viandas en el sistema.");
	}
	public NoExistenViandasException(String msj)
	{
		super(msj);
	}
}

