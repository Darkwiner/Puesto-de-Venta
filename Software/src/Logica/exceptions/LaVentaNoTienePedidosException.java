package Logica.exceptions;

public class LaVentaNoTienePedidosException extends Exception {

	public LaVentaNoTienePedidosException()
	{
		super("Error: La venta ingresada no tiene viandas.");
	}
	public LaVentaNoTienePedidosException(String msj)
	{
		super(msj);
	}
}
