package game.item;


public class Weapon extends AbstractItem{
	private int damage;
    public Weapon(String name, int damage, String description) {
       super(name, description);
       this.damage = damage;
    }

    public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	@Override
    public String getDescription() {
        return "oui";
    }
}
