package formulasIA;

import java.util.ArrayList;

public class Representacion {

	private ArrayList<Elemento> lista;
	private int numObjetivo;
	private int MAX;
	
	public Representacion(int numObjetivo)
	{
		this.numObjetivo = numObjetivo;
		this.lista = new ArrayList<Elemento>();
		this.MAX= 6;
	}

	public int obtenerValor()
	{
		int valor=this.lista.get(0).getValor();
		
		for (int i=1; i< this.MAX;i++)
		{
			switch(lista.get(i-1).getOperador())
			{
				case 0: valor = valor + this.lista.get(i).getValor();break;
				case 1: valor = valor - this.lista.get(i).getValor();break;
				case 2: valor = valor * this.lista.get(i).getValor();break;
				case 3: valor = valor / this.lista.get(i).getValor();break;
			}
		}
		return valor;
	}
	
	public  int getNumObjetivo()
	{
		return this.numObjetivo;
	}
	
	public void insertarElemento(int i, Elemento elem)
	{
		this.lista.add(i,elem);
	}
	
	public String toString()
	{
		String cadena = "";
		cadena = Integer.toString(this.lista.get(0).getValor());
		for(int i=1; i<this.MAX;i++)
		{
			cadena = cadena + this.operacion(this.lista.get(i-1).getOperador()) + Integer.toString(this.lista.get(i).getValor());
		}
		return cadena;
	}
	
	private String operacion(int opp)
	{
		String simbolo="";
		switch(opp)
		{
			case 0: simbolo = " + ";break;
			case 1: simbolo = " - ";break;
			case 2: simbolo = " * ";break;
			case 3: simbolo = " / ";break;
		}
		return simbolo;
	}
}
