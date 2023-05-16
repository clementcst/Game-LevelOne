package game.pnj;

import game.character.Player;
import game.map.Map;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class PnjCollision {
	public static boolean testCollision(Monster monster,GridPane gridpane,double diffX, double diffY, Map map) {		
			Player player = map.getPlayer();
			monster.getImageView().setLayoutX(monster.getImageView().getLayoutX()+diffX*32);
			monster.getImageView().setLayoutY(monster.getImageView().getLayoutY()+diffY*32);
	
		    Bounds monsterBounds = monster.getImageView().getBoundsInParent();
		     
		    monster.getImageView().setLayoutX(monster.getImageView().getLayoutX()-diffX*32);
		    monster.getImageView().setLayoutY(monster.getImageView().getLayoutY()-diffY*32);
			for (Node obstacle : gridpane.getChildren()) {
				
				    //Vérifier si le joueur est en collision avec l'obstacle
					 if (obstacle instanceof ImageView ) {
			 
						 //creation des bounds de l'obstacle et du player our verif les collisions
						 Bounds obstacleBounds = obstacle.getBoundsInParent();
	
					    //s'il y a collision
					    if (obstacleBounds.intersects(monsterBounds)) {
					    	
					    	
					    	//recuperation de l'url de l'image dde l'obstacle 
					    	Image obstacleImage = ((ImageView) obstacle).getImage();
					        String obstacleImagePath = obstacleImage.getUrl();
					        
					        //Switch selon le nom de l'img (via l'URL)
					        switch(obstacleImagePath.substring(16)) {
					        case "diamond.png":   
								return false;
					        case "épée.png":   
								return false;
					        case "potionBlue.png":
								return false;
					        case "potionRed.png":
								return false;
					        case "key.png":
								return false;
					        default:
					        	
					        break;
					        }
					    	return true;
					    }
					 }
				}
	
		     Bounds playerBounds = player.getSprite().getBoundsInParent();
		     if (playerBounds.intersects(monsterBounds)) {
		    	 System.out.println("bug");
		    	 if(player.CanBeHurt()) {
		    		 monster.attack(player, map);
		    		 player.takingDamage();
		    	 }		    	
		    	 return true;
		     }
			return false;
	}
	
	public static boolean testVision(Bounds bound, Map map) {	
		for (Node obstacle : map.getGridpaneObstacle().getChildren()) {
			
			 if (obstacle instanceof ImageView ) { // On vérifie si le joueur est collé à un mur, si c est le cas alors il ne pourra pas voir à travers
				 Bounds obstacleBounds = obstacle.getBoundsInParent();
				 if (obstacleBounds.intersects(bound)) {
					 return true;
				 }
			 }
		}
		return false;
	}
}

