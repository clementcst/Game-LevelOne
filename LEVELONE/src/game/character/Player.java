package game.character;

import game.inventory.Inventory;
import game.item.Item;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;


public class Player {
   

	private String name;
    private int health;
    private int money;
    private ImageView sprite;
    private Inventory inventory;

    public Player(String name, int health, int money, String spritePath) {
        this.name = name;
        this.health = health;
        this.money = money;
        Image image = new Image(spritePath);
        this.sprite = new ImageView(image);
        this.sprite.setFitWidth(40);
        this.sprite.setFitHeight(40);
        this.inventory = new Inventory();
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
    
    public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public void setSprite(ImageView sprite) {
		this.sprite = sprite;
	}

    public void moveLeft(GridPane gridpaneObstacle,GridPane gridpaneInteract) {
    	for(int i = 0; i < 10; i++) {
    		getSprite().setX(getSprite().getX() - 1);
        	if(PlayerCollision.testCollision(this,gridpaneObstacle)) {
        		getSprite().setX(getSprite().getX() + 1);
        	}
        	if(PlayerCollision.testCollision(this,gridpaneInteract)) {
        		System.out.println("AIE LE PERSONNAGE DE STAN M'A FAIT MAL !");
        		//setHealth(getHealth()-1);
        	}
    	}
    }
    
	public void moveRight(GridPane gridpaneObstacle,GridPane gridpaneInteract) {
		for(int i = 0; i < 10; i++) {
    		getSprite().setX(getSprite().getX() + 1);
        	if(PlayerCollision.testCollision(this,gridpaneObstacle)) {
        		getSprite().setX(getSprite().getX() - 1);
        	}
        	if(PlayerCollision.testCollision(this,gridpaneInteract)) {
        		System.out.println("AIE LE PERSONNAGE DE STAN M'A FAIT MAL !");
        		//setHealth(getHealth()-1);
        	}
    	}
	}
	
	public void moveTop(GridPane gridpaneObstacle,GridPane gridpaneInteract) {
		for(int i = 0; i < 10; i++) {
    		getSprite().setY(getSprite().getY() - 1);
        	if(PlayerCollision.testCollision(this,gridpaneObstacle)) {
        		getSprite().setY(getSprite().getY() + 1);
        	}
        	if(PlayerCollision.testCollision(this,gridpaneInteract)) {
        		System.out.println("AIE LE PERSONNAGE DE STAN M'A FAIT MAL !");
        		//setHealth(getHealth()-1);
        	}
    	}
	}
	
	public void moveBottom(GridPane gridpaneObstacle,GridPane gridpaneInteract) {
		for(int i = 0; i < 10; i++) {
    		getSprite().setY(getSprite().getY() + 1);
        	if(PlayerCollision.testCollision(this,gridpaneObstacle)) {
        		getSprite().setY(getSprite().getY() - 1);
        	}
        	if(PlayerCollision.testCollision(this,gridpaneInteract)) {
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