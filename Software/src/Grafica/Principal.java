package Grafica;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

public class Principal {
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		boolean err=false;
		try{
			ConexionCliente con = ConexionCliente.getInstancia();
		}catch (RemoteException e){
			JOptionPane.showMessageDialog(null, "No se puede conectar con el servidor. Compruebe la conexion y vuelva a intentarlo.");
			err=true;
		}
		if(!err){
			UIDesign window=new UIDesign();
			window.setVisible(true);
		}
	}
}
