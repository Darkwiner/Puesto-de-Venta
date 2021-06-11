package Logica.exceptions;

public class CantidadAEliminarINcorrectaException extends Exception {

	public CantidadAEliminarINcorrectaException()
	{
		super("Error: La cantidad de viandas del perdido es menor a la que se quiere eliminar.");
	}
	public CantidadAEliminarINcorrectaException(String msj)
	{
		super(msj);
	}
}
