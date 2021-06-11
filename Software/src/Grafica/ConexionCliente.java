package Grafica;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Properties;
import javax.swing.JOptionPane;
import Logica.IFachada;

public class ConexionCliente {
	private static ConexionCliente instancia;
	private IFachada miFachada;

	private  ConexionCliente() throws RemoteException, NotBoundException {

		Properties p = new Properties();
		String nomArch = "config/config.properties";
		try {
			p.load(new FileInputStream(nomArch));
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error: Archivo no existe.");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error: Archivo no existe o no esta accesible.");
		}
		try {
			String ip = p.getProperty("ipServidor");
			int puerto = Integer.parseInt(p.getProperty("puertoServidor"));
			//Pongo a correr el RMIregistry
			String ruta = "//" + ip + ":" + puerto + "/fachada";
			//Instancio mi objeto remoto y lo publico
			 miFachada = (IFachada) Naming.lookup(ruta);
		} catch (MalformedURLException e) {
			JOptionPane.showMessageDialog(null, "Error: Archivo no existe o no esta accesible");
		}
	}

	public static ConexionCliente getInstancia() throws RemoteException, NotBoundException {
		if (instancia == null)
			instancia = new ConexionCliente();
		return instancia;
	}

	public IFachada getIFachada() {
		return miFachada;
	}

	public void establecerNaming() throws NotBoundException, FileNotFoundException, IOException {
		Properties p = new Properties();
		String nomArch = "config/config.properties";
		p.load(new FileInputStream(nomArch));
		String ip = p.getProperty("ipServidor");
		int puerto = Integer.parseInt(p.getProperty("puertoServidor"));
		//Pongo a correr el RMIregistry
		String ruta = "//" + ip + ":" + puerto + "/fachada";
		//Instancio mi objeto remoto y lo publico
		IFachada miFachada = (IFachada) Naming.lookup(ruta);
	}

}
