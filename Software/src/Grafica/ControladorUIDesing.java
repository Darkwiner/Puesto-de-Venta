package Grafica;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JOptionPane;
import Logica.IFachada;
import Logica.valueobjects.VOInicioVenta;


public class ControladorUIDesing {

	private IFachada miFachada;
	private UIDesign miVentana;

	public  ControladorUIDesing (UIDesign miVentana) throws RemoteException, NotBoundException
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

	public void restaurarInformacion () 
	{
		try {
			miFachada.restaurarInformacion();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}


