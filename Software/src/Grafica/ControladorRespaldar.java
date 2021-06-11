package Grafica;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JOptionPane;
import Logica.IFachada;
import Logica.exceptions.NoHayDatosQueGuardarException;
import Logica.exceptions.PersistenciaException;
import Logica.valueobjects.VOInicioVenta;


public class ControladorRespaldar {

	private IFachada miFachada;
	private Respaldar miVentana;

	public  ControladorRespaldar (Respaldar miVentana) 
	{
		this.miVentana = miVentana;
		try {
			ConexionCliente conex = ConexionCliente.getInstancia();
			miFachada = conex.getIFachada();
		} 
		catch (RemoteException e1) {
			JOptionPane.showMessageDialog(null, "No se puede conectar con el servidor. Compruebe la conexion y vuelva a intentarlo.");	
		}
		catch (NotBoundException e1) {
			JOptionPane.showMessageDialog(null, e1);	
		}
	}

	public void guardarCambios () 
	{
		try {
			miFachada.guardarCambios();
			JOptionPane.showMessageDialog(null, "Datos guardados correctamente.");
		}
		catch(NoHayDatosQueGuardarException | RemoteException | PersistenciaException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}


