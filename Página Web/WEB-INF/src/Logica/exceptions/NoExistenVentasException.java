package Logica.exceptions;

public class NoExistenVentasException  extends Exception{

	public NoExistenVentasException()
	{
		super("Error: No existen ventas en el sistema.");
	}
	public NoExistenVentasException(String msj)
	{
		super(msj);
	}
}
