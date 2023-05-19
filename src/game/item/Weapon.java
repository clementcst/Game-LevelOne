package game.item;


public class Weapon extends Items{
	private int damage;
    public Weapon(String name, int damage, String description, String btnText) {
       super(name, description,btnText);
       this.damage = damage;
    }

    public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}


}
