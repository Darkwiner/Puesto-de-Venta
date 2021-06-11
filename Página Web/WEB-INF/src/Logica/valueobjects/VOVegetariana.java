package Logica.valueobjects;

import java.io.Serializable;

public class VOVegetariana extends VOTipoVianda implements Serializable{

	private boolean ovolactea;
	private String descripcionAdicional;
	
	public VOVegetariana() {
		super();
	}
	
	public VOVegetariana(String codigoAlfanumerico, String descripcion, float precioUnitario, boolean esVege, boolean ovo, String desc) {
		super(codigoAlfanumerico, descripcion, precioUnitario, esVege);
		this.ovolactea = ovo;
		this.descripcionAdicional = desc;
	}

	public boolean getOvolactea() {
		return ovolactea;
	}

	public String getDescripcionAdicional() {
		return descripcionAdicional;
	}
	
}
