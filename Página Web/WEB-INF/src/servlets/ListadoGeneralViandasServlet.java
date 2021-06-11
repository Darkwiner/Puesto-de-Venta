package servlets;


import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Logica.IFachada;
import Logica.exceptions.NoExistenViandasException;
import Logica.valueobjects.VOTipoVianda;
import ValueObjetsMostrar.VOListados;

//para cargar con archivo config
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class ListadoGeneralViandasServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		//Valido los datos ingresados
		boolean error = false;
		String msgError = new String();

		//Guardo los datos en la sesión
		if (!error)
		{		

			String ip = super.getInitParameter("ipServidor");
			String ruta = "";
			String puerto= super.getInitParameter("puertoServidor");
			try 
			{
				ruta = "//"+ip+":"+puerto+"/fachada";
				IFachada miFachada = (IFachada) Naming.lookup(ruta);
				ArrayList<VOTipoVianda> listaViandasGeneral0 = miFachada.listadoGeneralDeViandas();
				VOListados a=new VOListados();
				ArrayList<VOListados> listaViandasGeneral=a.cambiarTipo(listaViandasGeneral0);
				req.setAttribute("listaViandas", listaViandasGeneral);
			} catch (MalformedURLException e) {
				error = true;
				msgError = "URL mal Formada";
			} catch (NotBoundException e) {
				error = true;
				msgError = "Error en conexion con el servidor";
			}catch (RemoteException e) {
				error = true;
				msgError = "Error en conexion con el servidor";
			}
			catch (NoExistenViandasException e) {
				error = true;
				msgError=e.getMessage();
			}
		}



		// Forwardeo a la página apropiada
		RequestDispatcher rd;
		if (!error)
		{
			rd = req.getRequestDispatcher("ListadoGeneral.jsp");
		}
		else
		{
			req.setAttribute("msgError", msgError);
			rd = req.getRequestDispatcher("Error.jsp");
		}
		rd.forward(req, resp);
	}
}
