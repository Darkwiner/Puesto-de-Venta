package Logica;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import Logica.exceptions.CantidadAEliminarINcorrectaException;
import Logica.exceptions.FechaIncorrectaException;
import Logica.exceptions.LaVentaNoTienePedidosException;
import Logica.exceptions.NoExisteLaVentaException;
import Logica.exceptions.NoExisteLaViandaEnPedidoVentaException;
import Logica.exceptions.NoExisteLaViandaException;
import Logica.exceptions.NoExistenVentasException;
import Logica.exceptions.NoExistenViandasException;
import Logica.exceptions.NoHayDatosQueGuardarException;
import Logica.exceptions.NoPuedoAgregarMasViandasException;
import Logica.exceptions.PersistenciaException;
import Logica.exceptions.VentaFinalizadaException;
import Logica.exceptions.YaExisteViandaException;
import Logica.valueobjects.VOInicioVenta;
import Logica.valueobjects.VOTipoVianda;
import Logica.valueobjects.VOVenta;
import Logica.valueobjects.VOVianda;
import Logica.valueobjects.VOVegetariana;

public interface IFachada extends Remote {

	/*Creamos una nueva vianda, si ya existe el codigo lanza un error*/
	void altaNuevaVianda(VOVianda miVianda) throws YaExisteViandaException, RemoteException;

	/*Se crea una nueva Venta en proceso, se asigna un numero automáticamente y se verifica que fecha y 
	 * hora sean posteriores a la última fecha.*/
	void iniciarNuevaVenta(VOInicioVenta laVenta/*LocalDateTime fyh, String dire*/)
			throws FechaIncorrectaException, RemoteException;

	/*Se agrega vianda a venta en proceso, pasando codigo alfanumérico, cantidad de viandas 
	 	y numero de venta. Si no existe vianda, la venta no está en proceso o pasa el maximo, lanza error*/
	void agregarViandaAVenta(String cod, int cantVi, int numVenta) throws NoPuedoAgregarMasViandasException,
			VentaFinalizadaException, NoExisteLaVentaException, NoExisteLaViandaException, RemoteException;

	/*Se elimina vianda de venta en proceso, pasando codigo alfanumérico, cantidad de viandas y numero 
	 	de venta. Si la cantidad queda en 0, se elimina la vianda de la venta. Si venta no está en proceso, 
	 	no existe venta con ese número o no existe vianda con el codigo ingresado, devuelve error*/
	void eliminarViandaAVenta(String cod, int cantVi, int numVenta)
			throws VentaFinalizadaException, NoExisteLaVentaException, NoExisteLaViandaException,
			NoExisteLaViandaEnPedidoVentaException, CantidadAEliminarINcorrectaException, RemoteException;

	/*Finalizar venta en proceso - al finalizar, si se confirma, se marcará como finalizada 
	 	y queda almacenada. Si se cancela o no tiene registrada ninguna se elimina, sin importar si se 
	 	desea confirmarla o cancelarla. Si no existe venta con el numero se lanza error*/
	void finalizarVentaEnProceso(int numVenta, boolean confirmar)
			throws NoExisteLaVentaException, VentaFinalizadaException, RemoteException;

	/*Devolver listado de ventas en orden ascendente conteniendo número, fecha, hora, dirección de entrega, 
	 	monto total y estado*/
	ArrayList<VOVenta> listadoGeneralDeVentas() throws RemoteException, NoExistenVentasException;

	/*Devolver listado de viandas ordenado por codigo alfanumerico de vianda, conteniendo código, descripción, 
	 	precio unitario y tipo (común o vegetariana) de todas las viandas registradas en el sistema.*/
	public ArrayList <VOTipoVianda> listadoGeneralDeViandas() throws RemoteException, NoExistenViandasException;

	/*Dado el código, listar los datos de una vianda (código, descripción y precio unitario). Si es
	 	vegetariana, devolver si es ovoláctea o no junto con su descripción adicional. Si no existe emite error.*/
	VOVianda listadoDetalladoDeVianda(String cod) throws NoExisteLaViandaException, RemoteException;

	/*Dada la descripción de una vianda, o parte de ella, obtener un listado conteniendo código, descripción,
	 	precio unitario y tipo (común o vegetariana) de todas las viandas que coincidan con el criterio de
	 	búsqueda, ordenado alfanuméricamente por código*/
	public ArrayList<VOTipoVianda> listadoDeViandaDescripcion (String desc) throws NoExistenViandasException, RemoteException, NoExisteLaViandaException;

	/*Respaldar en disco todos los datos de la aplicación. se puede ejecutar cada vez que se quiera 
	 	Los datos se respaldarán juntos en un único archivo binario en disco (ubicado en el servidor central)*/
	void guardarCambios() throws PersistenciaException, NoHayDatosQueGuardarException, RemoteException;

	/*Restaurar a memoria todos los datos de la aplicación almacenados en disco. Este requerimiento será 
	 	ejecutado automáticamente en el servidor central cada vez que el sistema inicie su ejecución.(NO por
	 	usuarios)*/
	void restaurarInformacion() throws PersistenciaException, RemoteException;

	/*Devolver listado de toda las vindas de una ventas */
	ArrayList <VOTipoVianda> listadoViandasDeVenta (int numVenta) throws  RemoteException,LaVentaNoTienePedidosException, NoExisteLaVentaException;

	
	
}
