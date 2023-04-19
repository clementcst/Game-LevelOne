package game.item;


public class Potion extends AbstractItem {
	private String name;
	private String path;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Potion(String name) {
		this.name = name;
		this.path = "file:res/inventory/"+name+".jpg";
	}
	
	public String getPath() {
		return path;
	}
	
	//combien de pv, quelle potion, etc
	
	
}
