package Logica.exceptions;

public class NoHayDatosQueGuardarException extends Exception{
	
	public NoHayDatosQueGuardarException()
	{
		super("Error: No hay datos que guardar.");
	}
	public NoHayDatosQueGuardarException(String msj)
	{
		super(msj);
	}
}
