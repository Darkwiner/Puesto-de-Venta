package Servidor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.Date;
import java.util.Properties;
import Logica.exceptions.*;

import Logica.Fachada;
import Logica.IFachada;

public class Servidor {

	public static void main (String [] args) throws RemoteException 
	{
		try
		{ 
			Properties p = new Properties();
			String nomArch = "config/config.properties";
			p.load (new FileInputStream (nomArch));
			String ip = p.getProperty("ipServidor");
			int puerto = Integer.parseInt(p.getProperty("puertoServidor"));
			//Pongo a correr el RMIregistry
			LocateRegistry.createRegistry(puerto);
			String ruta = "//"+ip+":"+puerto+"/fachada";
			//Instancio mi objeto remoto y lo publico
			Fachada miFachada = new Fachada();
			System.out.println ("Iniciando servidor. Espere por favor...");
			Naming.rebind(ruta, miFachada);
			System.out.println ("Servidor iniciado" );
		}
		catch (RemoteException e)
		{ 
			e.printStackTrace();
			throw new RemoteException (e.getMessage());
		}
		catch (MalformedURLException e)
		{ 
			e.printStackTrace(); 
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace(); 
		} 
		catch (IOException e) 
		{
			e.printStackTrace(); 
		}
	}
}
