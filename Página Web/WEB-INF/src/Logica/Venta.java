package Logica;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.io.Serializable;
import Logica.valueobjects.*;

//VENTA DEBE SER SERIALIZABLE, PEDIDOS Y PEDIDO TAMBIÉN
public class Venta implements Serializable {

	private int numero;
	private LocalDateTime fechaYHora;
	private String direccion;
	private Boolean finalizado;
	private Pedidos pedidos;
	
	public Venta ()
	{
		
	}
	
	public Venta (int num, LocalDateTime fyh, String dire, boolean fin)
	{
		this.numero = num;
		this.fechaYHora = fyh;
		this.direccion = dire;
		this.finalizado = fin;
		this.pedidos = new Pedidos();  
	}
	
	public int getNumero () 
	{
		return this.numero;
	}
	
	public LocalDateTime getFechaYHora () 
	{
		return this.fechaYHora;
	}
	
	public String getDireccion () 
	{
		return this.direccion;
	}   
	
	public boolean getFinalizado ()
	{
		return this.finalizado;
	}
	
	public void setFechaYHora (LocalDateTime fyh)
	{
		this.fechaYHora = fyh;
	}
	
	public void setDirección (String dire)
	{
		this.direccion = dire;
	}
	
	public void setFinalizado (boolean fin)
	{
		this.finalizado = fin;
	}
	
	/*
	public boolean validoFecha (LocalDateTime fyh)
	{
		
	}*/
	
	//Valido si existe una vianda con el codigo alfanumérico ingresado y devuelvo true si existe.
	public boolean existeVianda (String cod)
	{
		return this.pedidos.member(cod);
	}
	
	//Devuelvo el monto total de las viandas seleccionadas en pedidos
	public float obtengoMontoTotal ()
	{
		return this.pedidos.importeTotal();
	}
	
	//Valido si la venta es vacía llamando a metodo de Pedidos
	public boolean esVacio ()
	{
		return this.pedidos.esVacio();
	}
	
	//Inserto pedido a pedidos de la venta // llamo a insertarPedido de Pedidos
	public void insertarPedido (Pedido elPedido)
	{
		this.pedidos.insertarPedido(elPedido);
	}
	
	//Elimino la cantidad de viandas pasada por parametro del pedido en pedidos de la venta
	public void eliminarPedido (String cod, int cant)
	{
		this.pedidos.modificoPedidos(cod, cant);
	}
	
	//Eliminar cada Pedido de Pedidos
	public void eliminarTodosLosPedidos ()
	{
		this.pedidos.eliminarTodos();
	}
	
	
	//Verifico si la cantidad de viandas a agregar junto a las ya agregadas, no excede el maximo establecido
	public boolean puedoAgregarVianda (int cantVianda) 
	{
		return this.pedidos.devolverMaximo() + cantVianda <= 30;
	}
	
	public Pedidos getPedidos ()
	{
		return pedidos;
	}

	//Valido que la fecha sea posterior a la fecha de la última venta registrada
	public boolean fechaEsMayor (LocalDateTime fecha)
	{
		return this.getFechaYHora().isBefore(fecha);
	}
	
	
	
	public void muestroVenta()
	{
		System.out.println("Mi venta");
		System.out.println(this.numero);
		System.out.println(this.fechaYHora);
		System.out.println(this.direccion);
		if(this.finalizado)
		{
			System.out.println("Finalizada");
		}
		else{
			System.out.println("Pendiente");

		}
		this.pedidos.muestroPedidos();
	}
	

	
	
}
