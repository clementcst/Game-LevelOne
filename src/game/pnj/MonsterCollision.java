package game.pnj;

import game.character.Player;
import game.map.Map;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class MonsterCollision {

public static boolean testCollision(Monster monster, Player player,GridPane gridpane,double diffX, double diffY, Map map) {		
		
		for (Node obstacle : gridpane.getChildren()) {
			
			    //Vérifier si le joueur est en collision avec l'obstacle
				 if (obstacle instanceof ImageView ) {
		 
					 //creation des bounds de l'obstacle et du player our verif les collisions
					 Bounds obstacleBounds = obstacle.getBoundsInParent();
					 
					 monster.getImageView().setLayoutX( monster.getImageView().getLayoutX()+diffX);
					 monster.getImageView().setLayoutY( monster.getImageView().getLayoutY()+diffY);

         		     Bounds playerBounds =  monster.getImageView().getBoundsInParent();
         		     
         		    monster.getImageView().setLayoutX(monster.getImageView().getLayoutX()-diffX);
         		    monster.getImageView().setLayoutY(monster.getImageView().getLayoutY()-diffY);

				    //s'il y a collision
				    if (obstacleBounds.intersects(playerBounds)) {	
				    	
				    	return true;
				    }
				 }
			}
			return false;
		}
	
	
	
	
}
