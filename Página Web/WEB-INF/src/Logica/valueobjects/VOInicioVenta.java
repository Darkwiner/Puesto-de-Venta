package Logica.valueobjects;

import java.io.Serializable;
import java.time.LocalDateTime;

public class VOInicioVenta implements Serializable {
	
	private LocalDateTime fechaYHora;
	private String direccion;
	
	public VOInicioVenta (LocalDateTime fyh, String direc) 
	{
		this.fechaYHora = fyh;
		this.direccion = direc;
	}
	
	public LocalDateTime getFechaYHora() {
		return fechaYHora;
	}

	public String getDireccion() {
		return direccion;
	}	
	
}
