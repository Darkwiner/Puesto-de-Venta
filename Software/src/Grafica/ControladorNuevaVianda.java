package Grafica;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JOptionPane;
import Logica.IFachada;
import Logica.exceptions.YaExisteViandaException;
import Logica.valueobjects.VOVianda;


public class ControladorNuevaVianda {

	private IFachada miFachada;
	private AltaNuevaVianda miVentana;

	public  ControladorNuevaVianda (AltaNuevaVianda miVentana) 
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
			JOptionPane.showMessageDialog(null, "No se puede conectar con el servidor. Compruebe la conexion y vuelva a intentarlo.");	
		}
	}

	public void altaNuevaVianda (VOVianda miVianda) 
	{
		try {
			miFachada.altaNuevaVianda(miVianda);
			JOptionPane.showMessageDialog(null, "Vianda registrada correctamente.");
		}catch(YaExisteViandaException | RemoteException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}


