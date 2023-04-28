package game.item;

public class Potion extends AbstractItem{
    public Potion(String name) {
        super(name);
    }

    @Override
    public String getDescription() {
        return "oui";
    }
}

