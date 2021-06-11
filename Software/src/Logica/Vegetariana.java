package Logica;
import java.io.Serializable;

public class Vegetariana extends Vianda implements Serializable{
	private boolean ovolacteas;
	private String descripcionAdicional;

	public Vegetariana ()
	{
		super();
	}

	public Vegetariana (String codigo, String descrip, float precioUni, boolean ovo, String descAdicional) 
	{
		super(codigo, descrip, precioUni); //Invoco al contructor por defecto
		this.ovolacteas = ovo;
		this.descripcionAdicional = descAdicional;
	}

	public boolean getOvolacteas ()
	{
		return this.ovolacteas;
	}
	public String getdescripci�nAdicional()
	{
		return this.descripcionAdicional;
	}

	public void setOvol�cteas (Boolean ovo)
	{
		this.ovolacteas = ovo;
	}

	public void setDescripci�nAdicional (String descAdicional)
	{
		this.descripcionAdicional = descAdicional;
	}

	public boolean esVegetariana()
	{
		return true;
	}
}

