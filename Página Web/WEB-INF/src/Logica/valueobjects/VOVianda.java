package Logica.valueobjects;

import java.io.Serializable;

public class VOVianda implements Serializable,Comparable<VOVianda> {

	private String codigoAlfanumerico;
	private String descripcion;
	private float precioUnitario;


	public VOVianda ()
	{}

	public VOVianda(String codigoAlfanumerico, String descripcion, float precioUnitario) 
	{
		this.codigoAlfanumerico = codigoAlfanumerico;
		this.descripcion = descripcion;
		this.precioUnitario = precioUnitario;
	}

	public String getCodigoAlfanumerico() {
		return codigoAlfanumerico;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public float getPrecioUnitario() {
		return precioUnitario;
	}

	public int compareTo(VOVianda miVO){

		//VOVianda miVO=(VOVianda)miVO0;
		int resultado=0;
		//si a1> a2, devuelve un número negativo
		//si a1 <a2, devuelve un número positivo
		//si a1 == a2, devuelve 0


		if(miVO.getCodigoAlfanumerico().length()==(codigoAlfanumerico.length()))
		{
			if(miVO.getCodigoAlfanumerico().compareTo(codigoAlfanumerico)==0)//son iguales
			{resultado =0;   }
			else if(miVO.getCodigoAlfanumerico().compareTo(codigoAlfanumerico)<0)// primer string es mayor que el segundo
			{ resultado= 1;}
			else if(miVO.getCodigoAlfanumerico().compareTo(codigoAlfanumerico)>0)// segundo string es mayor que el primer	 
			{ resultado= -1;} 
		}
		else 
		{
			if(miVO.getCodigoAlfanumerico().length()>(codigoAlfanumerico.length()))
			{ resultado= -1;} 
			else
			{ resultado= 1;}
		}


		return resultado;
	}


}
