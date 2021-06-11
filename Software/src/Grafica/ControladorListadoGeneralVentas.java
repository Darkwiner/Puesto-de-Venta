package Grafica;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import Logica.IFachada;
import Logica.Vegetariana;
import Logica.exceptions.NoExistenVentasException;
import Logica.valueobjects.VOVegetariana;
import Logica.valueobjects.VOVenta;


public class ControladorListadoGeneralVentas {

	private IFachada miFachada;
	private ListadoGeneralVentas miVentana;
	
	public  ControladorListadoGeneralVentas (ListadoGeneralVentas miVentana)
	{
		this.miVentana = miVentana;
		try {
			ConexionCliente conex = ConexionCliente.getInstancia();
			miFachada = conex.getIFachada();
		} catch (RemoteException | NotBoundException e1) {
			JOptionPane.showMessageDialog(null, "No se puede conectar con el servidor. Compruebe la conexion y vuelva a intentarlo.");	
		}
	}
	
	// Cargo el Listado General de Ventas
	public ArrayList<VOVenta> listadoGeneralDeVentas ()
	{
		ArrayList<VOVenta>  misVentas=null;
		try {
			misVentas=miFachada.listadoGeneralDeVentas();
		}catch(NoExistenVentasException | RemoteException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return misVentas;
	}
}
