package game.character;

import java.util.ArrayList;
import java.util.List;

import game.item.AbstractItem;
import game.item.Potion;
import game.map.Map;
import game.pnj.Monster;
import game.popUp.ActionChoice;
import game.popUp.ActionEndGame;
import game.popUp.ActionOnDoor;
import game.textures.Constants;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

	public class PlayerCollision {

	
	public static boolean testCollision(Player player,GridPane gridpane,double diffX, double diffY, Map map) {		
		
		for (Node obstacle : gridpane.getChildren()) {
			
			    //Vérifier si le joueur est en collision avec l'obstacle
				 if (obstacle instanceof ImageView ) {
		 
					 //creation des bounds de l'obstacle et du player our verif les collisions
					 Bounds obstacleBounds = obstacle.getBoundsInParent();
					 
					 player.getSprite().setLayoutX(player.getSprite().getLayoutX()+diffX);
					 player.getSprite().setLayoutY(player.getSprite().getLayoutY()+diffY);

         		     Bounds playerBounds = player.getSprite().getBoundsInParent();
         		     
         		     player.getSprite().setLayoutX(player.getSprite().getLayoutX()-diffX);
					 player.getSprite().setLayoutY(player.getSprite().getLayoutY()-diffY);

				    //s'il y a collision
				    if (obstacleBounds.intersects(playerBounds)) {
				    	
				    	
				    	//recuperation de l'url de l'image dde l'obstacle 
				    	Image obstacleImage = ((ImageView) obstacle).getImage();
				        String obstacleImagePath = obstacleImage.getUrl();
				        
				        //Switch selon le nom de l'img (via l'URL)
				        switch(obstacleImagePath.substring(16)) {
				        case "diamond.png":   
				        	ActionChoice.displayActionChoice(obstacle,player,gridpane,"Diamond",Constants.diamond.getImageView());

				        break;
				        case "épée.png":   
				        	ActionChoice.displayActionChoice(obstacle,player,gridpane,"Épée",Constants.épée.getImageView());

				        break;
				        case "pigKing.png":
				        	Monster m = (Monster) obstacle.getUserData();
				        	System.out.println("collision detected with " + m.getName());
				        	System.out.println("PV avant collision :"+player.getHealth());
				        	player.setHealth(player.getHealth()-m.getStrength(),map);
				        	System.out.println("PV apres collision :"+player.getHealth());
				        break;
				        case "pigMob.png":
				        	Monster pigMob = (Monster) obstacle.getUserData();
				        	System.out.println("collision detected with " + pigMob.getName());
				        	System.out.println("PV avant collision :"+player.getHealth());
				        	player.setHealth(player.getHealth()-pigMob.getStrength(),map);
				        	System.out.println("PV apres collision :"+player.getHealth());
				        break;
				        case "potionBlue.png":
				        	ActionChoice.displayActionChoice(obstacle,player,gridpane,"potionBlue",Constants.potionBlue.getImageView());
				        break;
				        case "potionRed.png":
				        	ActionChoice.displayActionChoice(obstacle,player,gridpane,"potionRed",Constants.potionRed.getImageView());
				        break;
				        case "key.png":
				        	ActionChoice.displayActionChoice(obstacle,player,gridpane,"key",Constants.key.getImageView());
				        break;
				        case "door1.png":
				        	ActionOnDoor.displayActionDoorChoice(obstacle,player,gridpane,map);//test
				        break;
				        case "door3.png":
				        	ActionOnDoor.displayActionDoorChoice(obstacle,player,gridpane,map);//test
				        break;
				        case "flag.png":
				        	ActionEndGame.displayEndGame(true);
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
	
	
	
	public static Monster attackCollision(Player player,GridPane gridpane) {		
		
		for (Node ennemy : gridpane.getChildren()) {
			
			    //Vérifier si le joueur est en collision avec l'obstacle
				 if (ennemy instanceof ImageView ) {
					 //creation des bounds de l'obstacle et du player our verif les collisions
					 Bounds obstacleBounds = ennemy.getBoundsInParent();
         		     Bounds playerBounds = player.getSprite().getBoundsInParent();
         		     
         		     // on prend la zone de collision du personnage a laquelle on ajoute artificiellement 10px de chaque coté
         		    double x = playerBounds.getMinX() - 10;
         		    double y = playerBounds.getMinY() - 10;
         		    double width = playerBounds.getWidth() + 20;
         		    double height = playerBounds.getHeight() + 20;
         		    Bounds areaBounds = new BoundingBox(x, y, width, height);
         		     
         		    if (obstacleBounds.intersects(areaBounds)) { 
         		    	Monster monster = (Monster) ennemy.getUserData();
         		    	System.out.println(monster.getName());
         		    	return monster;
				    }
				 }
			}
		return null;
		}
	}

