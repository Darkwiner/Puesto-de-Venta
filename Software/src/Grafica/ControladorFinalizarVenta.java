package Grafica;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JOptionPane;
import Logica.IFachada;
import Logica.exceptions.NoExisteLaVentaException;
import Logica.exceptions.VentaFinalizadaException;
import Logica.valueobjects.VOInicioVenta;


public class ControladorFinalizarVenta {

	private IFachada miFachada;
	private FinalizarVenta miVentana;
	private static ControladorFinalizarVenta instancia;

	public  ControladorFinalizarVenta (FinalizarVenta miVentana) 
	{
		this.miVentana = miVentana;
		try {
			ConexionCliente conex = ConexionCliente.getInstancia();
			miFachada = conex.getIFachada();
		} 
		catch (RemoteException e1) {
			FinalizarVenta.getInstancia().mostrar("No se puede conectar con el servidor. Compruebe la conexion y vuelva a intentarlo.");	
		}
		catch (NotBoundException e1) {	
			FinalizarVenta.getInstancia().mostrar(e1.getMessage());
		}
	}
	
	public void finalizarVentaEnProceso (int idVenta, boolean estado) 
	{
		try {
			miFachada.finalizarVentaEnProceso(idVenta, estado);
		}catch(NoExisteLaVentaException e)
		{
			e.printStackTrace();
			FinalizarVenta.getInstancia().mostrar(e.getMessage());
		}
		catch(VentaFinalizadaException e)
		{
			e.printStackTrace();
			FinalizarVenta.getInstancia().mostrar(e.getMessage());
		}
		catch(RemoteException e)
		{
			e.printStackTrace();
			FinalizarVenta.getInstancia().mostrar(e.getMessage());
		}
	}
}


