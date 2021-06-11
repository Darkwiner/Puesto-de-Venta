package Logica.valueobjects;

import java.io.Serializable;

public class VOTipoVianda extends VOVianda implements Serializable,Comparable<VOVianda>{
	
	private boolean esVegetariana;
	
	public VOTipoVianda() {
		
	}

	public VOTipoVianda(String codigoAlfanumerico, String descripcion, float precioUnitario, boolean esVege) {
		super(codigoAlfanumerico, descripcion, precioUnitario);
		this.esVegetariana = esVege;
	}

	public boolean getEsVegetariana() {
		return esVegetariana;
	}	

	public int compareTo(VOTipoVianda miVO){
		int resultado;
		VOVianda laVianda=(VOTipoVianda)miVO;
		resultado=laVianda.compareTo(miVO);
		return resultado;
	}

}
