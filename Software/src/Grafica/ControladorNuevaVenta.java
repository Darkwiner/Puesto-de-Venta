package Grafica;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JOptionPane;
import Logica.IFachada;
import Logica.exceptions.FechaIncorrectaException;
import Logica.valueobjects.VOInicioVenta;


public class ControladorNuevaVenta {

	private IFachada miFachada;
	private AltaNuevaVenta miVentana;

	public  ControladorNuevaVenta (AltaNuevaVenta miVentana) 
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

	public void altaNuevaVenta (VOInicioVenta miVenta) 
	{
		try {
			miFachada.iniciarNuevaVenta(miVenta);
			JOptionPane.showMessageDialog(null, "Venta ingresada correctamente.");
		}catch(FechaIncorrectaException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());;
		}catch(RemoteException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());;
		}
	}
}


