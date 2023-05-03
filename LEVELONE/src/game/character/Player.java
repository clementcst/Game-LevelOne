package game.character;

import game.direction.PlayerAnimation;
import game.inventory.Inventory;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;


public class Player {
   
	private int x_pos = 2;
	private int y_pos = 2;
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
        this.sprite.setFitWidth(32);
        this.sprite.setFitHeight(32);
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
	
	public int getX() {
		return this.x_pos;
	}

	public int getY() {
		return this.y_pos;
	}
	
	public void setX(int X) {
		this.x_pos = X;
	}
	
	public void setY(int Y) {
		this.y_pos = Y;
	}
	
    public void moveLeft(GridPane gridpaneObstacle,GridPane gridpaneInteract) {
    	//for(int i = 0; i < 10; i++) {
    		getSprite().setX(getSprite().getX() - 10);
        	if(PlayerCollision.testCollision(this,gridpaneObstacle)) {
        		getSprite().setX(getSprite().getX() + 10);
        		System.out.println("colisiongauxheq");
        	}
        	if(PlayerCollision.testCollision(this,gridpaneInteract)) {
        		/*System.out.println("AIE LE PERSONNAGE DE STAN M'A FAIT MAL !");*/
        	}
    	//}
    }
    
	public void move(GridPane gridpaneObstacle, GridPane gridpaneInteract, String direction) {
		if(direction.equals("Right")) {
			PlayerAnimation.animateCharacterMovement(gridpaneObstacle, getSprite(), direction, 2, 2, getX() + 1, getY());
			this.setX(getX()+1);
		}
		if(direction.equals("Left")) {
			PlayerAnimation.animateCharacterMovement(gridpaneObstacle, getSprite(), direction, 2, 2, getX() - 1, getY());
			this.setX(getX()-1);
		}
		if(direction.equals("Up")) {
			PlayerAnimation.animateCharacterMovement(gridpaneObstacle, getSprite(), direction, 2, 2, getX(), getY() - 1);
			this.setY(getY()-1);
		}
		if(direction.equals("Down")) {
			PlayerAnimation.animateCharacterMovement(gridpaneObstacle, getSprite(), direction, 2, 2, getX(), getY() + 1);
			this.setY(getY()+1);
		}
		
		/*if(!PlayerCollision.testCollision(this,gridpaneObstacle)) {
			
			//getSprite().setX(getSprite().getX() + getSprite().getTranslateX());
			System.out.println(getSprite().getTranslateX());
			transition.setOnFinished(event -> {
				//getSprite().setX(getSprite().getX());
				double x = getSprite().getX() + transition.getToX();
	            getSprite().setX(x);
	            System.out.println(transition.getToX());
			});
			
		}*/
		/*
		if(PlayerCollision.testCollision(this,gridpaneObstacle)) {
    		System.out.println("droite colision");
    		getSprite().setLayoutX(oldBounds.getMinX());
    	}*/
		
		//check collisions
		/*for (Node obstacle : gridpaneObstacle.getChildren()) {
			if(obstacle.getBoundsInParent().intersects(newBounds)) {
				transition.stop();
				getSprite().setLayoutX(oldBounds.getMinX());
				getSprite().setLayoutY(oldBounds.getMinY());
				System.out.println("COLLISION");
			}
		}*/
	}
	
	public void moveTop(GridPane gridpaneObstacle,GridPane gridpaneInteract) {
		//for(int i = 0; i < 10; i++) {
    		getSprite().setY(getSprite().getY() - 10);
        	if(PlayerCollision.testCollision(this,gridpaneObstacle)) {
        		getSprite().setY(getSprite().getY() + 10);
        	}
        	if(PlayerCollision.testCollision(this,gridpaneInteract)) {
        		//System.out.println("AIE LE PERSONNAGE DE STAN M'A FAIT MAL !");*/

        	}
    	//}
	}
	
	public void moveBottom(GridPane gridpaneObstacle,GridPane gridpaneInteract) {
		//for(int i = 0; i < 10; i++) {
    		getSprite().setY(getSprite().getY() + 10);
        	if(PlayerCollision.testCollision(this,gridpaneObstacle)) {
        		getSprite().setY(getSprite().getY() - 10);
        	}
        	if(PlayerCollision.testCollision(this,gridpaneInteract)) {
        		/*System.out.println("AIE LE PERSONNAGE DE STAN M'A FAIT MAL !");*/
        	}
    	//}
	}
	
	
	public  int[] getBlockIn() {
		int tab[] = new int[2];
		tab[0] =(int) (this.getSprite().getX() / 32)+1;
		tab[1] =(int) (this.getSprite().getY() / 32)+1;
		return tab;
		
	}
    
    // Actions que le joueur peut effectuer
    public void attack() {
        // Code pour attaquer un ennemi
    }
/*
    public void useItem(Item item) {
        // Code pour utiliser un item
    }*/

    
}