package Logica;
import java.io.Serializable;

public class Pedido implements Serializable{

	private int cantViandas;
	private Vianda vianda;  
		
	public Pedido ()
	{
		
	}
	
	public Pedido (int cantVian, Vianda vi)
	{
		this.cantViandas = cantVian;
		this.vianda = vi; 
	}
	
	public int getCantViandas ()
	{
		return this.cantViandas;
	}
	
	public Vianda getVianda ()
	{
		return this.vianda;
	}
	
	public void setCantViandas (int cant)
	{
		this.cantViandas = cant;
	}
	
	public void setVianda (Vianda vi)
	{
		this.vianda = vi;
	}
	/*
	public boolean equals (String cod)
	{
		return this.vianda.getCodigoAlfanumerico().equals(cod);
	}
	*/
	public void modificarPedido (int cant)
	{
		this.setCantViandas(this.getCantViandas() - cant);
	}
	
	public void sumarCantidad (int cant)
	{
		this.setCantViandas(this.getCantViandas() + cant);
	}
	
	public float importePedido ()
	{
		return this.getCantViandas()*this.getVianda().getPrecio();
	}
	
	public int totalPorPedido()
	{
		return this.getCantViandas();
	}
	
	public void muestroPedido()
	{
		System.out.println("Mi pedido");
		System.out.println(this.cantViandas);
		this.vianda.muestroVianda();
	}
	
	
}
