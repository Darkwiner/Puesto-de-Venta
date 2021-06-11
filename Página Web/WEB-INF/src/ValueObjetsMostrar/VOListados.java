package ValueObjetsMostrar;

import java.util.ArrayList;

import Logica.valueobjects.VOTipoVianda;

public class VOListados {
	
private String codigoAlfanumerico;
private String descripcion;
private float precioUnitario;
private String esVege;
	
	public VOListados() {
		
	}

	public VOListados(String codigoAlfanumerico, String descripcion, float precioUnitario, boolean esVege) {
		this.codigoAlfanumerico=codigoAlfanumerico;
		this.descripcion=descripcion;
		this.precioUnitario=precioUnitario;
		if(esVege)
		{
			this.esVege = "Vegetariana";
		}else
		{
			this.esVege = "No vegetariana";
		}
		
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

	public String getEsVege() {
		return esVege;
	}

	public ArrayList<VOListados> cambiarTipo(ArrayList<VOTipoVianda> lista){
		
		ArrayList<VOListados> resultado=new ArrayList<VOListados>();
		VOTipoVianda miVOTipoVianda;
		VOListados miVO;
		for(int i=0; i<lista.size();i++)
		{
			miVOTipoVianda=lista.get(i);
			miVO=new VOListados( miVOTipoVianda.getCodigoAlfanumerico(),  miVOTipoVianda.getDescripcion(), miVOTipoVianda.getPrecioUnitario(), miVOTipoVianda.getEsVegetariana());
			resultado.add(miVO);
		}
		return resultado;
	}
}
