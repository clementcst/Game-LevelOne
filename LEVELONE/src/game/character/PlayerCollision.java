package game.character;

import game.textures.Constants;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class PlayerCollision {

public static boolean testCollision(Player player,GridPane gridpane) {		
		
		for (Node obstacle : gridpane.getChildren()) {
		    // Vérifier si le joueur est en collision avec l'obstacle
			 if (obstacle instanceof ImageView) {
	 
				Bounds obstacleBounds = obstacle.localToScene(obstacle.getBoundsInLocal());
			    Bounds playerBounds = player.getSprite().localToScene(player.getSprite().getBoundsInLocal());
			    if (obstacleBounds.intersects(playerBounds)) {
			    	
			    	 if(((ImageView) obstacle).getImage().equals(Constants.door.getImage())){
						 System.out.println("c'est une porte");
					 }else{
						 System.out.println("collision détecté");
					 }
			    	
			    	
			    	return true;
			    }
			 }
		}
		System.out.println("pas de collision detecté");
		return false;
	}
	
	
}
