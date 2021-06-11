package Logica.exceptions;

public class NoExisteLaVentaException extends Exception {

	public NoExisteLaVentaException()
	{
		super("Error: La venta ingresada no existe.");
	}
	public NoExisteLaVentaException(String msj)
	{
		super(msj);
	}
}
