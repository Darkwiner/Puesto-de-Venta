package Logica;

import java.time.LocalDateTime;
import java.util.*;
import Logica.exceptions.*;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import Logica.valueobjects.*;


import Persistencia.*;


//preguntar porqué funciona con Fachada serializable y no con la iFachada en el cliente/servidor
public class Fachada extends UnicastRemoteObject implements IFachada {

	private static final long serialVersionUID = 1L;
	private Viandas dicViandas;
	private Ventas secVentas;
	public boolean datosModificados;
	private static Fachada instancia;
	private Monitor miMonitor;

	//MODIFICAR CABEZALES DE PROCEDIMIENTOS PARA RECIBIR UN VO 
	//IMPLEMENTAR CLASE MONITOR
	//RMI

	//Aplicar Singleton
	//capaLogica ()


	public Fachada () throws RemoteException
	{
		dicViandas = new Viandas();
		secVentas = new Ventas();
		datosModificados = false;
		miMonitor = Monitor.getInstancia();
		try {
			restaurarInformacion();
		} catch (RemoteException | PersistenciaException e) {
			e.printStackTrace();
		}
	}

	public synchronized static IFachada getInstancia()
	{
		try{
			if (instancia == null)
			{
				instancia = new Fachada();
			}
		}catch(RemoteException e)
		{
			System.out.println(e.getMessage());
		}
		return instancia;
	}

	/*Creamos una nueva vianda, si ya existe el codigo lanza un error*/
	@Override
	public  void altaNuevaVianda (VOVianda miVianda) throws YaExisteViandaException, RemoteException
	{
		miMonitor.comienzoEscritura();
		try {
			boolean Vacia=false,noEsta=false;

			if(dicViandas.esVacio())
			{
				Vacia = true;
			}
			if(!Vacia) 
			{
				if (!dicViandas.member(miVianda.getCodigoAlfanumerico()))
				{
					noEsta = true;
				}
			}
			if (Vacia || noEsta)
			{
				datosModificados = true;
				if (miVianda instanceof VOVianda && !(miVianda instanceof VOVegetariana))
				{
					Vianda miViandaNormal = new Vianda(miVianda.getCodigoAlfanumerico(), miVianda.getDescripcion(), miVianda.getPrecioUnitario());
					dicViandas.insertVianda(miVianda.getCodigoAlfanumerico(), miViandaNormal);
				}
				else 
				{
					VOVegetariana miVege =(VOVegetariana)miVianda;
					Vegetariana miViandaVegetariana = new Vegetariana (miVege.getCodigoAlfanumerico(), miVege.getDescripcion(), miVege.getPrecioUnitario(), miVege.getOvolactea(),miVege.getDescripcionAdicional() );
					dicViandas.insertVianda(miVianda.getCodigoAlfanumerico(), miViandaVegetariana);
				}
				miMonitor.terminoEscritura();
			}
			else
			{
				miMonitor.terminoEscritura();
				throw new YaExisteViandaException();
			}
		}
		catch(Exception e ) 
		{
			miMonitor.terminoEscritura();
			throw e;
		}

	}

	/*Se crea una nueva Venta en proceso, se asigna un numero autom�ticamente y se verifica que fecha y 
	 * hora sean posteriores a la �ltima fecha.*/
	@Override
	public void iniciarNuevaVenta (VOInicioVenta laVenta) throws FechaIncorrectaException, RemoteException
	{
		miMonitor.comienzoEscritura();
		if (secVentas.esVacio())
		{
			Venta miVenta = new Venta(1, laVenta.getFechaYHora(), laVenta.getDireccion(), false);
			secVentas.insertarVenta(miVenta);
			miMonitor.terminoEscritura();
		}
		else
		{
			if (secVentas.comparoFechaUltimaVenta(laVenta.getFechaYHora()))
			{
				datosModificados = true;
				Venta miVenta = new Venta (secVentas.proximoIdVenta(), laVenta.getFechaYHora(), laVenta.getDireccion(), false);
				secVentas.insertarVenta(miVenta);
				miMonitor.terminoEscritura();
			}
			else
			{
				miMonitor.terminoEscritura();
				throw new FechaIncorrectaException();
			}
		}

	}

	/*Se agrega vianda a venta en proceso, pasando codigo alfanum�rico, cantidad de viandas 
	 	y numero de venta. Si no existe vianda, la venta no est� en proceso o pasa el maximo, lanza error*/
	@Override
	public void  agregarViandaAVenta (String cod, int cantVi, int numVenta)throws NoPuedoAgregarMasViandasException,VentaFinalizadaException,NoExisteLaVentaException,NoExisteLaViandaException, RemoteException
	{   
		miMonitor.comienzoEscritura();
		try 
		{
			if (dicViandas.member(cod))
			{
				if (secVentas.member(numVenta))
				{
					Venta miVenta = secVentas.find(numVenta);
					if (!miVenta.getFinalizado())
					{
						if (miVenta.puedoAgregarVianda(cantVi))
						{
							Pedidos miPedidos = miVenta.getPedidos();
							if (miPedidos.member(cod))
							{
								Pedido miPedido = miPedidos.find(cod);
								miPedido.sumarCantidad(cantVi);
							}
							else
							{
								Vianda miVianda = dicViandas.find(cod);
								Pedido miPedido = new Pedido (cantVi, miVianda);
								miPedidos.insertarPedido(miPedido);
							}
							datosModificados = true;
							miMonitor.terminoEscritura();
						}
						else {miMonitor.terminoEscritura();
						throw new NoPuedoAgregarMasViandasException();}
					}
					else {miMonitor.terminoEscritura();
					throw new VentaFinalizadaException();}
				}
				else {miMonitor.terminoEscritura();
				throw new NoExisteLaVentaException();} 
			}
			else {miMonitor.terminoEscritura();
			throw new NoExisteLaViandaException();}
		}
		catch (Exception e) {
			miMonitor.terminoEscritura();
			throw e;
		}	
	}

	/*Se elimina vianda de venta en proceso, pasando codigo alfanum�rico, cantidad de viandas y numero 
	 	de venta. Si la cantidad queda en 0, se elimina la vianda de la venta. Si venta no est� en proceso, 
	 	no existe venta con ese n�mero o no existe vianda con el codigo ingresado, devuelve error*/
	@Override
	public void eliminarViandaAVenta (String cod, int cantVi, int numVenta) throws VentaFinalizadaException,NoExisteLaVentaException,NoExisteLaViandaException,NoExisteLaViandaEnPedidoVentaException,CantidadAEliminarINcorrectaException, RemoteException
	{
		miMonitor.comienzoEscritura();
		try
		{
			if (secVentas.member(numVenta))
			{
				Venta miVenta = new Venta();
				miVenta = secVentas.find(numVenta);
				if (!miVenta.getFinalizado())
				{
					if (dicViandas.member(cod))
					{
						Pedidos miPedidos = miVenta.getPedidos();
						//valido si la venta tiene ese pedido
						if(miPedidos.member(cod))
						{
							if(miPedidos.puedoModificoPedidos(cod, cantVi))
							{
								datosModificados = true;
								miPedidos.modificoPedidos(cod, cantVi);
								miMonitor.terminoEscritura();
							}
							else{miMonitor.terminoEscritura();
							throw new CantidadAEliminarINcorrectaException(); }
						}
						else{miMonitor.terminoEscritura();
						throw new NoExisteLaViandaEnPedidoVentaException();}	
					}
					else{miMonitor.terminoEscritura();
					throw new NoExisteLaViandaException();}
				}
				else{miMonitor.terminoEscritura();
				throw new VentaFinalizadaException();}
			}
			else{miMonitor.terminoEscritura();
			throw new NoExisteLaVentaException();}
		}
		catch (Exception e) {
			miMonitor.terminoEscritura();
			throw e;
		}
	}

	/*Finalizar venta en proceso - al finalizar, si se confirma, se marcar� como finalizada 
	 	y queda almacenada. Si se cancela o no tiene registrada ninguna se elimina, sin importar si se 
	 	desea confirmarla o cancelarla. Si no existe venta con el numero se lanza error*/
	@Override
	public void finalizarVentaEnProceso (int numVenta, boolean confirmar)throws NoExisteLaVentaException,VentaFinalizadaException, RemoteException
	{
		miMonitor.comienzoEscritura();
		try
		{
			if (secVentas.member(numVenta))
			{
				Venta miVenta = secVentas.find(numVenta);
				if (!miVenta.getFinalizado())
				{
					datosModificados = true;
					if (miVenta.esVacio() || confirmar == false)
					{
						miVenta.eliminarTodosLosPedidos();
						secVentas.eliminoVenta(numVenta);
					}
					else
					{
						miVenta.setFinalizado(confirmar);
					}
					miMonitor.terminoEscritura();
				}
				else {miMonitor.terminoEscritura();
				throw new VentaFinalizadaException();}
			}
			else {miMonitor.terminoEscritura();
			throw new NoExisteLaVentaException();}
		}
		catch (Exception e) {
			miMonitor.terminoEscritura();
			throw e;
		}
	}


	/*Devolver listado de ventas en orden ascendente conteniendo n�mero, fecha, hora, direcci�n de entrega, 
	 	monto total y estado*/
	@Override
	public ArrayList <VOVenta> listadoGeneralDeVentas () throws RemoteException, NoExistenVentasException
	{
		miMonitor.comienzoLectura();
		if(!secVentas.esVacio())
		{
			//RETORNAR LISTADO VACIO Y CONTROLAR EN LA CAPA GRAFICA SI EL LISTADO VIENE VACIO
			ArrayList <VOVenta> listado = new ArrayList<VOVenta>();
			listado = secVentas.listarVentas();
			miMonitor.terminoLectura();
			return listado;
		}
		else
		{
			miMonitor.terminoLectura();
			throw new NoExistenVentasException();
		}

	}

	/*Devolver listado de viandas ordenado por codigo alfanumerico de vianda, conteniendo c�digo, descripci�n, 
	 	precio unitario y tipo (com�n o vegetariana) de todas las viandas registradas en el sistema.*/
	@Override
	public ArrayList <VOTipoVianda> listadoGeneralDeViandas() throws RemoteException, NoExistenViandasException
	{
		miMonitor.comienzoLectura();
		if (!dicViandas.esVacio())
		{
			//RETORNAR LISTADO VACIO Y CONTROLAR EN LA CAPA GRAFICA SI EL LISTADO VIENE VACIO
			ArrayList <VOTipoVianda> listaViandas = new ArrayList<VOTipoVianda>();
			listaViandas = dicViandas.listadoGeneralDeViandas();
			miMonitor.terminoLectura();
			Collections.sort(listaViandas);
			return listaViandas;
		}
		else
			miMonitor.terminoLectura();
			throw new NoExistenViandasException();
	}

	/*Dado el c�digo, listar los datos de una vianda (c�digo, descripci�n y precio unitario). Si es
	 	vegetariana, devolver si es ovol�ctea o no junto con su descripci�n adicional. Si no existe emite error.*/
	@Override
	public VOVianda listadoDetalladoDeVianda (String cod) throws NoExisteLaViandaException, RemoteException
	{
		miMonitor.comienzoLectura();
		if (dicViandas.member(cod))
		{
			VOVianda miVOVianda = new VOVianda();
			miVOVianda = dicViandas.devolverVOVianda(cod);
			miMonitor.terminoLectura();
			return miVOVianda;
		}
		else
		{
			miMonitor.terminoLectura();
			throw new NoExisteLaViandaException();
		}
	}

	/*Dada la descripci�n de una vianda, o parte de ella, obtener un listado conteniendo c�digo, descripci�n,
	 	precio unitario y tipo (com�n o vegetariana) de todas las viandas que coincidan con el criterio de
	 	b�squeda, ordenado alfanum�ricamente por c�digo*/
	@Override
	public ArrayList <VOTipoVianda> listadoDeViandaDescripcion (String descrip) throws RemoteException, NoExisteLaViandaException
	{
		miMonitor.comienzoLectura();
		if(!dicViandas.esVacio())
		{
			ArrayList <VOTipoVianda> miVOTipoVianda = dicViandas.buscoPorDescripcion(descrip);
			miMonitor.terminoLectura();
			return miVOTipoVianda;
		}
		else
			throw new NoExisteLaViandaException();
	}

	/*Respaldar en disco todos los datos de la aplicaci�n. se puede ejecutar cada vez que se quiera 
	 	Los datos se respaldar�n juntos en un �nico archivo binario en disco (ubicado en el servidor central)*/
	//	@Override
	public void guardarCambios () throws PersistenciaException, NoHayDatosQueGuardarException, RemoteException
	{
		if (datosModificados)
		{
			miMonitor.comienzoEscritura();
			try
			{
				datosModificados = false;
				VOFachadaPersistencia fach = new VOFachadaPersistencia(dicViandas, secVentas);
				Persistencia per = new Persistencia();
				per.respaldar(fach,"Datos.dat");
				miMonitor.terminoEscritura();
			}
			catch (Exception e) {
				miMonitor.terminoEscritura();
				throw e;
			}

		}
		else {
			miMonitor.terminoEscritura();
			throw new NoHayDatosQueGuardarException();
		}
	}

	/*Restaurar a memoria todos los datos de la aplicaci�n almacenados en disco. Este requerimiento ser� 
	 	ejecutado autom�ticamente en el servidor central cada vez que el sistema inicie su ejecuci�n.(NO por
	 	usuarios)*/

	//@Override
	public void restaurarInformacion()  throws PersistenciaException, RemoteException
	{

		miMonitor.comienzoEscritura();
		try {
			Persistencia per = new Persistencia();
			VOFachadaPersistencia fach =per.restaurar("Datos.dat");
			dicViandas = fach.getMisViandas();
			secVentas = fach.getMisVentas();
			miMonitor.terminoEscritura();
		}
		catch(Exception e ) 
		{
			miMonitor.terminoEscritura();
			throw e;
		}

	}

	/*Devolver listado de toda las vindas de una ventas */
	public ArrayList <VOTipoVianda> listadoViandasDeVenta (int numeroVenta) throws  RemoteException, LaVentaNoTienePedidosException, NoExisteLaVentaException
	{
		miMonitor.comienzoLectura();
		ArrayList <VOTipoVianda> listado = new ArrayList<VOTipoVianda>();
		if (secVentas.member(numeroVenta))
		{
			Venta miVenta = secVentas.find(numeroVenta);
			if (!miVenta.esVacio())
			{
				Pedidos listadoPedidos= miVenta.getPedidos();//secVentas.listarViandaVentas(numeroVenta); aca ya tenemos la venta
				listado= listadoPedidos.devolverPedidos();//nuevo
				miMonitor.terminoLectura();
			}
			else
			{
				miMonitor.terminoLectura();
				throw new LaVentaNoTienePedidosException();
			}
		}
		else
		{
			miMonitor.terminoLectura();
			throw new NoExisteLaVentaException() ;
		}
		return listado;
	}

}
