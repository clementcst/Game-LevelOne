package game.character;

import game.item.Potion;
import game.pnj.Monster;
import game.textures.Constants;
import javafx.application.Platform;
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
import javafx.stage.Stage;

	public class PlayerCollision {
		
		private static boolean isOpen = false;
		
	
	public static void displayActionChoice(Node node,Player player, GridPane gridpane,String name,ImageView imageView) {
		 	Button takeButton = new Button("Take");
	        Button dontTakeButton = new Button("Don't Take");

	        BorderPane borderPane = new BorderPane();

	        VBox vBox = new VBox(10);
	        vBox.setAlignment(Pos.CENTER);
	        vBox.getChildren().addAll(
	                new Label(name),
	                imageView,
	                new HBox(10, takeButton, dontTakeButton)
	        );

	        borderPane.setCenter(vBox);

	        Scene dialogScene = new Scene(borderPane, 300, 200);

	        Stage dialog = new Stage();
	        dialog.setScene(dialogScene);
	        dialog.setTitle("Pick Up Items Decision");


	        takeButton.setOnAction(event -> {
	            System.out.println("Take button clicked");
	            gridpane.getChildren().remove(node);
		        player.getInventory().push(new Potion(name));
		        isOpen = false;
	            dialog.close();
	        });

	        dontTakeButton.setOnAction(event -> {
	            System.out.println("Don't take button clicked");
	            // TODO: Implement don't take action
	            isOpen = false;
	            dialog.close();
	        });

	        //Platform.runLater(() -> dialog.showAndWait());
	        if (isOpen == false) {
	        	Platform.runLater(() -> dialog.show());
	        	isOpen = true;
	        }
	  }

	
	public static boolean testCollision(Player player,GridPane gridpane,double diffX, double diffY) {		
		
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
				        	displayActionChoice(obstacle,player,gridpane,"Diamond",Constants.diamond.getImageView());

				        break;
				        case "épée.png":   
				        	displayActionChoice(obstacle,player,gridpane,"Épée",Constants.épée.getImageView());

				        break;
				        case "pigKing.png":
				        	System.out.println("collision detected with mob");
				        	System.out.println("PV avant collision :"+player.getHealth());
				        	player.setHealth(player.getHealth()-1);
				        	System.out.println("PV apres collision :"+player.getHealth());
				        break;
				        case "potionBlue.png":
				        	displayActionChoice(obstacle,player,gridpane,"potionBlue",Constants.potionBlue.getImageView());
				        break;
				        case "potionRed.png":
				        	displayActionChoice(obstacle,player,gridpane,"potionRed",Constants.potionRed.getImageView());
				        break;
				        case "key.png":
				        	displayActionChoice(obstacle,player,gridpane,"key",Constants.key.getImageView());
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

