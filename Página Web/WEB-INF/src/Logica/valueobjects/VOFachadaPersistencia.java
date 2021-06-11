package Logica.valueobjects;

import java.time.LocalDateTime;
import Logica.Ventas;
import Logica.Viandas;
import java.io.Serializable;

public class VOFachadaPersistencia implements Serializable {
	
	private Viandas misViandas;
	private Ventas misVentas;
	
		
	public VOFachadaPersistencia(Viandas misViandas, Ventas misVentas) {
		this.misViandas = misViandas;
		this.misVentas = misVentas;
	}


	public Viandas getMisViandas() {
		return misViandas;
	}


	public Ventas getMisVentas() {
		return misVentas;
	}

	
	
	
	
}
