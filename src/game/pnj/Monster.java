package game.pnj;

import java.util.Random;

import game.character.Player;
import game.item.AbstractItem;
import game.map.Map;
import game.textures.Constants;
import game.textures.Texture;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
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
		 super(name, image);
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
		 this.randomM.getKeyFrames().add(new KeyFrame(Duration.seconds(2),event ->{   			
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
		int x = (int)this.imageView.getLayoutX();
		int y = (int)this.imageView.getLayoutY();
		map.getGridpanePnj().getChildren().remove(this.getImageView());
		System.out.println("X:"+x+"Y:"+y);
		map.getGridpaneInteract().add(this.getItemOnDeath().getImageView(), x/32, y/32);

	}

	//MOVEMENT TEST
	
	public static void movement(GridPane gameBoard, ImageView characterImageView, int startX, int startY, int endX, int endY) {
//	    // Load the animation frames for the character's movement
//	    Image[] animationFrames = new Image[8];
//	    for (int i = 0; i < animationFrames.length; i++) {
//	        animationFrames[i] = new Image("file:res/images/move" + direction + i + ".png");
//	    }

	    // Get the starting and ending positions of the character on the game board
	    Region startSquare = (Region) gameBoard.getChildren().get(startY + 22 * startX);
	    Region endSquare = (Region) gameBoard.getChildren().get(endY + 22 * endX);

	    // Set the starting position of the character
	    characterImageView.setX(startSquare.getLayoutX());
	    characterImageView.setY(startSquare.getLayoutY());

	    // Create a TranslateTransition to move the character to the ending position
	    TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), characterImageView);
	    translateTransition.setToX(endSquare.getLayoutX() - startSquare.getLayoutX());
	    translateTransition.setToY(endSquare.getLayoutY() - startSquare.getLayoutY());

	    // Create a Timeline to animate the character's movement frames
//	    Timeline animationTimeline = new Timeline();
//	    animationTimeline.setCycleCount(Animation.INDEFINITE);
//
//	    for (int i = 0; i < animationFrames.length; i++) {
//	        int frameIndex = i;
//	        KeyFrame keyFrame = new KeyFrame(Duration.millis(100 * (i + 1)), event -> {
//	            characterImageView.setImage(animationFrames[frameIndex]);
//	        });
//	        animationTimeline.getKeyFrames().add(keyFrame);
//	    }

	    // Avoids the animation from playing infinitely
//	    translateTransition.setOnFinished(event -> {
//	    	animationTimeline.stop();
//	    });
	    
	    // Play the TranslateTransition and Timeline animations simultaneously
	    translateTransition.play();
//	    animationTimeline.play();
	}
	
	//MOVEMENT TEST
	
	// Comportements des monstres
	 public boolean move(String direction, Map map) {
		 switch(direction) {
		 	case "UP" :
		 		if(!PnjCollision.testCollision(this, map.getGridpaneInteract(),0,-this.getSpeed(), map) && !PnjCollision.testCollision(this, map.getGridpaneObstacle(),0, -this.getSpeed(), map)) {
    				movement(map.getGridpanePnj(), this.getImageView(), this.getX(), this.getY(), this.getNewX(), this.getNewY()-1);
    				this.setNewY(this.getNewY()-1);
    				return true;
    			}
		 		return false;
		 	case "DOWN" :
		 		if(!PnjCollision.testCollision(this, map.getGridpaneInteract(),0,this.getSpeed(), map) && !PnjCollision.testCollision(this, map.getGridpaneObstacle(),0, this.getSpeed(), map)) {
		 			movement(map.getGridpanePnj(), this.getImageView(), this.getX(), this.getY(), this.getNewX(), this.getNewY()+1);
    				this.setNewY(this.getNewY()+1);
    				return true;
    			}
		 		return false;
		 	case "LEFT" :
		 		if(!PnjCollision.testCollision(this, map.getGridpaneInteract(),-this.getSpeed(),0, map) && !PnjCollision.testCollision(this, map.getGridpaneObstacle(),-this.getSpeed(),0, map)) {
		 			movement(map.getGridpanePnj(), this.getImageView(), this.getX(), this.getY(), this.getNewX()-1, this.getNewY());
    				this.setNewX(this.getNewX()-1);
    				return true;
    			}
		 		return false;
		 	case "RIGTH" :
		 		if(!PnjCollision.testCollision(this, map.getGridpaneInteract(),this.getSpeed(),0, map) && !PnjCollision.testCollision(this, map.getGridpaneObstacle(),this.getSpeed(),0, map)) {
		 			movement(map.getGridpanePnj(), this.getImageView(), this.getX(), this.getY(), this.getNewX()+1, this.getNewY());
    				this.setNewX(this.getNewX()+1);
    				return true;
    			}
		 		return false;
		 	default :
		 		return false;
		 }
		 
	 }
	 
	 

	public void randomMove(Map map) {
		this.playerInVision(map);
		 if(this.hasVision()) {
			 System.out.println("Je te vois");
			 return;
		 }
		 int direction;
		 Boolean hasMoved;
		 Random random = new Random();
		 direction = (int) random.nextInt(4);
		 switch (direction) {
		 case 0 :
			 hasMoved = this.move("UP", map);
			 if(!hasMoved)
				 randomMove(map);
			 break;
		 case 1 :
			 hasMoved = this.move("LEFT", map);
			 if(!hasMoved)
				 randomMove(map);
			 break;
		 case 2 :
			 hasMoved = this.move("RIGTH", map);
			 if(!hasMoved)
				 randomMove(map);
			 break;
		 case 3 :
			 hasMoved = this.move("DOWN", map);
			 if(!hasMoved)
				 randomMove(map);
			 break;
		default :
			this.randomMove(map);
			break;
		 }
		 
	 }
	 
	public void moveWithVision() {
			 
	}
	
	public void playerInVision(Map map) {
		Boolean hasMoved;
		this.setHasVision(false);
		Bounds playerBounds = map.getPlayer().getSprite().getBoundsInParent();
		Bounds monsterBounds = this.getImageView().getBoundsInParent();
		Bounds gaucheHaut = new BoundingBox(monsterBounds.getMinX()-30, monsterBounds.getMinY()-30, monsterBounds.getWidth(), monsterBounds.getHeight()+30);
		Bounds hautDroite = new BoundingBox(monsterBounds.getMinX(), monsterBounds.getMinY()-30, monsterBounds.getWidth()+30, monsterBounds.getHeight());
		Bounds droiteBas = new BoundingBox(monsterBounds.getMinX()+30, monsterBounds.getMinY(), monsterBounds.getWidth(), monsterBounds.getHeight()+30);
		Bounds basGauche = new BoundingBox(monsterBounds.getMinX()-30, monsterBounds.getMinY()+30, monsterBounds.getWidth()+30, monsterBounds.getHeight());
		Bounds gaucheHautPleineVision = new BoundingBox(monsterBounds.getMinX()-64, monsterBounds.getMinY()-64, 64, 94);
		Bounds hautDroitePleineVision = new BoundingBox(monsterBounds.getMinX(), monsterBounds.getMinY()-64, 94, 64);
		Bounds droiteBasPleineVision = new BoundingBox(monsterBounds.getMinX()+30, monsterBounds.getMinY(), 64, 94);
		Bounds basGauchePleineVision = new BoundingBox(monsterBounds.getMinX()-64, monsterBounds.getMinY()+30, 94, 64);
		
		if(!PnjCollision.testVision(gaucheHaut, map)) {
			if(playerBounds.intersects(gaucheHautPleineVision)) {
				this.setHasVision(true);
				hasMoved = this.move("LEFT", map);
				System.out.println("1");
				if(!hasMoved)
					hasMoved = this.move("UP", map);
				return;
			}
		}
		if(!PnjCollision.testVision(hautDroite, map)) {
			if(playerBounds.intersects(hautDroitePleineVision)) {
				this.setHasVision(true);
				hasMoved = this.move("UP", map);
				System.out.println("2");
				if(!hasMoved)
					hasMoved = this.move("RIGTH", map);
				return;
			}
		}
		if(!PnjCollision.testVision(droiteBas, map)) {
			if(playerBounds.intersects(droiteBasPleineVision)) {
				this.setHasVision(true);
				hasMoved = this.move("RIGTH", map);
				System.out.println("3");
				if(!hasMoved)
					hasMoved = this.move("DOWN", map);
				return;
			}
		}
		if(!PnjCollision.testVision(basGauche, map)) {
			if(playerBounds.intersects(basGauchePleineVision)) {
				this.setHasVision(true);
				hasMoved = this.move("DOWN", map);
				System.out.println("4");
				if(!hasMoved)
					hasMoved = this.move("LEFT", map);
				return;
			}
		}
		
	}
	 public void attack(Player player) {
	     // Code pour attaquer le joueur
	 }

 // ...
}