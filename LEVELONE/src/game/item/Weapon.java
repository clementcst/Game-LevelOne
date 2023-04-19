package game.item;


public class Weapon extends AbstractItem{
	private String name;
	private String path;
	private String[] description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Weapon(String name) {
		this.name = name;
		this.path = "file:res/inventory/"+name+".jpg";
	}
	
	public String getPath() {
		return path;
	}
	
	public String getDescription() {
		return "oui";
	}
}
