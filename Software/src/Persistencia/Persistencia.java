package Persistencia;

import Logica.exceptions.*;
import Logica.valueobjects.VOFachadaPersistencia;
import java.util.Properties;
import java.io.*;

public class Persistencia {

	public Persistencia()
	{

	}

	public void respaldar (VOFachadaPersistencia VOfC,String nombreArchivo) throws PersistenciaException
	{
		String ruta;
		try
		{ 
			Properties p = new Properties();
			String nomArch = "config/config.properties";
			// Abro el archivo properties y leo los datos de configuraci�n
			p.load (new FileInputStream (nomArch));
			if (System.getProperty("os.name").startsWith("Windows")) {
				ruta = p.getProperty("ruta1");
			} else {
				ruta = p.getProperty("ruta2");
			} 
			nombreArchivo= ruta + nombreArchivo;
		}
		catch (IOException e)
		{ e.printStackTrace(); }
		try
		{
			//Abro el archivo y creo un flujo de comunicaci�n hacia �l
			FileOutputStream f = new FileOutputStream(nombreArchivo);
			ObjectOutputStream o = new ObjectOutputStream(f);
			//Escribo el archivo a trav�s del flujo
			o.writeObject (VOfC);
			o.close();
			f.close();
		}
		catch (IOException e)
		{ e.printStackTrace();
		throw new PersistenciaException();
		}
	}

	public VOFachadaPersistencia restaurar (String nombreArchivo) throws PersistenciaException
	{
		String ruta ;
		try
		{ 
			Properties p = new Properties();
			String nomArch = "config/config.properties";
			// Abro el archivo properties y leo los datos de configuraci�n
			p.load (new FileInputStream (nomArch));
			if (System.getProperty("os.name").startsWith("Windows")) {
				ruta = p.getProperty("ruta1");
			} else {
				ruta = p.getProperty("ruta2");
			} 
			nombreArchivo= ruta + nombreArchivo;
		}
		catch (IOException e)
		{ e.printStackTrace(); }
		try
		{
			//Abro el archivo y creo un flujo de comunicaci�n hacia �l
			FileInputStream f = new FileInputStream(nombreArchivo);
			ObjectInputStream o = new ObjectInputStream(f);
			//Leo el archivo a trav�s del flujo
			VOFachadaPersistencia laFachadaDat = (VOFachadaPersistencia) o.readObject();
			o.close();
			f.close();
			return laFachadaDat;
		}
		catch (IOException e)
		{ 
			e.printStackTrace();
			throw new PersistenciaException("Error al recuperar");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenciaException("Error al recuperar");
		}
	}
}
