package game.direction;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class PlayerAnimation {
	public static void animateCharacterMovement(GridPane gameBoard, ImageView characterImageView, String direction, int startX, int startY, int endX, int endY) {
	    // Load the animation frames for the character's movement
	    Image[] animationFrames = new Image[8];
	    for (int i = 0; i < animationFrames.length; i++) {
	        animationFrames[i] = new Image("file:res/images/move" + direction + i + ".png");
	    }

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
	    Timeline animationTimeline = new Timeline();
	    animationTimeline.setCycleCount(Animation.INDEFINITE);

	    for (int i = 0; i < animationFrames.length; i++) {
	        int frameIndex = i;
	        KeyFrame keyFrame = new KeyFrame(Duration.millis(100 * (i + 1)), event -> {
	            characterImageView.setImage(animationFrames[frameIndex]);
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
}
