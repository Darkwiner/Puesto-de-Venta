package Logica.exceptions;

public class VentaFinalizadaException extends Exception {

	public VentaFinalizadaException()
	{
		super("Error: La venta ingresada ya esta finalizada.");
	}
	public VentaFinalizadaException(String msj)
	{
		super(msj);
	}
}
