package Logica;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.text.html.HTMLDocument.Iterator;

import Logica.valueobjects.*;

public class Ventas implements Serializable {

	//Crear lista encadenada preguntar
	LinkedList <Venta> ventas=new LinkedList <Venta>();

	//Verifico si existe una venta
	public boolean member (int numVenta)
	{
		boolean esta = false;
		int i=0;
		while(!esta && this.ventas.size()>i)
		{
			if(this.ventas.get(i).getNumero() == numVenta)
				esta=true;
			i++;
		}
		return esta;
	}

	//Inserto una venta al final de la lista - Precondición la venta no existe
	public void insertarVenta (Venta miVenta)
	{
		this.ventas.add(miVenta);
	}

	//Devuelvo la Venta con el numero pasado por parámetro
	public Venta find (int numVenta)
	{

		boolean esta = false;
		Venta venta = new Venta();
		int i=0;
		while(!esta && this.ventas.size()>i)
		{
			if(this.ventas.get(i).getNumero() == numVenta)
			{
				esta=true;
				venta = this.ventas.get(i);
			}
			i++;
		}
		return venta;
	}

	//Devuelvo listado de ventas en orden ascendente 
	public ArrayList <VOVenta> listarVentas () 
	{
		ArrayList <VOVenta> lista = new ArrayList<VOVenta>();
		VOVenta miVOVenta;
		int i=0;
		Venta miVenta;
		while(this.ventas.size()>i)
		{
			miVenta = this.ventas.get(i);
			miVOVenta = new VOVenta(miVenta.getNumero(), miVenta.getFechaYHora(), miVenta.getDireccion(), miVenta.obtengoMontoTotal(), miVenta.getFinalizado());
			lista.add(miVOVenta);
			i++;
		}
		return lista;
	}

	//Devuelve el nuevo numero de venta
	public int proximoIdVenta ()
	{
		int total=this.ventas.size()-1;
		return this.ventas.get(total).getNumero()+1;
	}

	/*Verifico que la venta esté en proceso*/
	public boolean ventaEnProceso (int numVenta)
	{
		Venta miVenta = this.find(numVenta);
		return miVenta.getFinalizado();
	}

	//Elimino la venta registrada con el numero de venta pasado por parámetro
	public void eliminoVenta (int numVenta)
	{
		boolean esta = false;
		int i=0;
		while(!esta && this.ventas.size()>i)
		{
			if(this.ventas.get(i).getNumero() == numVenta)
			{
				esta=true;
				this.ventas.remove(i);
			}
			i++;
		}
	}

	//Comparo la fecha ingresada con la última en la lista y devuelvo true si es mayor o sea válida
	public boolean comparoFechaUltimaVenta (LocalDateTime fecha)
	{
		int total=this.ventas.size() - 1;
		Venta miVenta = this.ventas.get(total);
		return miVenta.fechaEsMayor(fecha);	
	}

	//Verifico si el listado de ventas es vacío
	public boolean esVacio ()
	{
		return this.ventas.isEmpty();
	}



	public void muestroVentas()
	{
		Venta miVenta;
		for(int i=0;i<this.ventas.size();i++)
		{
			miVenta=this.ventas.get(i);
			miVenta.muestroVenta();
		}

	}

	//Devuelvo los pedidos de mi venta 
			public ArrayList <VOTipoVianda> listarViandaVentas (int numVenta) 
			{
				Venta miVenta = this.find(numVenta);
				Pedidos misPedidos = miVenta.getPedidos();
				return misPedidos.devolverPedidos();
			}
	
	
		
}




