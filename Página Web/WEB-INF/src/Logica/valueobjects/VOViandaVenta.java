package Logica.valueobjects;

public class VOViandaVenta extends VOTipoVianda{

	private int cantidad;

	public VOViandaVenta() {
		super();
	}

	public VOViandaVenta(String codigoAlfanumerico, String descripcion, float precioUnitario, boolean esVege, int cant) {
		super(codigoAlfanumerico, descripcion, precioUnitario, esVege);
		cantidad = cant;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}
