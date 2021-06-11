package Logica.exceptions;

public class NoExisteLaViandaException extends Exception {

	public NoExisteLaViandaException()
	{
		super("Error: La vianda ingresada no existe.");
	}
	public NoExisteLaViandaException(String msj)
	{
		super(msj);
	}
}
