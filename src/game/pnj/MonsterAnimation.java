package game.pnj;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.util.Duration;

public class MonsterAnimation {
	
	public static void animatedMove(GridPane gameBoard, ImageView characterImageView, String direction, int startX, int startY, int endX, int endY) {
	    // Get the monster's type
		String monsterType = "pigMob";
		
		Image monsterImage = characterImageView.getImage();
        String monsterImagePath = monsterImage.getUrl();
        if(monsterImagePath.substring(16).contains("pigKing")) {
        	monsterType = "pigKing";
        }
		
        if(direction.equals("Up")) {
        	direction = "Left";
        }
        if(direction.equals("Down")) {
        	direction = "Right";
        }
        
		// Load the animation frames for the character's movement
	    Image[] animationFrames = new Image[6];
	    for (int i = 0; i < animationFrames.length; i++) {
	        animationFrames[i] = new Image("file:res/images/"+ monsterType + "Move" + direction + i + ".png");
	    }

	    // Get the starting and ending positions of the character on the game board
	    Region startSquare = (Region) gameBoard.getChildren().get(startY + 22 * startX);
	    Region endSquare = (Region) gameBoard.getChildren().get(endY + 22 * endX);

	    // Set the starting position of the character
	    characterImageView.setX(startSquare.getLayoutX());
	    characterImageView.setY(startSquare.getLayoutY());

	    // Create a TranslateTransition to move the character to the ending position
	    TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), characterImageView);
	    translateTransition.setToX(endSquare.getLayoutX()+1 - startSquare.getLayoutX());
	    translateTransition.setToY(endSquare.getLayoutY()+1 - startSquare.getLayoutY());

	    // Create a Timeline to animate the character's movement frames
	    Timeline animationTimeline = new Timeline();
	    animationTimeline.setCycleCount(Animation.INDEFINITE);

	    for (int i = 0; i < animationFrames.length; i++) {
	        int frameIndex = i;
	        KeyFrame keyFrame = new KeyFrame(Duration.millis(100 * (i + 1)), event -> {
	            characterImageView.setImage(animationFrames[frameIndex]);
	            characterImageView.setFitHeight(30);
	            characterImageView.setFitWidth(30);
	        });
	        animationTimeline.getKeyFrames().add(keyFrame);
	    }

	    // Avoids the animation from playing infinitely
	    translateTransition.setOnFinished(event -> {
	    	animationTimeline.stop();
	    });
	    
	    // Play the TranslateTransition and Timeline animations simultaneously
	    translateTransition.play();
	    animationTimeline.play();
	}

	public static void animatedAttack(ImageView characterImageView, String direction) {
	    // Get the monster's type
		String monsterType = "pigMob";
		
		Image monsterImage = characterImageView.getImage();
        String monsterImagePath = monsterImage.getUrl();
        if(monsterImagePath.substring(16).contains("pigKing")) {
        	monsterType = "pigKing";
        }
		
		// Load the animation frames for the character's movement
	    Image[] animationFrames = new Image[5];
	    for (int i = 0; i < animationFrames.length; i++) {
	        animationFrames[i] = new Image("file:res/images/"+ monsterType + "Attack" + direction + i + ".png");
	    }

	    // Create a Timeline to animate the character's movement frames
	    Timeline animationTimeline = new Timeline();
	    animationTimeline.setCycleCount(5);

	    for (int i = 0; i < animationFrames.length; i++) {
	        int frameIndex = i;
	        KeyFrame keyFrame = new KeyFrame(Duration.millis(100 * (i + 1)), event -> {
	            characterImageView.setImage(animationFrames[frameIndex]);
	            characterImageView.setFitHeight(30);
	            characterImageView.setFitWidth(30);
	        });
	        animationTimeline.getKeyFrames().add(keyFrame);
	    }
	    
	    // Play the TranslateTransition and Timeline animations simultaneously
	    animationTimeline.play();
	}
	
}
