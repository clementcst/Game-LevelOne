package game.pnj;

import java.util.Random;

import game.character.Player;
import game.item.AbstractItem;
import game.map.Map;
import game.popUp.ActionEndGame;
import game.textures.Constants;
import game.textures.Texture;
import javafx.animation.PauseTransition;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.util.Duration;

//Classe pour représenter les monstres
public class Monster extends AbstractPnj{
 private int health;
 private int strength;
 private int speed;
 private boolean hasVision;
 private ImageView imageView;
 private AbstractItem itemOnDeath;
 private Timeline randomM;
 private int x;
 private int y;
 private int newX;
 private int newY;

	 public Monster(String name, int health, int strength, boolean hasVision, Texture image, AbstractItem drop, Map map, int x, int y) {
		 super(name, image, true);
		 this.health = health;
		 this.strength = strength;				 
		 this.hasVision = hasVision;
		 this.speed = 30;
		 this.imageView = image.getImageView();
		 this.imageView.setFitHeight(30);
		 this.imageView.setFitWidth(30);
		 this.itemOnDeath = drop;
		 this.imageView.setUserData(this);
		 
		 this.x = x;
		 this.y = y;
		 this.newX = x;
		 this.newY = y;
		 this.randomM = new Timeline();
		 this.randomM.getKeyFrames().add(new KeyFrame(Duration.seconds(1),event ->{   			
			 this.randomMove(map);
		 }));
		 this.randomM.setCycleCount(Timeline.INDEFINITE);
	}
	 

	public Timeline getRandomM() {
		return randomM;
	}


	public void setRandomM(Timeline randomM) {
		this.randomM = randomM;
	}


	public ImageView getImageView() {
			return imageView;
	}


	public void setImageView(ImageView imageView) {
			this.imageView = imageView;
	}


	 public int getHealth() {
	     return health;
	 }
	
	 public int getNewX() {
		return newX;
	}


	public void setNewX(int newX) {
		this.newX = newX;
	}


	public int getNewY() {
		return newY;
	}


	public void setNewY(int newY) {
		this.newY = newY;
	}


	public void setHealth(int health) {
	     this.health = health;
	 }
	
	 public int getStrength() {
	     return strength;
	 }
	
	 public int getSpeed() {
		return speed;
	}


	public void setSpeed(int speed) {
		this.speed = speed;
	}


	public void setStrength(int strength) {
	     this.strength = strength;
	 }
	
	 public boolean hasVision() {
	     return hasVision;
	 }
	
	 public void setHasVision(boolean hasVision) {
	     this.hasVision = hasVision;
	 }
	
	 public AbstractItem getItemOnDeath() {
		return itemOnDeath;
	}

	public void setItemOnDeath(AbstractItem itemOnDeath) {
		this.itemOnDeath = itemOnDeath;
	}
	
	 
	 public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void drop(Map map) {
		int x = (int)this.getNewX();
		int y = (int)this.getNewY();
		this.randomM.stop();
		map.getGridpanePnj().getChildren().remove(this.getImageView());
		map.getTableauDeMonstres().remove(this);
		map.getGridpaneInteract().add(this.getItemOnDeath().getImageView(), x, y);
	}
	
	public void takingDamage() {
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200), event -> {
		    this.getImageView().setVisible(!this.getImageView().isVisible());
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		this.setCanBeHurt(false);
		PauseTransition pause = new PauseTransition(Duration.seconds(1));
		pause.setOnFinished(event -> {
		    timeline.stop();
		    this.getImageView().setVisible(true);
		    this.setCanBeHurt(true);
		});
		timeline.play();
		pause.play();
	}
	
	 // Comportements des monstres
	 public boolean move(String direction, Map map) {
	    boolean hasMoved = false;

	    switch(direction) {
	        case "UP":
	            hasMoved = this.moveIfPossible(map, 0, -1, "Up");
	            break;
	        case "DOWN":
	            hasMoved = this.moveIfPossible(map, 0, 1, "Down");
	            break;
	        case "LEFT":
	            hasMoved = this.moveIfPossible(map, -1, 0, "Left");
	            break;
	        case "RIGHT":
	            hasMoved = this.moveIfPossible(map, 1, 0, "Right");
	            break;
	    }

	    return hasMoved;
	}
	 
	 private boolean moveIfPossible(Map map, int newX, int newY, String direction) {
		    if (!PnjCollision.testCollision(this, map.getGridpaneInteract(), newX, newY, map, direction) && !PnjCollision.testCollision(this, map.getGridpaneObstacle(), newX, newY, map, direction)) {
		    	MonsterAnimation.animatedMove(map.getGridpanePnj(), this.getImageView(), direction, this.getX(), this.getY(), this.getNewX() + newX, this.getNewY() + newY);
		        this.setNewX(this.getNewX() + newX);
		        this.setNewY(this.getNewY() + newY);
		        return true;
		    }
		    return false;
	}

	 public void randomMove(Map map) {
		    this.playerInVision(map);
		    if (this.hasVision()) {
		        return;
		    }

		    boolean hasMoved;
		    int direction;
		    Random random = new Random();
		    direction = random.nextInt(4);

		    switch (direction) {
		        case 0:
		        	hasMoved = this.moveIfPossible(map, 0, -1, "Up");
		            break;
		        case 1:
		        	hasMoved = this.moveIfPossible(map, -1, 0, "Left");
		            break;
		        case 2:
		        	hasMoved = this.moveIfPossible(map, 1, 0, "Right");
		            break;
		        case 3:
		        	hasMoved = this.moveIfPossible(map, 0, 1, "Down");
		            break;
		        default :
		        	hasMoved = false;
		        	break;
		    }
		    if(!hasMoved) {
		    	randomMove(map);
		    }
	}
	
	private boolean checkVisionAndMoveIfPossible(double sortX, double sortY, double shortWidth, double sortHeight, double longX, double longY, double longWidth, double longHeight, Bounds playerBounds, String direction, Map map) {
	    if (!PnjCollision.testVision(new BoundingBox(sortX,sortY,shortWidth,sortHeight), map) && playerBounds.intersects(longX, longY, longWidth, longHeight)) {
	        this.setHasVision(true);
	        return this.move(direction, map);
	    }
	    return false;
	}
	 
	public void moveWithVision() {
			 
	}
	
	public void playerInVision(Map map) {
	    this.setHasVision(false);
	    Bounds playerBounds = map.getPlayer().getSprite().getBoundsInParent();
	    Bounds monsterBounds = this.getImageView().getBoundsInParent();

	    if (!checkVisionAndMoveIfPossible(monsterBounds.getMinX() - 30, monsterBounds.getMinY() - 30,monsterBounds.getWidth(),monsterBounds.getHeight()+30,monsterBounds.getMinX()-64,monsterBounds.getMinY()-64, 64, 94, playerBounds, "LEFT", map)) {
	        if (!checkVisionAndMoveIfPossible(monsterBounds.getMinX(), monsterBounds.getMinY() - 30,monsterBounds.getWidth()+30,monsterBounds.getHeight(),monsterBounds.getMinX(), monsterBounds.getMinY()-64, 94, 64, playerBounds, "UP", map)) {
	            if (!checkVisionAndMoveIfPossible(monsterBounds.getMinX() + 30, monsterBounds.getMinY(), monsterBounds.getWidth(), monsterBounds.getHeight()+30,monsterBounds.getMinX()+30, monsterBounds.getMinY(), 64, 94, playerBounds, "RIGHT", map)) {
	                checkVisionAndMoveIfPossible(monsterBounds.getMinX() - 30, monsterBounds.getMinY() + 30,monsterBounds.getWidth()+30, monsterBounds.getHeight(),monsterBounds.getMinX()-64,monsterBounds.getMinY()+30,  94, 64, playerBounds, "DOWN", map);
	            }
	        }
	    }
	}
	
	 public void attack(Player player, Map map, String direction) {
		 MonsterAnimation.animatedAttack(this.getImageView(), direction);
		 player.setHealth(player.getHealth()-this.getStrength(), map);
		 if(player.getHealth() <= 0) {
			 //FIN
		 }
	 }

}
