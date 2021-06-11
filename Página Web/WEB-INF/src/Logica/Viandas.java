package Logica;
import java.util.*;
import java.io.Serializable;
import Logica.valueobjects.VOTipoVianda;
import Logica.valueobjects.VOVianda;
import Logica.valueobjects.VOVegetariana;

public class Viandas implements Serializable{


	TreeMap <String,Vianda> viandas =new TreeMap<String,Vianda>(String.CASE_INSENSITIVE_ORDER);



	//Verifico que una vianda con el codigo alfanumerico est� en la lista
	public boolean member (String cod)
	{
		return this.viandas.containsKey(cod);
	}

	//Inserto una vianda en el arbol
	public void insertVianda(String cod, Vianda vi)
	{
		viandas.put(cod, vi);
	} 

	//Busco si existe una vianda con el codigo pasado por par�metro y lo devuelvo
	public Vianda find (String cod)
	{
		return viandas.get(cod);
	}

	//Verifico si el arbol es vac�o
	public boolean esVacio ()
	{
		return (viandas.isEmpty());
		//return this.viandas.size()==0;
		//return this.viandas==null;
	}

	//Devuelvo las viandas que tienen parte o la totalidad en su descripci�n, del texto pasado por parametro
	public ArrayList <VOTipoVianda> buscoPorDescripcion (String descrip) 
	{
		Iterator <Vianda> iter = viandas.values().iterator();
		ArrayList <VOTipoVianda> resultado = new ArrayList<VOTipoVianda>();
		while (iter.hasNext())
		{ 
			Vianda vi = iter.next();
			if(vi.contieneDescripcion(descrip))
			{
				VOTipoVianda voTipoVianda = new VOTipoVianda(vi.getCodigoAlfanumerico(),vi.getDescripci�n(),vi.getPrecio(), vi.esVegetariana());
				resultado.add(voTipoVianda);
			}
		}
		Collections.sort(resultado);
		return resultado;
	}

	/*Devolver listado de viandas ordenado por codigo alfanumerico de vianda, conteniendo c�digo, descripci�n, 
	 * precio unitario y tipo (com�n o vegetariana) de todas las viandas registradas en el sistema. */
	public ArrayList <VOTipoVianda> listadoGeneralDeViandas ()
	{
		Iterator <Vianda> iter = viandas.values().iterator();
		ArrayList <VOTipoVianda> resultado = new ArrayList<VOTipoVianda>();
		while (iter.hasNext())
		{ 
			Vianda vi = iter.next();
			VOTipoVianda voTipoVianda = new VOTipoVianda(vi.getCodigoAlfanumerico(),vi.getDescripci�n(),vi.getPrecio(), vi.esVegetariana());
			resultado.add(voTipoVianda);
		}
		return resultado;
	}

	/*Dado el codigo de una vianda devuelvo vianda com�n o vianda vegetariana*/
	public VOVianda devolverVOVianda(String cod) 
	{
		VOVianda miVOVianda;
		Vianda laVianda = this.find(cod);
		if (!laVianda.esVegetariana()) 
		{
			miVOVianda = new VOVianda(laVianda.getCodigoAlfanumerico(), laVianda.getDescripci�n(), laVianda.getPrecio());
		}
		else 
		{
			Vegetariana vege = new Vegetariana();
			vege = (Vegetariana) laVianda;
			miVOVianda = new VOVegetariana (vege.getCodigoAlfanumerico(), vege.getDescripci�n(), vege.getPrecio(), vege.esVegetariana(), vege.getOvolacteas(),vege.getdescripci�nAdicional());
		}
		return miVOVianda;
	}

	public void muestroViandas()
	{
		Iterator <Vianda> iter = viandas.values().iterator();

		while (iter.hasNext())
		{ 
			Vianda vi = iter.next();
			vi.muestroVianda();
		}
	}


}
