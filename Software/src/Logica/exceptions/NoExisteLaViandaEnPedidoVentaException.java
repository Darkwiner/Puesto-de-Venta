package Logica.exceptions;

public class NoExisteLaViandaEnPedidoVentaException extends Exception{

	public NoExisteLaViandaEnPedidoVentaException()
	{
		super("Error: La vianda ingresada no existe en los pedidos de la venta.");
	}
	public NoExisteLaViandaEnPedidoVentaException(String msj)
	{
		super(msj);
	}
}
