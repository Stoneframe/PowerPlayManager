package model;

public class Player {

	private String name;
	private Side side;
	private Attributes attributes;
	
	public Player(String name, Side side, Attributes attributes) {
		this.name = name;
		this.side = side;
		this.attributes = attributes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Side getSide() {
		return side;
	}
	
	public void setSide(Side side) {
		this.side = side;
	}
	
	public Attributes getAttributes() {
		return attributes;
	}

	@Override
	public String toString() {
		return String.format(
			"%s (%s)\n%s",
			name,
			side,
			attributes);
	}
	
}
