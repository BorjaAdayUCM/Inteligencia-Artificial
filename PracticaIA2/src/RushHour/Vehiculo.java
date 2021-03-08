package RushHour;

public class Vehiculo {
	
	public enum Direction {
		HORIZONTAL,
		VERTICAL
	}
	
	private char name;
	private Position position;
	private int length;
	private Direction direction;

	public Vehiculo(int x, int y, int len, Direction dir, char c) {
		this.position = new Position(x,y);
		this.length = len;
		this.direction = dir;
		this.name = c;
	}
	
	public void move(int desplazamiento) {
		if (direction == Direction.VERTICAL)
			this.position.setX(this.position.getX() - desplazamiento);
		else
			this.position.setY(this.position.getY() + desplazamiento);
	}
	
	public char getName() {
		return this.name;
	}
	
	public int getLength() {
		return this.length;
	}
	
	public Direction getDirection() {
		return this.direction;
	}
	
	public Position getPosition() {
		return this.position;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + length;
		result = prime * result + name;
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || this.getClass() != obj.getClass()) return false;
		Vehiculo other = (Vehiculo) obj;
		if (this.direction != other.direction || this.length != other.length || this.name != other.name || !this.position.equals(other.position)) return false;
		return true;
	}

	@Override
	public String toString() {
		return Character.toString(this.name);
	}
}

