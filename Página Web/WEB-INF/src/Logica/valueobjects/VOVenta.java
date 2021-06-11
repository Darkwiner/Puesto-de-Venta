package Logica.valueobjects;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;


public class VOVenta implements Serializable {
	
	private LocalDateTime fechaYHora;
	private String direccion;
	private int numero;
	private float montoTotal;
	private boolean finalizado;
	
	
	public VOVenta (int num, LocalDateTime fyh, String direc, float montoTotal, boolean finalizado) 
	{
		this.fechaYHora = fyh;
		this.direccion = direc;
		this.numero = num;
		this.montoTotal = montoTotal;
		this.finalizado = finalizado;
	}
	
	public LocalDateTime getFechaYHora() {
		return fechaYHora;
	}

	public String getDireccion() {
		return direccion;
	}

	public int getNumero() 
	{
		return numero;
	}
	
	public float getMontoTotal() 
	{
		return montoTotal;
	}
	
	public boolean getFinalizado() 
	{
		return finalizado;
	}
	
	public String convertirFechaAString(LocalDateTime fechaDate) {

		return String.format(fechaYHora.getYear() + "-" + fechaYHora.getMonthValue() + "-" + fechaYHora.getDayOfMonth() +" "+fechaYHora.getHour()+":"+fechaYHora.getMinute());

	    }
	
	@Override
	public String toString(){
		return String.format(numero + " " + direccion + " " + convertirFechaAString(fechaYHora)); 
	}
	
	
}
