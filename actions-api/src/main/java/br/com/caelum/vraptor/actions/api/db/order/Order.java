package br.com.caelum.vraptor.actions.api.db.order;

public class Order {
	
	private String property;
	
	private Direction direction;
	
	public enum Direction {
		ASC, DESC
	}

	protected Order(String property, Direction direction) {
		this.property = property;
		this.direction = direction;
	}

	public static Order asc(String property) {
		return new Order(property, Direction.ASC);
	}

	public String property() {
		return property;
	}

	public Direction direction() {
		return direction;
	}

	public static Order desc(String property) {
		return new Order(property, Direction.DESC);
	}
	
	@Override
	public String toString() {
		return property + " " + direction;
	}	

}
