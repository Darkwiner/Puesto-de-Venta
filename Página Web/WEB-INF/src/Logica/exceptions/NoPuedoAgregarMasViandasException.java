package Logica.exceptions;

public class NoPuedoAgregarMasViandasException  extends Exception{

	public NoPuedoAgregarMasViandasException()
	{
		super("Error: No se pueden agregar mas viandas.");
	}
	public NoPuedoAgregarMasViandasException(String msj)
	{
		super(msj);
	}
}
