package Grafica;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import Logica.IFachada;
import Logica.Vegetariana;
import Logica.valueobjects.VOTipoVianda;
import Logica.valueobjects.VOVegetariana;
import Logica.valueobjects.VOVenta;
import Logica.valueobjects.VOVianda;
import Logica.exceptions.LaVentaNoTienePedidosException;
import Logica.exceptions.NoExisteLaVentaException;
import Logica.exceptions.NoExisteLaViandaException;
import Logica.exceptions.NoExistenViandasException;



public class ControladorListadoGeneralViandas {

	private IFachada miFachada;
	private ListadoGeneralViandas miVentana;

	public  ControladorListadoGeneralViandas (ListadoGeneralViandas miVentana) 
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

	// Cargo el Listado General de Viandas
	public ArrayList<VOTipoVianda> ListadoGeneralViandas () 
	{
		ArrayList<VOTipoVianda>  misViandas=null;
		try {
			misViandas=miFachada.listadoGeneralDeViandas();
		}catch(NoExistenViandasException | RemoteException e1)
		{
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		return misViandas;
	}

	public VOVianda listadoDetalladoDeVianda (String cod) 
	{
		VOVianda  miVianda=null;
		try {
			miVianda=miFachada.listadoDetalladoDeVianda(cod);
		}catch(NoExisteLaViandaException | RemoteException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return miVianda;
	}

	public ArrayList<VOTipoVianda> listadoDeViandaDescripcion (String desc) 
	{
		ArrayList<VOTipoVianda>  misViandas=null;
		try {
			misViandas=miFachada.listadoDeViandaDescripcion(desc);
		}catch(NoExistenViandasException | RemoteException | NoExisteLaViandaException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return misViandas;
	}

	public ArrayList<VOTipoVianda> listadoViandasVenta (int numVenta) 
	{
		ArrayList <VOTipoVianda> viandasVenta = null;
		try {
			viandasVenta = miFachada.listadoViandasDeVenta(numVenta);
		}catch( LaVentaNoTienePedidosException | NoExisteLaVentaException | RemoteException  e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return viandasVenta;
	}


}
