package game.item;

public class Potion extends AbstractItem{
    public Potion(String name, String description) {
        super(name, description);
    }

    @Override
    public String getDescription() {
        return "oui";
    }
}

