package Logica;

import java.util.*;

import Logica.valueobjects.*;

import java.io.Serializable;

public class Pedidos implements Serializable {

	LinkedList<Pedido> pedidos = new LinkedList<Pedido>();

	// Inserto un pedido al final de Pedidos // precondición pedidos ya está creado.
	public void insertarPedido(Pedido elPedido) {
		this.pedidos.add(elPedido);
	}

	// Devuelve pedido con la vianda pasada por parámetro
	public Pedido find(String cod) {
		boolean esta = false;
		int i = 0;
		Pedido elPedido = new Pedido();
		while (!esta && this.pedidos.size() > i) {
			if (this.pedidos.get(i).getVianda().equals(cod)) {
				elPedido = pedidos.get(i);
				esta = true;
			}
			i++;
		}
		return elPedido;
	}

	public boolean puedoModificoPedidos(String cod, int cant) {
		boolean sePuede = false;
		// obtengo el pedido
		Pedido elPedido = this.find(cod);
		if (elPedido.getCantViandas() >= cant) {
			sePuede = true;
		}
		return sePuede;
	}

	/*
	 * Busco en pedidos si hay un pedido con el codigo pasado y verifico si la
	 * cantidad es menor o igual a la del pedido, si se cumple resto o elimino
	 * pedido
	 */
	// Precondición el pedido existe
	public void modificoPedidos(String cod, int cant) {
		// obtengo el pedido
		Pedido elPedido = this.find(cod);
		if (elPedido.getCantViandas() == cant)
			pedidos.remove(elPedido);
		else
			elPedido.modificarPedido(cant);
	}

	/*
	 * Req - Listado de viandas de una venta?? Devuelvo un VOVianda[] con (código,
	 * descripción, precio unitario, cantidad de unidades y observación adicional).
	 * Las viandas serán listadas en el mismo orden en que fueron siendo agregadas a
	 * la venta. En caso de que no exista una venta con dicho número, se emitirá un
	 * mensaje de error.
	 */
	// public arrayList <VOVianda> listarPedido () //listar vianda sería
	// {

	// }

	// Devuelvo si Pedidos es vacío
	public boolean esVacio() {
		return this.pedidos.isEmpty();
	}

	// Elimino todos los pedido de pedidos
	public void eliminarTodos() {
		for (int i = 0; this.pedidos.size() > i; i++) {
			this.pedidos.remove(this.pedidos.get(i));
		}
	}

	// Devuelve la suma de la cantidad de viandas ingresadas en cada pedido
	public int devolverMaximo() {
		int maximo = 0;
		for (int i = 0; this.pedidos.size() > i; i++) {
			maximo = maximo + this.pedidos.get(i).totalPorPedido();
		}
		return maximo;
	}

	// confirmo si existe una vianda en pedidos con el código ingresado
	public boolean member(String cod) {
		boolean esta = false;
		int i = 0;
		while (!esta && this.pedidos.size() > i) {
			if (this.pedidos.get(i).getVianda().equals(cod))
				esta = true;
			i++;
		}
		return esta;
	}

	public float importeTotal() {
		float importe = 0;
		for (int i = 0; i < this.pedidos.size(); i++) {
			importe = importe + this.pedidos.get(i).importePedido();
		}
		return importe;
	}

	public void muestroPedidos() {
		System.out.println("Mi pedidos");
		Pedido miPedido;
		for (int i = 0; i < this.pedidos.size(); i++) {
			miPedido = this.pedidos.get(i);
			miPedido.muestroPedido();
		}
	}

	public ArrayList<VOTipoVianda> devolverPedidos() {

		//Variables
		ArrayList<VOTipoVianda> resultado = new ArrayList<VOTipoVianda>();
		Iterator<Pedido> iter = pedidos.iterator();
		Pedido miPedido;
		Vianda miVianda;
		VOTipoVianda miVO;
		int cantViandas;

		while (iter.hasNext()) {

			//obtengo el pedido y la vainda del mismo
			miPedido = iter.next();
			miVianda = miPedido.getVianda();

			cantViandas = miPedido.getCantViandas();
			if (miVianda instanceof Vianda && !(miVianda instanceof Vegetariana)) 
			{
				miVO = new VOViandaVenta(miVianda.getCodigoAlfanumerico(), miVianda.getDescripción(),miVianda.getPrecio(), miVianda.esVegetariana(), miPedido.getCantViandas());
				resultado.add(miVO);

			} else //if (miVianda instanceof Vegetariana)  
				// -> no es nesesario, si miVianda es tipo vianda (miVianda instanceof Vianda)esto es true y (miVianda instanceof Vegetariana) esto es falso pero esta negado por lo tanto es true, entra al if. 
				// -> si miVianda es tipo vegetariana (miVianda instanceof Vianda)esto es true y (miVianda instanceof Vegetariana) esto es true pero esta negado, por lo tanto es false y entra al else. 
			{
				// VOViandaVentaVegetariana(String codigoAlfanumerico, String descripcion, float
				// precioUnitario, boolean esVege, boolean ovo, String desc, int cant)
				VOTipoVianda miVOVege = new VOViandaVentaVegetariana(miVianda.getCodigoAlfanumerico(),
						miVianda.getDescripción(), miVianda.getPrecio(), miVianda.esVegetariana(),
						((Vegetariana) miVianda).getOvolacteas(), ((Vegetariana) miVianda).getdescripciónAdicional(),
						cantViandas);
				resultado.add(miVOVege);
			}
		}
		return resultado;

	}
}
