package formulasIA;

public class Elemento {
	
	private int valor;
	private int operador;//0 = + , 1 = - 2 = * 3= /
	
	public Elemento (int valor, int operador)
	{
		this.valor=valor;
		this.operador=operador;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getOperador() {
		return operador;
	}

	public void setOperador(int operador) {
		this.operador = operador;
	}
}
