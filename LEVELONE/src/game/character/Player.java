package game.character;

import game.item.Item;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// Classe pour représenter le joueur
public class Player {
    private String name;
    private int health;
    private int money;
    private ImageView sprite; // L'image qui représente le joueur dans l'interface graphique

    public Player(String name, int health, int money, String spritePath) {
        this.name = name;
        this.health = health;
        this.money = money;
        Image image = new Image(spritePath);
        this.sprite = new ImageView(image);
    }

    // Getters et setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }


    public ImageView getSprite() {
        return sprite;
    }

    public void setSprite(String imagePath) {
        Image image = new Image(imagePath);
        sprite.setImage(image);
    }

    // Actions que le joueur peut effectuer

    public void attack() {
        // Code pour attaquer un ennemi
    }

    public void useItem(Item item) {
        // Code pour utiliser un item
    }

    // ...
}