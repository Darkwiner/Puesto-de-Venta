package Logica.valueobjects;

public class VOViandaVentaVegetariana extends VOVegetariana{

	private int cantidad;
	
	
	public VOViandaVentaVegetariana() {
		super();
	}

	public VOViandaVentaVegetariana(String codigoAlfanumerico, String descripcion, float precioUnitario, boolean esVege, boolean ovo, String desc, int cant) {
		super(codigoAlfanumerico, descripcion, precioUnitario, esVege, ovo, desc);
		cantidad = cant;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


}
