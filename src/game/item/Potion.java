package game.item;


public class Potion extends AbstractItem {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Potion(String name) {
		this.name = name;
	}
	
	//combien de pv, quelle potion, etc
	
	
}
