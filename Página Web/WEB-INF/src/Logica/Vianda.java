package Logica;

import java.io.Serializable;

//AGREGUE EL EJEMPLO DE OBJETO SERIALIZABLE
public class Vianda implements Serializable {

	private String codigoAlfanumerico;
	private String descripcion;
	private float precioUnitario;
	
	public Vianda()
	{}
	
	public Vianda(String codigo, String descrip, float precioUni) {
		this.codigoAlfanumerico = codigo;
		this.descripcion = descrip;
		this.precioUnitario = precioUni;
	}
	
	public String getCodigoAlfanumerico () 
	{
		return this.codigoAlfanumerico;
	}
	
	public String getDescripción () 
	{
		return this.descripcion;
	}
	
	public float getPrecio ()
	{
		return this.precioUnitario;
	}
	
	public void setCodigoAlfanumerico (String codigo)
	{
		this.codigoAlfanumerico = codigo;
	}
	
	public void setDescripcion (String descrip)
	{
		this.descripcion = descrip;
	}
	
	public void setPrecioUnitario (float precio)
	{
		this.precioUnitario = precio;
	}
	
	public boolean esVegetariana () 
	{
		return false;
	}
	
	public boolean contieneDescripcion(String descrip)
	{
		return (this.getDescripción().toLowerCase().contains(descrip.toLowerCase()));
	}
	
	public boolean equals(String cod)
	{
		return this.codigoAlfanumerico.equals(cod);
	}
	
	public void muestroVianda()
	{
		System.out.println("Mi vianda");
		System.out.println(this.codigoAlfanumerico);
		System.out.println(this.descripcion);
		System.out.println(this.precioUnitario);
	}
}
