package game.item;


public class Weapon extends AbstractItem{
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Weapon(String name) {
		this.name = name;
	}
}
