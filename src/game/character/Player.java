package game.character;

import game.inventory.Inventory;
import game.item.Weapon;
import game.map.Map;
import game.pnj.Monster;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.util.Duration;



public class Player {
   
	private double x;
	private double y;
	
	private String name;
    private int health;
    private int money;
    private Weapon weapon;
    private ImageView sprite;
    private Inventory inventory;
    private boolean isAttacking = false;
    
    private Timeline animationRight;
    private Timeline animationUp;
    private Timeline animationLeft;
    private Timeline animationDown;
    
    private Timeline animationAttackRight;
    private Timeline animationAttackLeft;

    public Player(String name, int health, int money, String spritePath,Map map) {
    	
    	this.animationRight = PlayerAnimation.animateCharacterMovement(this, "RIGHT", map);
    	this.animationUp = PlayerAnimation.animateCharacterMovement(this, "UP", map);
    	this.animationLeft = PlayerAnimation.animateCharacterMovement(this, "LEFT", map);
    	this.animationDown = PlayerAnimation.animateCharacterMovement(this, "DOWN", map);
    	
    	this.animationAttackRight = PlayerAnimation.animateCharacterAttack(this, "RIGHT", map);
    	this.animationAttackLeft = PlayerAnimation.animateCharacterAttack(this, "LEFT", map);

        this.name = name;
        this.health = health;
        this.money = money;
        Image image = new Image(spritePath);
        this.sprite = new ImageView(image);
        this.sprite.setFitWidth(40);
        this.sprite.setFitHeight(40);
        this.x = 70;
        this.y = 70;
        this.inventory = new Inventory();
        this.weapon = new Weapon("épée basique", 1, "Permet de taper","equip weapon");   
    }

    // Getters et setters

    public Timeline getAnimationAttackRight() {
		return animationAttackRight;
	}

	public void setAnimationAttackRight(Timeline animationAttackRight) {
		this.animationAttackRight = animationAttackRight;
	}

	public Timeline getAnimationAttackLeft() {
		return animationAttackLeft;
	}

	public void setAnimationAttackLeft(Timeline animationAttackLeft) {
		this.animationAttackLeft = animationAttackLeft;
	}

	public Timeline getAnimationRight() {
		return animationRight;
	}

	public void setAnimationRight(Timeline animationRight) {
		this.animationRight = animationRight;
	}

	public Timeline getAnimationUp() {
		return animationUp;
	}

	public void setAnimationUp(Timeline animationUp) {
		this.animationUp = animationUp;
	}

	public Timeline getAnimationLeft() {
		return animationLeft;
	}

	public void setAnimationLeft(Timeline animationLeft) {
		this.animationLeft = animationLeft;
	}

	public Timeline getAnimationDown() {
		return animationDown;
	}

	public void setAnimationDown(Timeline animationDown) {
		this.animationDown = animationDown;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health, Map map) {
        this.health = health;
        map.majLife();
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

	
	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}
	
	public void setX(double X) {
		this.x = X;
	}
	
	public void setY(double Y) {
		this.y = Y;
	}
	
	public void stopAnimation() {
		this.getAnimationDown().stop();
		this.getAnimationUp().stop();
		this.getAnimationLeft().stop();
		this.getAnimationRight().stop();
	}

    // Actions que le joueur peut effectuer
    public void attack(Map map) {
    	Image playerImage = this.getSprite().getImage();
        String playerImagePath = playerImage.getUrl();
        //Switch selon le nom de l'img (via l'URL)
        if(playerImagePath.substring(16).contains("moveR") || playerImagePath.substring(16).contains("moveU")) {
        	this.getAnimationAttackRight().play();
        }
        if(playerImagePath.substring(16).contains("moveL") || playerImagePath.substring(16).contains("moveD")) {
        	this.getAnimationAttackLeft().play();
        }
    	Monster m = PlayerCollision.attackCollision(this, map.getGridpanePnj());
    	if(m != null) {
    		System.out.println("on attaque le monstre "+m.getName());
			m.setHealth(m.getHealth() - this.getWeapon().getDamage());
			System.out.println("PV:"+m.getHealth());
			if(m.getHealth()<=0) { //Si le monstre n'a plus de vie, on le supprime
				m.drop(map);
//				map.getGridpanePnj().getChildren().remove(m.getImageView());
				//map.getGridpanePnj().add(new ImageView(new Image("file:res/images/diamond.png")),4,7);
			}else {
				Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200), event -> {
				    m.getImageView().setVisible(!m.getImageView().isVisible());
				}));
				timeline.setCycleCount(Timeline.INDEFINITE);
				
				PauseTransition pause = new PauseTransition(Duration.seconds(1));
				pause.setOnFinished(event -> {
				    timeline.stop();
				    m.getImageView().setVisible(true);
				});
				timeline.play();
				pause.play();
			}
    	}
    } 
}