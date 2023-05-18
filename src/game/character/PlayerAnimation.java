package game.character;

import game.map.Map;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class PlayerAnimation {
	
	

	public static Timeline animateCharacterMovement(Player player, String direction, Map map) {
	    
		// Load the animation frames for the character's movement
		 Image[] moveAnimation = new Image[8];
		    for (int i = 0; i < moveAnimation.length; i++) {
		    	moveAnimation[i] = new Image("file:res/images/move"+direction+i+".png");
		    }

		if(direction.equals("RIGHT")) {
					
			final Timeline animationTimeline = new Timeline();
			animationTimeline.getKeyFrames().add(
					new KeyFrame(Duration.millis(50),event ->{
			    			
			    			boolean collision = false;
			    			
			    			if(!player.isInvisible() && PlayerCollision.testCollision(map.getPlayer(), map.getGridpaneObstacle(),5,0, map)) {
			    				collision=true;
			    			}
			    		
			    			if(PlayerCollision.testCollision(map.getPlayer(), map.getGridpaneInteract(),5,0, map)) {
			    				collision=true;
			    				player.stopAnimation();
			    			}
			    			
			    			if(PlayerCollision.testCollision(map.getPlayer(), map.getGridpanePnj(),5,0, map)) {
			    				collision=true;
			    			}
			    			
			    			if(collision == false) {
			    				int etape = (int) ((System.currentTimeMillis() / 100) % 8);
							    double x = map.getPlayer().getSprite().getLayoutX() + map.getPlayer().getSpeed();
							    map.getPlayer().getSprite().setImage(moveAnimation[etape]);
							    map.getPlayer().getSprite().setLayoutX(x);
							    map.getPlayer().setX(x);
			    			}				    
			    		}));

			   animationTimeline.setCycleCount(Timeline.INDEFINITE);
			   return animationTimeline;

		}
		if(direction.equals("LEFT")) {
			
			
			final Timeline animationTimeline = new Timeline();
			animationTimeline.getKeyFrames().add(
					new KeyFrame(Duration.millis(50),event ->{
			    			
			    			boolean collision = false;
			    			
			    			if(!player.isInvisible() && PlayerCollision.testCollision(map.getPlayer(), map.getGridpaneObstacle(),-5,0, map)) {
			    				collision=true;   		
			    			}
			    		
			    			if(PlayerCollision.testCollision(map.getPlayer(), map.getGridpaneInteract(),-5,0, map)) {
			    				collision=true;
			    				player.stopAnimation();
			    			}
			    			
			    			if(PlayerCollision.testCollision(map.getPlayer(), map.getGridpanePnj(),-5,0, map)) {
			    				collision=true;
			    			}
			    			
			    			
			    			if(collision == false) {
			    				int etape = (int) ((System.currentTimeMillis() / 100) % 8);
							    double x = map.getPlayer().getSprite().getLayoutX() - map.getPlayer().getSpeed();
							    map.getPlayer().getSprite().setImage(moveAnimation[etape]);
							    map.getPlayer().getSprite().setLayoutX(x);
							    map.getPlayer().setX(x);
			    			}				    
			    		}));

			   animationTimeline.setCycleCount(Timeline.INDEFINITE);
			   return animationTimeline;
			
		}
		if(direction.equals("UP")) {
			
			final Timeline animationTimeline = new Timeline();
			animationTimeline.getKeyFrames().add(
					new KeyFrame(Duration.millis(50),event ->{
			    			
			    			boolean collision = false;
			    			
			    			if(!player.isInvisible() && PlayerCollision.testCollision(map.getPlayer(), map.getGridpaneObstacle(),0,-5, map)) {
			    				collision=true;
			    			}
			    		
			    			if(PlayerCollision.testCollision(map.getPlayer(), map.getGridpaneInteract(),0,-5, map)) {
			    				collision=true;
			    				player.stopAnimation();
			    			}
			    			
			    			if(PlayerCollision.testCollision(map.getPlayer(), map.getGridpanePnj(),0,-5, map)) {
			    				collision=true;
			    			}
			    		
			    			
			    			if(collision == false) {
			    				int etape = (int) ((System.currentTimeMillis() / 100) % 8);
							    double y = map.getPlayer().getSprite().getLayoutY() - map.getPlayer().getSpeed();
							    map.getPlayer().getSprite().setImage(moveAnimation[etape]);
							    map.getPlayer().getSprite().setLayoutY(y);
							    map.getPlayer().setX(y);
			    			}				    
			    		}));

			   animationTimeline.setCycleCount(Timeline.INDEFINITE);
			   return animationTimeline;
		}
		if(direction.equals("DOWN")) {
			
			
			final Timeline animationTimeline = new Timeline();
			animationTimeline.getKeyFrames().add(
					new KeyFrame(Duration.millis(50),event ->{
			    			
			    			boolean collision = false;
			    			
			    			if(!player.isInvisible() && PlayerCollision.testCollision(map.getPlayer(), map.getGridpaneObstacle(),0,5, map)) {
			    				collision=true;
			    			}
			    		
			    			if(PlayerCollision.testCollision(map.getPlayer(), map.getGridpaneInteract(),0,5, map)) {
			    				collision=true;
			    				player.stopAnimation();
			    			}
			    			
			    			if(PlayerCollision.testCollision(map.getPlayer(), map.getGridpanePnj(),0,5, map)) {
			    				collision=true;
			    			}

			    			
			    			if(collision == false) {
			    				int etape = (int) ((System.currentTimeMillis() / 100) % 8);
							    double y = map.getPlayer().getSprite().getLayoutY() + map.getPlayer().getSpeed();
							    map.getPlayer().getSprite().setImage(moveAnimation[etape]);
							    map.getPlayer().getSprite().setLayoutY(y);
							    map.getPlayer().setX(y);
			    			}				    
			    		}));

			   animationTimeline.setCycleCount(Timeline.INDEFINITE);
			   return animationTimeline;
		}
		
		return null;
	}
	
public static Timeline animateCharacterAttack(Player player, String direction, Map map) {
	    
		// Load the animation frames for the character's attack
		 Image[] attackAnimation = new Image[6];
		    for (int i = 0; i < attackAnimation.length; i++) {
		    	attackAnimation[i] = new Image("file:res/images/attack"+direction+i+".png");
		    }
		    
		final Timeline animationTimeline = new Timeline();
		animationTimeline.getKeyFrames().add( new KeyFrame(Duration.millis(50),event ->{	
			player.setAttackAnimation(true);
    		int etape = (int) ((System.currentTimeMillis() / 100) % 6);
    		map.getPlayer().getSprite().setScaleX(2.5);
    		map.getPlayer().getSprite().setScaleY(2.5);
		    map.getPlayer().getSprite().setImage(attackAnimation[etape]);
	   	}));
		
		animationTimeline.setOnFinished(event -> {
			map.getPlayer().getSprite().setScaleX(1);
			map.getPlayer().getSprite().setScaleY(1);
			map.getPlayer().getSprite().setImage(new Image("file:res/images/move"+direction+"0.png"));
			player.setAttackAnimation(false);
		});
		
		animationTimeline.setCycleCount(6);
	    return animationTimeline;
		
	}
	
	
}
