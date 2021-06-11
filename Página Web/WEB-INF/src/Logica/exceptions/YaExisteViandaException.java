package Logica.exceptions;

public class YaExisteViandaException extends Exception {
	
	public YaExisteViandaException()
	{
		super("Error: La vianda ya existe.");
	}
	public YaExisteViandaException(String msj)
	{
		super(msj);
	}	
}

