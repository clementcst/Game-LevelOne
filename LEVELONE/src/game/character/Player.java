package game.character;

import game.inventory.Inventory;
import game.item.Weapon;
import game.map.Map;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;


public class Player {
   

	private String name;
    private int health;
    private int money;
    private Weapon weapon;
    private ImageView sprite;
    private Inventory inventory;
    private boolean isAttacking = false;

    public Player(String name, int health, int money, String spritePath) {
        this.name = name;
        this.health = health;
        this.money = money;
        Image image = new Image(spritePath);
        this.sprite = new ImageView(image);
        this.sprite.setFitWidth(40);
        this.sprite.setFitHeight(40);
        this.inventory = new Inventory();
        this.weapon = new Weapon("épée basique", 1);
        
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

    public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
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

    public boolean isAttacking() {
		return isAttacking;
	}

	public void setAttacking(boolean isAttacking) {
		this.isAttacking = isAttacking;
	}

	public void moveLeft(GridPane gridpaneObstacle,GridPane gridpaneInteract) {
    	//for(int i = 0; i < 10; i++) {
    		getSprite().setX(getSprite().getX() - 10);
        	if(PlayerCollision.testCollision(this,gridpaneObstacle)) {
        		getSprite().setX(getSprite().getX() + 10);
        	}
        	if(PlayerCollision.testCollision(this,gridpaneInteract)) {
        		/*System.out.println("AIE LE PERSONNAGE DE STAN M'A FAIT MAL !");*/
        	}
    	//}
    }
    
	public void moveRight(GridPane gridpaneObstacle,GridPane gridpaneInteract) {
		//for(int i = 0; i < 10; i++) {
    		getSprite().setX(getSprite().getX() + 10);
        	if(PlayerCollision.testCollision(this,gridpaneObstacle)) {
        		getSprite().setX(getSprite().getX() - 10);
        	}
        	if(PlayerCollision.testCollision(this,gridpaneInteract)) {
        		/*System.out.println("AIE LE PERSONNAGE DE STAN M'A FAIT MAL !");*/
        	}
    	//}
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
    public void attack(Player player, GridPane gridpaneInteract, Map map) {
    	String qui = PlayerCollision.attackCollision(player, gridpaneInteract);
    	switch(qui) {
    		case "Monstre1":
    			int vieMonster = map.getMonster1().getHealth();
    			int damageWeapon = player.getWeapon().getDamage();
    			vieMonster = vieMonster - damageWeapon;
    			if(vieMonster<=0) { //Si le monstre n'a plus de vie, on le supprime
    				map.getGridpaneInteract().getChildren().remove(map.getMonster1().getImageView());
    				map.setMonster1(null);
    				
    			} else {
    				map.getMonster1().setHealth(vieMonster);
    				System.out.println(vieMonster);
    			}
    		case "nobody" : 
    			break;
			default :
				break;
    	}
    	System.out.println("attaque");
        // Code pour attaquer un ennemi
    }
/*
    public void useItem(Item item) {
        // Code pour utiliser un item
    }*/

    
}