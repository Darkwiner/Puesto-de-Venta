package Grafica;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import Logica.IFachada;
import Logica.Vegetariana;
import Logica.exceptions.CantidadAEliminarINcorrectaException;
import Logica.exceptions.NoExisteLaVentaException;
import Logica.exceptions.NoExisteLaViandaEnPedidoVentaException;
import Logica.exceptions.NoExisteLaViandaException;
import Logica.exceptions.NoExistenVentasException;
import Logica.exceptions.NoExistenViandasException;
import Logica.exceptions.NoPuedoAgregarMasViandasException;
import Logica.exceptions.VentaFinalizadaException;
import Logica.valueobjects.VOTipoVianda;
import Logica.valueobjects.VOVegetariana;
import Logica.valueobjects.VOVenta;


public class ControladorModificarVenta {

	private IFachada miFachada;
	private ModificarVenta miVentana;

	public  ControladorModificarVenta (ModificarVenta miVentana) 
	{
		//this.miFachada = ConexionCliente.getIFachada();
		this.miVentana = miVentana;
		try {
			ConexionCliente conex = ConexionCliente.getInstancia();
			miFachada = conex.getIFachada();
		} catch (RemoteException | NotBoundException e1) {
			JOptionPane.showMessageDialog(null, "No se puede conectar con el servidor. Compruebe la conexion y vuelva a intentarlo.");	
		}
	}

	public ArrayList<VOVenta> listadoGeneralDeVentas () 
	{
		ArrayList<VOVenta>  misVentas=null;
		try {
			misVentas=miFachada.listadoGeneralDeVentas();
		}catch(NoExistenVentasException| RemoteException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return misVentas;
	}

	public ArrayList<VOTipoVianda> listadoGeneralDeViandas () 
	{
		ArrayList<VOTipoVianda>  misViandas=null;
		try {
			misViandas=miFachada.listadoGeneralDeViandas();
		}catch(NoExistenViandasException | RemoteException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return misViandas;
	}

	public void  agregarViandaAVenta(String codVianda,int cantidad, int codVenta) 
	{
		try {
			miFachada.agregarViandaAVenta(codVianda, cantidad, codVenta);
			JOptionPane.showMessageDialog(null, "Operacion realizada");
		}catch(NoPuedoAgregarMasViandasException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		catch(VentaFinalizadaException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		catch(NoExisteLaVentaException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		catch(NoExisteLaViandaException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		catch(RemoteException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void  eliminarViandaAVenta(String codVianda,int cantidad, int codVenta)
	{
		try {
			miFachada.eliminarViandaAVenta(codVianda, cantidad, codVenta);
		} catch (RemoteException | VentaFinalizadaException | NoExisteLaVentaException
				| NoExisteLaViandaException | NoExisteLaViandaEnPedidoVentaException
				| CantidadAEliminarINcorrectaException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}


