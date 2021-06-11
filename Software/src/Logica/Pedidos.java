package Logica;

import java.util.*;

import Logica.valueobjects.*;

import java.io.Serializable;

public class Pedidos implements Serializable {

	LinkedList<Pedido> pedidos = new LinkedList<Pedido>();

	//Inserto un pedido al final de Pedidos //Precondición: pedidos ya está creado.
	public void insertarPedido(Pedido elPedido) {
		this.pedidos.add(elPedido);
	}

	//Devuelve pedido con la vianda pasada por parámetro
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

	/* Busco en pedidos si hay un pedido con el codigo pasado y verifico si la
	  cantidad es menor o igual a la del pedido, si se cumple resto o elimino
	  pedido*/
	//Precondición el pedido existe
	public void modificoPedidos(String cod, int cant) {
		//Obtengo el pedido
		Pedido elPedido = this.find(cod);
		if (elPedido.getCantViandas() == cant)
			pedidos.remove(elPedido);
		else
			elPedido.modificarPedido(cant);
	}

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

	//Confirmo si existe una vianda en pedidos con el código ingresado
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
			//Obtengo el pedido y la vianda del mismo
			miPedido = iter.next();
			miVianda = miPedido.getVianda();
			cantViandas = miPedido.getCantViandas();
			if (miVianda instanceof Vianda && !(miVianda instanceof Vegetariana)) 
			{
				miVO = new VOViandaVenta(miVianda.getCodigoAlfanumerico(), miVianda.getDescripción(),miVianda.getPrecio(), miVianda.esVegetariana(), miPedido.getCantViandas());
				resultado.add(miVO);
			} else  
			{
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
