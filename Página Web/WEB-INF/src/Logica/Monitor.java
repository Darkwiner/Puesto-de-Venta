package Logica;

import java.io.Serializable;

public class Monitor implements Serializable{
	private int cantLectores=0; ;
	private boolean escribiendo=false; 
	private static Monitor instancia; 
	
	public synchronized static Monitor getInstancia()
	{
		if (instancia == null)
		{
			instancia = new Monitor();
		}
		return instancia;
	}
	
	public void Monitor ()
	{
	}
	
	public synchronized   void comienzoLectura ()
	{
		while (escribiendo)
		{
			try
			{
				wait();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		cantLectores++; 
	}
	
	
	
	public synchronized  void terminoLectura ()
	{
		cantLectores--;
		notify();
	}
	
	public synchronized void comienzoEscritura ()
	{
		while (escribiendo || cantLectores > 0)
		{
			try
			{
				wait();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		escribiendo = true;
	}
	
	public synchronized void terminoEscritura ()
	{
		escribiendo = false;
		notify();
	}
}
