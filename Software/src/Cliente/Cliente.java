package Cliente;

import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.rmi.NotBoundException;

import Logica.Fachada;
import Logica.IFachada;
import Logica.Vianda;
import Logica.exceptions.*;
import Logica.valueobjects.*;

public class Cliente {
/*
	public static void main(String[] args) {
		try {


			//primera prueba cargar datos en el sistema y guadar , la variable cargaInicialConSave=true
			//-segunda prueba  restaurar datos y listar lo restaurado , hay que bajar el servidor y poner la variable cargaInicialConSave=true
			boolean cargaInicialConSave=false;


			Properties p = new Properties();
			String nomArch = "config/config.properties";
			p.load (new FileInputStream (nomArch));
			String ip = p.getProperty("ipServidor");
			int puerto = Integer.parseInt(p.getProperty("puertoServidor"));
			// pongo a correr el rmiregistry
			String ruta = "//"+ip+":"+puerto+"/fachada";
			// instancio mi Objeto Remoto y lo publico
			IFachada miFachada = (IFachada)Naming.lookup(ruta);

			if(cargaInicialConSave) 
			{
				//////////////////////Creo Viandas /////////////////////
				System.out.println("\n\n\n****Prueba alta de nuevas viandas****");

				String cod1="a1";
				String desc1="Milanesas con pure";
				float precio1=215;
				VOVegetariana viandaUno=new VOVegetariana(cod1, desc1, precio1,false,false," ");

				String cod2="a2";
				String desc2="Piza";
				float precio2=300;
				VOVegetariana viandaDos=new VOVegetariana(cod2, desc2, precio2,true,false,"tiene queso");

				String cod3="a3";
				String desc3="Tarata de pollo";
				float precio3=510.5f;
				VOVegetariana viandaTres=new VOVegetariana(cod3, desc3, precio3,true,true,"no tiene queso ni huevo");

				String cod5="a5";
				String desc5="pescado a La marinera";
				float precio5=210.5f;
				VOVegetariana viandaCinco=new VOVegetariana(cod5, desc5, precio5,true,true,"no tiene queso ni huevo");

				String cod6="a6";
				String desc6="Polenta";
				float precio6=210.5f;
				VOVegetariana viandaSeis=new VOVegetariana(cod6, desc6, precio6,true,true,"huevo");
				try{
					//repito vianda y controlo duplicados
					VOVegetariana viandaCuatro= new VOVegetariana(cod1, desc1, precio1,false,false," ");
					System.out.println("  Doy de alta 4 viandas");
					miFachada.altaNuevaVianda(viandaUno);
					miFachada.altaNuevaVianda(viandaDos);
					miFachada.altaNuevaVianda(viandaTres);
					miFachada.altaNuevaVianda(viandaCinco);
					miFachada.altaNuevaVianda(viandaSeis);
					System.out.println("  Doy de alta vianda 4 igual a la vianda 1");
					miFachada.altaNuevaVianda(viandaCuatro);

				}catch(YaExisteViandaException e)
				{
					System.out.println(e.getMessage());
				}
				catch (RemoteException e)
				{
					System.out.println(e.getMessage());
				}

				/////////////////////Muestro todas las viandas  /////////////////////
				try{
					System.out.println("\n\n\n****Listado de todas las viandas del sistema****");
					ArrayList<VOTipoVianda> misViandas= miFachada.listadoGeneralDeViandas();
					for(int i=0;i<misViandas.size();i++)
					{ 
						System.out.println("--> codigo:"+misViandas.get(i).getCodigoAlfanumerico());
						System.out.println("    descripcion:"+misViandas.get(i).getDescripcion());
						System.out.println("    precio:"+misViandas.get(i).getPrecioUnitario());
						if(misViandas.get(i).getEsVegetariana())
						{
							System.out.println("Soy vegetariana"); 
						}
						else {
							System.out.println("No soy vegetariana"); 
						}
					}

				}
				catch (RemoteException e)
				{
					System.out.println(e.getMessage());
				}

				/////////////////////Creo ventas iniciales /////////////////////
				System.out.println("\n\n\n****Creo ventas****");
				String dir1="arenal grande 1570";
				LocalDateTime f1= LocalDateTime.now();
				VOVenta venta1= new VOVenta(0, f1, dir1, 0, false);

				String dir2="colonia 1635";
				Thread.sleep(800);
				LocalDateTime f2= LocalDateTime.now();
				VOVenta venta2= new VOVenta(0, f2, dir2, 0, false);

				String dir3="uruguay 1635";
				Thread.sleep(800);
				LocalDateTime f3= LocalDateTime.now();
				VOVenta venta3= new VOVenta(0, f3, dir3, 0, false);

				String dir4="cufre 1635";
				VOVenta venta4= new VOVenta(0, f1, dir4, 0, false);

				String dir5="manulo lamas 1635";
				Thread.sleep(800);
				LocalDateTime f5= LocalDateTime.now();
				VOVenta venta5= new VOVenta(0, f5, dir5, 0, false);
				
				String dir6="manulo 1635";
				Thread.sleep(800);
				LocalDateTime f6= LocalDateTime.now();
				VOVenta venta6= new VOVenta(0, f5, dir5, 0, false);
				
				try{
					System.out.println("  Doy de alta tres ventas con la hora correcta");
					miFachada.iniciarNuevaVenta(venta1);
					miFachada.iniciarNuevaVenta(venta2);
					miFachada.iniciarNuevaVenta(venta3);
					miFachada.iniciarNuevaVenta(venta5);
					miFachada.iniciarNuevaVenta(venta6);
					System.out.println("  Doy una cuarta venta con fecha anterior a la ultima venta ingresada");
					miFachada.iniciarNuevaVenta(venta4);
					
				}catch(FechaIncorrectaException e)
				{
					System.out.println(e.getMessage());
				}
				catch (RemoteException e)
				{
					System.out.println(e.getMessage());
				}

				/////////////////////Confirmo una venta que no existe y vacia/////////////////////
				System.out.println("\n\n\n****Confirmo una venta no confirmada que no tiene vianda****");
				boolean confirmo=true;
				try{
					System.out.println("  Confirmo la segunda venta"); 
					miFachada.finalizarVentaEnProceso(2, confirmo);

				}
				catch(VentaFinalizadaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NoExisteLaVentaException e)
				{
					System.out.println(e.getMessage());
				}
				catch (RemoteException e)
				{
					System.out.println(e.getMessage());
				}

				try{
					System.out.println("  Confirmo venta que no existe");
					miFachada.finalizarVentaEnProceso(70, confirmo);
				}
				catch(VentaFinalizadaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NoExisteLaVentaException e)
				{
					System.out.println(e.getMessage());
				}
				catch (RemoteException e)
				{
					System.out.println(e.getMessage());
				}

				/////////////////////Agrego vianda existente a venta /////////////////////

				System.out.println("\n\n\n****Agrego viandas a las ventas****");
				try{
					System.out.println("  Agrego vindas a la venta 1 sin pasarme del maximo");
					miFachada.agregarViandaAVenta(cod1 , 10, 1);
					miFachada.agregarViandaAVenta(cod3 , 10, 1);
					miFachada.agregarViandaAVenta(cod2 , 10, 1);
				}
				catch(NoPuedoAgregarMasViandasException e)
				{
					System.out.println(e.getMessage());
				}
				catch(VentaFinalizadaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NoExisteLaVentaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NoExisteLaViandaException e)
				{
					System.out.println(e.getMessage());
				}
				catch (RemoteException e)
				{
					System.out.println(e.getMessage());
				}

				try{
					System.out.println("  Agrego vindas a la venta 3 sin pasarme del maximo");
					miFachada.agregarViandaAVenta(cod1 , 15, 3);
					miFachada.agregarViandaAVenta(cod3 , 10, 3);
				}
				catch(NoPuedoAgregarMasViandasException e)
				{
					System.out.println(e.getMessage());
				}
				catch(VentaFinalizadaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NoExisteLaVentaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NoExisteLaViandaException e)
				{
					System.out.println(e.getMessage());
				}
				catch (RemoteException e)
				{
					System.out.println(e.getMessage());
				}

				try{
					System.out.println("  Agrego vindas a la venta 5 sin pasarme del maximo");
					miFachada.agregarViandaAVenta(cod5 , 10, 4);
				}
				catch(NoPuedoAgregarMasViandasException e)
				{
					System.out.println(e.getMessage());
				}
				catch(VentaFinalizadaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NoExisteLaVentaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NoExisteLaViandaException e)
				{
					System.out.println(e.getMessage());
				}
				catch (RemoteException e)
				{
					System.out.println(e.getMessage());
				}
				try{
					System.out.println("  Agrego vindas a la venta 1 que ya tiene 30 viandas");
					miFachada.agregarViandaAVenta(cod1 , 10, 1);
				}
				catch(NoPuedoAgregarMasViandasException e)
				{
					System.out.println(e.getMessage());
				}
				catch(VentaFinalizadaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NoExisteLaVentaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NoExisteLaViandaException e)
				{
					System.out.println(e.getMessage());
				}
				catch (RemoteException e)
				{
					System.out.println(e.getMessage());
				}


				try{
					System.out.println("  Agrego vindas a venta que no existe");
					miFachada.agregarViandaAVenta(cod1 , 10, 50);
				}
				catch(NoPuedoAgregarMasViandasException e)
				{
					System.out.println(e.getMessage());
				}
				catch(VentaFinalizadaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NoExisteLaVentaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NoExisteLaViandaException e)
				{
					System.out.println(e.getMessage());
				}
				catch (RemoteException e)
				{
					System.out.println(e.getMessage());
				}


				try{
					System.out.println("  Agrego vindas que no existe a venta");
					miFachada.agregarViandaAVenta("a69" , 10, 3);
				}
				catch(NoPuedoAgregarMasViandasException e)
				{
					System.out.println(e.getMessage());
				}
				catch(VentaFinalizadaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NoExisteLaVentaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NoExisteLaViandaException e)
				{
					System.out.println(e.getMessage());
				}
				catch (RemoteException e)
				{
					System.out.println(e.getMessage());
				}






				/////////////////////Confirmo una venta con viandas /////////////////////
				System.out.println("\n\n\n****Confirmo una venta con viandas****");

				try{
					System.out.println("  Confirmo la tercera venta  comentario");
					miFachada.finalizarVentaEnProceso(3, confirmo);
				}
				catch(VentaFinalizadaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NoExisteLaVentaException e)
				{
					System.out.println(e.getMessage());
				}
				catch (RemoteException e)
				{
					System.out.println(e.getMessage());
				}

				try{
					System.out.println("  Confirmo nuevamanete la tercera venta");
					miFachada.finalizarVentaEnProceso(3, confirmo);

				}
				catch(VentaFinalizadaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NoExisteLaVentaException e)
				{
					System.out.println(e.getMessage());
				}
				catch (RemoteException e)
				{
					System.out.println(e.getMessage());
				}


				/////////////////////Agrego una vianda a una venta ya finalizada /////////////////////
				System.out.println("\n\n\n****Agrego una vianda a la venta 3 que ya esta finalizada****");
				try{
					System.out.println("  Agrego vindas a venta finalizada");
					miFachada.agregarViandaAVenta(cod3 , 1, 3);
				}
				catch(NoPuedoAgregarMasViandasException e)
				{
					System.out.println(e.getMessage());
				}
				catch(VentaFinalizadaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NoExisteLaVentaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NoExisteLaViandaException e)
				{
					System.out.println(e.getMessage());
				}
				catch (RemoteException e)
				{
					System.out.println(e.getMessage());
				}



				/////////////////////elimino vianda de venta/////////////////////
				System.out.println("\n\n\n****Elimino vianda de una venta****");

				try{
					System.out.println("  Elimino 15 viandas de la venta 3, la misma esta finalizada");
					miFachada.eliminarViandaAVenta(cod1, 10, 3);
				}
				catch(VentaFinalizadaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NoExisteLaVentaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NoExisteLaViandaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NoExisteLaViandaEnPedidoVentaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(CantidadAEliminarINcorrectaException e)
				{
					System.out.println(e.getMessage());
				}
				catch (RemoteException e)
				{
					System.out.println(e.getMessage());
				}

				try{
					System.out.println("  Elimino 15 viandas de la venta 50, no existe la venta");
					miFachada.eliminarViandaAVenta(cod1, 10, 50);
				}
				catch(VentaFinalizadaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NoExisteLaVentaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NoExisteLaViandaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NoExisteLaViandaEnPedidoVentaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(CantidadAEliminarINcorrectaException e)
				{
					System.out.println(e.getMessage());
				}
				catch (RemoteException e)
				{
					System.out.println(e.getMessage());
				}

				try{
					System.out.println("  Elimino 15 viandas de la venta 1, no existe la vianda");
					miFachada.eliminarViandaAVenta("a69", 10, 1);
				}
				catch(VentaFinalizadaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NoExisteLaVentaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NoExisteLaViandaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NoExisteLaViandaEnPedidoVentaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(CantidadAEliminarINcorrectaException e)
				{
					System.out.println(e.getMessage());
				}
				catch (RemoteException e)
				{
					System.out.println(e.getMessage());
				}


				try{
					System.out.println("  Elimino 15 viandas de la venta 1, no existe la vianda para esa venta en particular");
					miFachada.eliminarViandaAVenta(cod5, 10, 1);
				}
				catch(VentaFinalizadaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NoExisteLaVentaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NoExisteLaViandaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NoExisteLaViandaEnPedidoVentaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(CantidadAEliminarINcorrectaException e)
				{
					System.out.println(e.getMessage());
				}
				catch (RemoteException e)
				{
					System.out.println(e.getMessage());
				}

				try{
					System.out.println("  Elimino 20 viandas a1 de la venta 1, la cantidad a eliminar es incorrecta");
					miFachada.eliminarViandaAVenta(cod1, 20, 1);
				}
				catch(VentaFinalizadaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NoExisteLaVentaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NoExisteLaViandaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NoExisteLaViandaEnPedidoVentaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(CantidadAEliminarINcorrectaException e)
				{
					System.out.println(e.getMessage());
				}
				catch (RemoteException e)
				{
					System.out.println(e.getMessage());
				}

				try{
					System.out.println("  Elimino 9 viandas de la venta 1");
					miFachada.eliminarViandaAVenta(cod1, 9, 1);
				}
				catch(VentaFinalizadaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NoExisteLaVentaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NoExisteLaViandaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NoExisteLaViandaEnPedidoVentaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(CantidadAEliminarINcorrectaException e)
				{
					System.out.println(e.getMessage());
				}
				catch (RemoteException e)
				{
					System.out.println(e.getMessage());
				}





				//////////////////////Confirmo como cancelada una venta con viandas/////////////////////
				System.out.println("\n\n\n****Confirmo como cancelada una venta con viandas****");

				try{
					System.out.println("  Confirmo la quinta venta");
					miFachada.finalizarVentaEnProceso(4, false);
				}
				catch(VentaFinalizadaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NoExisteLaVentaException e)
				{
					System.out.println(e.getMessage());
				}
				catch (RemoteException e)
				{
					System.out.println(e.getMessage());
				}





				/////////////////////Muestro todas las viandas  /////////////////////
				try{
					System.out.println("\n\n\n****Listado de todas las viandas del sistema luego de realizar operaciones****");
					ArrayList<VOTipoVianda> misViandas= miFachada.listadoGeneralDeViandas();
					for(int i=0;i<misViandas.size();i++)
					{ 
						System.out.println("--> codigo:"+misViandas.get(i).getCodigoAlfanumerico());
						System.out.println("    descripcion:"+misViandas.get(i).getDescripcion());
						System.out.println("    precio:"+misViandas.get(i).getPrecioUnitario());
						if(misViandas.get(i).getEsVegetariana())
						{
							System.out.println("Soy vegetariana"); 
						}
						else {
							System.out.println("Soy vegetariana"); 
						}
					}

				}
				catch (RemoteException e)
				{
					System.out.println(e.getMessage());
				}




				/////////////////////Muestro todas las ventas  /////////////////////
				try{
					System.out.println("\n\n\n****Listado de todas las ventas del sistema****");
					ArrayList<VOVenta> misventas= miFachada.listadoGeneralDeVentas();
					for(int i=0;i<misventas.size();i++)
					{ 
						System.out.println("--> codigo:"+misventas.get(i).getNumero());
						System.out.println("    fecha y hora:"+misventas.get(i).getFechaYHora());
						System.out.println("    direccion:"+misventas.get(i).getDireccion());
						System.out.println("    importe:"+misventas.get(i).getMontoTotal());
						if(misventas.get(i).getFinalizado())
						{
							System.out.println("estoy confirmada"); 
						}
						else {
							System.out.println("estoy pendiente"); 
						}
					}
				}
				catch (RemoteException e)
				{
					System.out.println(e.getMessage());
				}



				/////////////////////Muestro una vinda en detalle  /////////////////////
				System.out.println("\n\n\n****Listo una vianda en particular en detalle****");
				try{
					System.out.println("  Muestro una vianda que existe en el sistema");
					VOVianda miVianda= miFachada.listadoDetalladoDeVianda(cod5);
					System.out.println("--> codigo:"+miVianda.getCodigoAlfanumerico());
					System.out.println("    descripcion:"+miVianda.getDescripcion());
					System.out.println("    precio:"+miVianda.getPrecioUnitario());
				}
				catch (RemoteException e)
				{
					System.out.println(e.getMessage());
				}
				catch (NoExisteLaViandaException e)
				{
					System.out.println(e.getMessage());
				}
				try{
					System.out.println("  Muestro una vianda que no existe en el sistema");
					VOVianda miVianda= miFachada.listadoDetalladoDeVianda("a69");
					System.out.println("--> codigo:"+miVianda.getCodigoAlfanumerico());
					System.out.println("    descripcion:"+miVianda.getDescripcion());
					System.out.println("    precio:"+miVianda.getPrecioUnitario());
				}
				catch (RemoteException e)
				{
					System.out.println(e.getMessage());
				}
				catch (NoExisteLaViandaException e)
				{
					System.out.println(e.getMessage());
				}



				/////////////////////Muestro una vinda en detalle si es vegetariana muestro atributos  /////////////////////
				System.out.println("\n\n\n****Listo una vianda que contenga  en su nombre algon los caracteres especificos****");
				try{
					System.out.println("  Muestro una viandas que tengan en su nombre la");
					ArrayList<VOTipoVianda> miTViandas= miFachada.listadoDeViandaDescripcion("la");
					for(int i=0;i<miTViandas.size();i++)
					{ 
						System.out.println("--> codigo:"+miTViandas.get(i).getCodigoAlfanumerico());
						System.out.println("    descripcion:"+miTViandas.get(i).getDescripcion());
						System.out.println("    precio:"+miTViandas.get(i).getPrecioUnitario());
						if(miTViandas.get(i).getEsVegetariana())
						{
							System.out.println("Soy vegetariana"); 
						}
						else {
							System.out.println("No soy vegetariana"); 
						}
					}
				}
				catch (RemoteException e)
				{
					System.out.println(e.getMessage());
				}



				/////////////////////Pruebas persistencia guardar/////////////////////
				System.out.println("\n\n\n****Guado todos los datos****");
				try{
					System.out.println("  Si tengo datos para guardar");
					miFachada.guardarCambios();
				}
				catch(PersistenciaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NoHayDatosQueGuardarException e)
				{
					System.out.println(e.getMessage());
				}
				catch (RemoteException e)
				{
					System.out.println(e.getMessage());
				}
			}
			else
			{
				/////////////////////Pruebas persistencia restaurar, para probar este punto hay q poner en false la variable cargaInicialConSave/////////////////////
				System.out.println("\n\n\n****restauro todos los datos****");
				try{
					miFachada.restaurarInformacion();

					System.out.println("voy a restaurar");
				}
				catch(PersistenciaException e)
				{
					System.out.println(e.getMessage());
				}
				catch(RemoteException e)
				{
					System.out.println(e.getMessage());



				}
				/////////////////////Muestro todas las viandas  /////////////////////
				try{
					System.out.println("\n\n\n****Listado de todas las viandas del sistema luego de realizar operaciones****");
					ArrayList<VOTipoVianda> misViandas= miFachada.listadoGeneralDeViandas();
					for(int i=0;i<misViandas.size();i++)
					{ 
						System.out.println("--> codigo:"+misViandas.get(i).getCodigoAlfanumerico());
						System.out.println("    descripcion:"+misViandas.get(i).getDescripcion());
						System.out.println("    precio:"+misViandas.get(i).getPrecioUnitario());
						if(misViandas.get(i).getEsVegetariana())
						{
							System.out.println("Soy vegetariana"); 
						}
						else {
							System.out.println("Soy vegetariana"); 
						}
					}

				}
				catch (RemoteException e)
				{
					System.out.println(e.getMessage());
				}




				/////////////////////Muestro todas las ventas  /////////////////////
				try{
					System.out.println("\n\n\n****Listado de todas las ventas del sistema****");
					ArrayList<VOVenta> misventas= miFachada.listadoGeneralDeVentas();
					for(int i=0;i<misventas.size();i++)
					{ 
						System.out.println("--> codigo:"+misventas.get(i).getNumero());
						System.out.println("    fecha y hora:"+misventas.get(i).getFechaYHora());
						System.out.println("    direccion:"+misventas.get(i).getDireccion());
						System.out.println("    importe:"+misventas.get(i).getMontoTotal());
						if(misventas.get(i).getFinalizado())
						{
							System.out.println("estoy confirmada"); 
						}
						else {
							System.out.println("estoy pendiente"); 
						}
					}
				}
				catch (RemoteException e)
				{
					System.out.println(e.getMessage());
				}



				/////////////////////Muestro una vinda en detalle  /////////////////////
				System.out.println("\n\n\n****Listo una vianda en particular en detalle****");
				try{
					System.out.println("  Muestro una vianda que existe en el sistema");
					VOVianda miVianda= miFachada.listadoDetalladoDeVianda("a5");
					System.out.println("--> codigo:"+miVianda.getCodigoAlfanumerico());
					System.out.println("    descripcion:"+miVianda.getDescripcion());
					System.out.println("    precio:"+miVianda.getPrecioUnitario());
				}
				catch (RemoteException e)
				{
					System.out.println(e.getMessage());
				}
				catch (NoExisteLaViandaException e)
				{
					System.out.println(e.getMessage());
				}
				try{
					System.out.println("  Muestro una vianda que no existe en el sistema");
					VOVianda miVianda= miFachada.listadoDetalladoDeVianda("a69");
					System.out.println("--> codigo:"+miVianda.getCodigoAlfanumerico());
					System.out.println("    descripcion:"+miVianda.getDescripcion());
					System.out.println("    precio:"+miVianda.getPrecioUnitario());
				}
				catch (RemoteException e)
				{
					System.out.println(e.getMessage());
				}
				catch (NoExisteLaViandaException e)
				{
					System.out.println(e.getMessage());
				}



				/////////////////////Muestro una vinda en detalle si es vegetariana muestro atributos  /////////////////////
				System.out.println("\n\n\n****Listo una vianda que contenga  en su nombre algon los caracteres especificos****");
				try{
					System.out.println("  Muestro una viandas que tengan en su nombre la");
					ArrayList<VOTipoVianda> miTViandas= miFachada.listadoDeViandaDescripcion("la");
					for(int i=0;i<miTViandas.size();i++)
					{ 
						System.out.println("--> codigo:"+miTViandas.get(i).getCodigoAlfanumerico());
						System.out.println("    descripcion:"+miTViandas.get(i).getDescripcion());
						System.out.println("    precio:"+miTViandas.get(i).getPrecioUnitario());
						if(miTViandas.get(i).getEsVegetariana())
						{
							System.out.println("Soy vegetariana"); 
						}
						else {
							System.out.println("No soy vegetariana"); 
						}
					}
				}
				catch (RemoteException e)
				{
					System.out.println(e.getMessage());
				}
			}
		}catch (Exception e) {System.out.println(e.getMessage());}
	}
*/	
} // fin de la clase
