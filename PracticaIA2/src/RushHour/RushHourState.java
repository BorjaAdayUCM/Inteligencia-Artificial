package RushHour;

import java.util.ArrayList;
import java.util.Arrays;

import aima.core.agent.Action;

public class RushHourState {

	private int tam;
	private ArrayList<Vehiculo> vehiculos;
	private Vehiculo[][] board;
	
	public RushHourState()
	{
		this.tam = 6;
		this.vehiculos = new ArrayList<Vehiculo>();
		this.board = new Vehiculo[this.tam][this.tam];
		Vehiculo v1 = new Vehiculo(1, 3, 2, Vehiculo.Direction.HORIZONTAL, 'a');
		Vehiculo v2 = new Vehiculo(0, 4, 2, Vehiculo.Direction.VERTICAL, 'b');
		Vehiculo v3 = new Vehiculo(2, 4, 2, Vehiculo.Direction.VERTICAL, 'c');
		Vehiculo v4 = new Vehiculo(0, 5, 3, Vehiculo.Direction.VERTICAL, 'd');
		Vehiculo v5 = new Vehiculo(3, 2, 3, Vehiculo.Direction.VERTICAL, 'e');
		Vehiculo v6 = new Vehiculo(4, 3, 2, Vehiculo.Direction.VERTICAL, 'f');
		Vehiculo v7 = new Vehiculo(4, 5, 2, Vehiculo.Direction.HORIZONTAL, 'g');
		Vehiculo v8 = new Vehiculo(2, 3, 2, Vehiculo.Direction.HORIZONTAL, 'r');
		this.vehiculos.add(v1);
		this.vehiculos.add(v2);
		this.vehiculos.add(v3);
		this.vehiculos.add(v4);
		this.vehiculos.add(v5);
		this.vehiculos.add(v6);
		this.vehiculos.add(v7);
		this.vehiculos.add(v8);
		this.encode(this.tam, this.vehiculos);
	}

	public RushHourState(int tam, ArrayList<Vehiculo> listaVehiculos) {
		this.tam = tam;
		this.vehiculos = listaVehiculos;
		this.board = new Vehiculo[this.tam][this.tam];
		this.encode(this.tam, this.vehiculos);
	}

	public RushHourState(RushHourState tablero) 
	{
		this.tam = tablero.getTam();
		this.vehiculos = new ArrayList<Vehiculo>();
		this.board = new Vehiculo[this.tam][this.tam];
		for(Vehiculo v: tablero.vehiculos){
			Vehiculo vadd = new Vehiculo(v.getPosition().getX(), v.getPosition().getY(), v.getLength(), v.getDirection(), v.getName());
			this.vehiculos.add(vadd);
		}
		this.encode(this.tam, this.vehiculos);
	}
	
	public void encode(int tam, ArrayList<Vehiculo> listaVehiculos) {
		for(int i = 0; i < tam ; i++)
			for(int j = 0; j < tam; j++)
				this.board[i][j] = null;
		for(Vehiculo v : listaVehiculos)
		{
			if (v.getDirection() == Vehiculo.Direction.HORIZONTAL) {
				for (int i = 0; i < v.getLength(); i++) {
					board[v.getPosition().getX()][v.getPosition().getY() - i] = v;
				}
			}
			else if (v.getDirection() == Vehiculo.Direction.VERTICAL) {
				for (int i = 0; i < v.getLength(); i++) {
					board[v.getPosition().getX() + i][v.getPosition().getY()] = v;
				}
			}
		}
	}

	public int getTam() {
		return tam;
	}

	public Vehiculo[][] getBoard() {
		return board;
	}
	
	public ArrayList<Vehiculo> getVehiculos() {
		return vehiculos;
	}
	
	public Vehiculo searchVehiculo(char nombre) {
		for(Vehiculo v : this.vehiculos) {
			if (v.getName() == nombre) return v;
		}
		return null;
	}
	
	public void move(Action a) {
		if(this.movePossible(a)) {
			String action = a.toString();
			String parts[] = action.split(" ");
			Vehiculo vehiculo = this.searchVehiculo(parts[1].charAt(0));
			int desplazamiento = Integer.parseInt(parts[2].substring(0, parts[2].length() - 1));
			vehiculo.move(desplazamiento);
			this.encode(this.tam, this.vehiculos);
		}
	}

	public boolean movePossible (Action a) {
		String action = a.toString();
		String parts[] = action.split(" ");
		Vehiculo vehiculo = this.searchVehiculo(parts[1].charAt(0));
		int desplazamiento = Integer.parseInt(parts[2].substring(0, parts[2].length() - 1));
		if (vehiculo.getDirection() == Vehiculo.Direction.HORIZONTAL)
		{
			if (desplazamiento > 0) {
				if (vehiculo.getPosition().getY() + desplazamiento >= this.tam) return false;
				for(int i = 1; i <= desplazamiento; i++) {
					if (this.board[vehiculo.getPosition().getX()][vehiculo.getPosition().getY() + i] != null) return false;
				}
				return true;
			}
			else if (desplazamiento < 0)
				if (vehiculo.getPosition().getY() + desplazamiento - (vehiculo.getLength() - 1) < 0) return false;
				for(int i = -1; i >= desplazamiento; i--) {
					if (this.board[vehiculo.getPosition().getX()][vehiculo.getPosition().getY() + i - (vehiculo.getLength() - 1)] != null) return false;
				}
				return true;
		}
		else
		{
			if (desplazamiento > 0) {
				if (vehiculo.getPosition().getX() - desplazamiento < 0) return false;
				for(int i = 1; i <= desplazamiento; i++) {
					if (this.board[vehiculo.getPosition().getX() - i][vehiculo.getPosition().getY()] != null) return false;
				}
				return true;
			}
			else if (desplazamiento < 0)
				if (vehiculo.getPosition().getX() - desplazamiento + (vehiculo.getLength() - 1) >= this.tam) return false;
				for(int i = -1; i >= desplazamiento; i--) {
					if (this.board[vehiculo.getPosition().getX() - i + (vehiculo.getLength() - 1)][vehiculo.getPosition().getY()] != null) return false;
				}
				return true;
		}
	}
	
	@Override
	public String toString() 
	{
		int cellSize = 7;
		String space = " ";
		String vDelimitier = "|";
		String hDelimitier = "-";		
		String board = "";
		for (int row = 0; row < this.tam; row++)
		{
			board += space + MyStringUtils.repeat(hDelimitier, cellSize * this.tam + this.tam - 1) + space + '\n';
			for (int col = 0; col < this.tam; col++)
			{
				String numero = String.valueOf(this.board[row][col]);
				if (this.board[row][col] == null)
				{
					board += vDelimitier + MyStringUtils.repeat(space, cellSize);
				}
				else
				{
					board += vDelimitier + MyStringUtils.centre(numero, cellSize);
				}
			}
			board += vDelimitier + '\n';
		}
		board += space + MyStringUtils.repeat(hDelimitier, cellSize * this.tam + this.tam - 1);
		return board;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(board);
		result = prime * result + tam;
		result = prime * result + ((vehiculos == null) ? 0 : vehiculos.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object state) {
		if (this == state) return true;
		if (state == null || this.getClass() != state.getClass()) return false;
		RushHourState estado = (RushHourState) state;
		if (this.tam != estado.tam) return false;
		for(int i = 0; i < this.tam; i++)
		{
			for(int j = 0; j < this.tam; j++)
			{
				if (this.board[i][j] == null && estado.board[i][j] != null) return false; 
				else if (this.board[i][j] != null && estado.board[i][j] == null) return false;
				else if (this.board[i][j] == null && estado.board[i][j] == null);
				else if (!this.board[i][j].equals(estado.board[i][j])) return false;
			}
		}
		return true;
	}


}
