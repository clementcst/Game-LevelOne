package game.item;


public class Weapon extends AbstractItem{
    public Weapon(String name) {
        super(name);
    }

    @Override
    public String getDescription() {
        return "oui";
    }
}
