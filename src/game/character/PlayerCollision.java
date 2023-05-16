package game.character;


import game.item.Items;
import game.map.Map;
import game.pnj.Monster;
import game.popUp.ActionChoice;
import game.popUp.ActionEndGame;
import game.popUp.ActionOnDoor;
import game.popUp.ActionOnMerchant;
import game.textures.Constants;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;


	public class PlayerCollision {

	
	public static boolean testCollision(Player player,GridPane gridpane,double diffX, double diffY, Map map) {		
		
		for (Node obstacle : gridpane.getChildren()) {
			
			    //VÃ©rifier si le joueur est en collision avec l'obstacle
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
				        	Items D = new Items("diamond","beautiful diamond wit a lot of utilities ;)", "Use With Merchant");
				        	ActionChoice.displayActionChoice(obstacle,player,gridpane,D,Constants.diamond.getImageView());
				        break;
				        case "épée.png":
				        	Items W = new Items("épée","beautiful weapon ", "Use to kill ennemy");
				        	ActionChoice.displayActionChoice(obstacle,player,gridpane,W,Constants.épée.getImageView());
				        break;
				        case "pigKing.png":
				        	if(player.CanBeHurt()) {
					        	Monster pigKing = (Monster) obstacle.getUserData();
					        	System.out.println("collision detected with " + pigKing.getName());
					        	System.out.println("PV avant collision :"+player.getHealth());
					        	player.setHealth(player.getHealth()-pigKing.getStrength(),map);
					        	player.takingDamage();
					        	System.out.println("PV apres collision :"+player.getHealth());
				        	}
				        break;
				        case "pigMob.png":
				        	if(player.CanBeHurt()) {
					        	Monster pigMob = (Monster) obstacle.getUserData();
					        	System.out.println("collision detected with " + pigMob.getName());
					        	System.out.println("PV avant collision :"+player.getHealth());
					        	player.setHealth(player.getHealth()-pigMob.getStrength(),map);
					        	player.takingDamage();
					        	System.out.println("PV apres collision :"+player.getHealth());
				        	}
				        break;
				        case "potionBlue.png":
				        	Items B = new Items("potionBlue", "Your weapon w'll have 1 more damage","Drink");
				        	ActionChoice.displayActionChoice(obstacle,player,gridpane,B,Constants.potionBlue.getImageView());
				        break;
				        case "potionRed.png":
				        	Items R = new Items("potionRed", "Give you 1 more life to destroy ennemy","Drink");
				        	ActionChoice.displayActionChoice(obstacle,player,gridpane,R,Constants.potionRed.getImageView());
				        break;
				        case "potionGreen.png":
                            Items G = new Items("potionGreen", "Add 50s More to finish the game !","Drink");
                            ActionChoice.displayActionChoice(obstacle,player,gridpane,G,Constants.potionGreen.getImageView());
                        break;
                        case "potionPurple.png":
                            Items P = new Items("potionPurple", "Temporarily immobilizes monsters for 20 seconds","Drink");
                            ActionChoice.displayActionChoice(obstacle,player,gridpane,P,Constants.potionPurple.getImageView());
                        break;
                        case "potionYellow.png":
                            Items Y = new Items("potionYellow", "Invicibility for 15 seconds","Drink");
                            ActionChoice.displayActionChoice(obstacle,player,gridpane,Y,Constants.potionYellow.getImageView());
                        break;
				        case "key.png":
				        	Items K = new Items("key", "Permet d'ouvrir une porte","Use next to a door");
				        	ActionChoice.displayActionChoice(obstacle,player,gridpane,K,Constants.key.getImageView());
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
				        case "merchant.png":
				        	ActionOnMerchant.displayActionMerchant(obstacle, player, gridpane, map);
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
			
			    //VÃ©rifier si le joueur est en collision avec l'obstacle
				 if (ennemy instanceof ImageView ) {
					 //creation des bounds de l'obstacle et du player our verif les collisions
					 Bounds obstacleBounds = ennemy.getBoundsInParent();
         		     Bounds playerBounds = player.getSprite().getBoundsInParent();
         		     
         		     // on prend la zone de collision du personnage a laquelle on ajoute artificiellement 10px de chaque cotÃ©
         		    double x = playerBounds.getMinX() - 32;
         		    double y = playerBounds.getMinY() - 32;
         		    double width = playerBounds.getWidth() + 96;
         		    double height = playerBounds.getHeight() + 96;
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

