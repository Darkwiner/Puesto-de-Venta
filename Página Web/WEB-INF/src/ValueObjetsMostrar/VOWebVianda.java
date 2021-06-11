package ValueObjetsMostrar;

import java.util.ArrayList;

import Logica.valueobjects.VOTipoVianda;
import Logica.valueobjects.VOVegetariana;
import Logica.valueobjects.VOVianda;

public class VOWebVianda {

	private String codigoAlfanumerico;
	private String descripcion;
	private float  precioUnitario;
	private String ovolactea;
	private String descripcionAdicional;




	public VOWebVianda()
	{

	}


	public VOWebVianda(String codigoAlfanumerico, String descripcion, float precioUnitario, boolean ovolactea,String descripcionAdicional) {
		super();
		this.codigoAlfanumerico = codigoAlfanumerico;
		this.descripcion = descripcion;
		this.precioUnitario = precioUnitario;
		if(ovolactea)
		{
			this.ovolactea = "Ovolactea";
		}
		else
		{
			this.ovolactea = "No ovolactea";
		}
		this.descripcionAdicional = descripcionAdicional;
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







	public String getOvolactea() {
		return ovolactea;
	}







	public String getDescripcionAdicional() {
		return descripcionAdicional;
	}


	




}
