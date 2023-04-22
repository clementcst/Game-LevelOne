package game.character;

import game.item.AbstractItem;
import game.item.Potion;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

	public class PlayerCollision {

		public static Node getNodeFromBounds(Bounds bounds, GridPane gridpane) {
			for (Node nodes : gridpane.getChildren()) {
				if(nodes.getBoundsInParent().intersects(bounds)) {
					return nodes;
				}
			}
		return null;
	}
		
	
	public static boolean testCollision(Player player,GridPane gridpane) {		
			
		for (Node obstacle : gridpane.getChildren()) {
			
			    //Vérifier si le joueur est en collision avec l'obstacle
				 if (obstacle instanceof ImageView) {
		 
					Bounds obstacleBounds = obstacle.localToScene(obstacle.getBoundsInLocal());
				    Bounds playerBounds = player.getSprite().localToScene(player.getSprite().getBoundsInLocal());

				    
				    if (obstacleBounds.intersects(playerBounds)) {
				    		    	
				    	Image obstacleImage = ((ImageView) obstacle).getImage();
				        String obstacleImagePath = obstacleImage.getUrl();

				        switch(obstacleImagePath.substring(16)) {
				        case "diamond.png":
				        	 Node collidingNode = getNodeFromBounds(obstacleBounds, gridpane);
					         gridpane.getChildren().remove(collidingNode);
					         player.getInventory().push((AbstractItem) new Potion("diamond"));
				        break;
				        default:
				        	
				        break;
				        }
				        
				    	return true;
				    }
				 }
			}
			return false;
		}
	
	
}
