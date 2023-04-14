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
    //private Inventory inventory;

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
    
    public void moveLeft(GridPane gridpaneObstacle,GridPane gridpaneInteract) {
    	for(int i = 0; i < 10; i++) {
    		getSprite().setX(getSprite().getX() - 1);
        	if(testCollision(gridpaneObstacle)) {
        		getSprite().setX(getSprite().getX() + 1);
        	}
        	if(testCollision(gridpaneInteract)) {
        		System.out.println("AIE LE PERSONNAGE DE STAN M'A FAIT MAL !");
        		//setHealth(getHealth()-1);
        	}
    	}
    }
    
	public void moveRight(GridPane gridpaneObstacle,GridPane gridpaneInteract) {
		for(int i = 0; i < 10; i++) {
    		getSprite().setX(getSprite().getX() + 1);
        	if(testCollision(gridpaneObstacle)) {
        		getSprite().setX(getSprite().getX() - 1);
        	}
        	if(testCollision(gridpaneInteract)) {
        		System.out.println("AIE LE PERSONNAGE DE STAN M'A FAIT MAL !");
        		//setHealth(getHealth()-1);
        	}
    	}
	}
	
	public void moveTop(GridPane gridpaneObstacle,GridPane gridpaneInteract) {
		for(int i = 0; i < 10; i++) {
    		getSprite().setY(getSprite().getY() - 1);
        	if(testCollision(gridpaneObstacle)) {
        		getSprite().setY(getSprite().getY() + 1);
        	}
        	if(testCollision(gridpaneInteract)) {
        		System.out.println("AIE LE PERSONNAGE DE STAN M'A FAIT MAL !");
        		//setHealth(getHealth()-1);
        	}
    	}
	}
	
	public void moveBottom(GridPane gridpaneObstacle,GridPane gridpaneInteract) {
		for(int i = 0; i < 10; i++) {
    		getSprite().setY(getSprite().getY() + 1);
        	if(testCollision(gridpaneObstacle)) {
        		getSprite().setY(getSprite().getY() - 1);
        	}
        	if(testCollision(gridpaneInteract)) {
        		System.out.println("AIE LE PERSONNAGE DE STAN M'A FAIT MAL !");
        		//setHealth(getHealth()-1);
        	}
    	}
	}
    
    // Actions que le joueur peut effectuer
    public void attack() {
        // Code pour attaquer un ennemi
    }

    public void useItem(Item item) {
        // Code pour utiliser un item
    }

    
}