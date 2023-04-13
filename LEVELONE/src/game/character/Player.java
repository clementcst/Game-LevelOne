package game.character;

import game.item.Item;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;


public class Player {
    private String name;
    private int health;
    private int money;
    private ImageView sprite;

    public Player(String name, int health, int money, String spritePath) {
        this.name = name;
        this.health = health;
        this.money = money;
        Image image = new Image(spritePath);
        this.sprite = new ImageView(image);
        this.sprite.setFitWidth(40);
        this.sprite.setFitHeight(40);
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

    public boolean testCollision(GridPane gridpane) {		
		
		for (Node obstacle : gridpane.getChildren()) {
		    // Vérifier si le joueur est en collision avec l'obstacle
			 if (obstacle instanceof ImageView) {
				Bounds obstacleBounds = obstacle.localToScene(obstacle.getBoundsInLocal());
			    Bounds playerBounds = getSprite().localToScene(getSprite().getBoundsInLocal());
			    if (obstacleBounds.intersects(playerBounds)) {
			    	System.out.println("collision détecté");
			    	return true;
			    }
			 }
		}
		System.out.println("pas de collision detecté");
		return false;
	}
    
    // Actions que le joueur peut effectuer
    public void attack() {
        // Code pour attaquer un ennemi
    }

    public void useItem(Item item) {
        // Code pour utiliser un item
    }

    
}