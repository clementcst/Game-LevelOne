package game.popUp;


import game.character.Player;
import game.item.AbstractItem;
import game.item.Items;
import game.map.Map;
import javafx.application.Platform;
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
import javafx.stage.StageStyle;
import utils.Minuteur;

public class ActionOnMerchant {
private static boolean isOpen = false;
	
	
	public static void displayActionMerchant(Node node,Player player, GridPane gridpane,Map map) {

		int nbrDiamond = 0;
		player.stopAnimation();
		
		for(AbstractItem item : player.getInventory().getItem()) {
			
			
			if(item != null && item.getName().equals("diamond")) {
	    		 nbrDiamond++;
				}
			}

			if(nbrDiamond >= 1) {
				
				//System.out.println("i y a assez de diam ");
				Button takeButton2 = new Button("Trade 1 diamonds for a gift ? (red potion) ");
	    		takeButton2.setStyle("-fx-background-color: rgb(56,52,68); -fx-text-fill: white; -fx-border-color: white; -fx-border-width: 2px; -fx-border-radius: 5;");
	    		
	    		 Button goBack = new Button("Go back");
	    		goBack.setStyle("-fx-background-color: rgb(56,52,68); -fx-text-fill: white; -fx-border-color: white; -fx-border-width: 2px; -fx-border-radius: 5;");
	
	    		  BorderPane borderPane = new BorderPane();
	    	      borderPane.setStyle("-fx-padding: 5px; -fx-background-color: rgb(56,52,68);");

	    	        VBox vBox = new VBox(10);
	    	        vBox.setStyle("-fx-border-width: 2px; -fx-border-color: white; -fx-border-radius: 10; -fx-background-color: rgb(56,52,50);");
	    	        vBox.setAlignment(Pos.CENTER);
	    	        
	    	        Label itm_name = new Label("It's time to get out ! ");
	    	        itm_name.setStyle("-fx-text-fill: white; -fx-margin-bottom: 10px;");
	    	        
	    	        HBox hBox = new HBox(10, takeButton2);
	    	        hBox.setAlignment(Pos.CENTER);
	    	        
	    	        HBox hBox2 = new HBox(10, goBack);
	    	        hBox2.setAlignment(Pos.CENTER);
	    	        
	    	        ImageView trade = new ImageView(new Image("file:res/images/tradeDiamondPotionRed.png"));
	    	        trade.setFitHeight(50);
	    	        trade.setFitWidth(100);
	    	        
	    	        
	    	        vBox.getChildren().addAll(
	    	        		itm_name,
	    	        		trade,
	    	                hBox,
	    	                hBox2
	    	        );

	    	        borderPane.setCenter(vBox);
	    	        
	    	        Scene dialogScene = new Scene(borderPane, 300, 200);

	    	        Stage dialog = new Stage();
	    	        dialog.setScene(dialogScene);
	    	        dialog.setTitle("Merchant Interaction");

	    	        dialog.setOnCloseRequest(event -> {
	    	        	//Ferme la Stage dialog quand on clique sur la croix rouge
	    	        	isOpen = false;
	    	        	dialog.close();
	    	        });

	    	        takeButton2.setOnAction(event -> {
	    	        	//enlevr les diamonds et ajouter objets special
	    	        	for(AbstractItem item : player.getInventory().getItem()) {
	    	        		if(item != null && item.getName().equals("diamond")) {
	    	        			player.getInventory().remove(item, player, map);
	    	        			break;
	    	        		}
	    	        	   isOpen = false;
	 	    	           dialog.close();
	    	        	}
	    	        	Items R = new Items("potionRed", "Give you 1 more life to destroy ennemy","Drink");
	    	        	player.getInventory().push(R);
		    	     });
	    	        
	    	    	goBack.setOnAction(event -> {
	    	    		//Ferme la Stage dialog quand on clique sur la croix rouge
	    	    			isOpen = false;
	        	        	dialog.close();;
	    	    	});


	    	        //Platform.runLater(() -> dialog.showAndWait());
	    	        if (isOpen == false) {
	    	        	dialog.initModality(Modality.APPLICATION_MODAL);
	    	        	dialog.initStyle(StageStyle.UNDECORATED);
	    	        	Platform.runLater(() -> dialog.showAndWait());
	    	        	isOpen = true;
	    	        }
	   
			}else {
				//System.out.println("i y a PAS assez de diam ");
				Button takeButton2 = new Button("Go back to Fight");
		   		 takeButton2.setStyle("-fx-background-color: rgb(56,52,68); -fx-text-fill: white; -fx-border-color: white; -fx-border-width: 2px; -fx-border-radius: 5;");
		   		 
		   		  BorderPane borderPane = new BorderPane();
		   	        borderPane.setStyle("-fx-padding: 5px; -fx-background-color: rgb(56,52,68);");

		   	        VBox vBox = new VBox(10);
			        vBox.setStyle("-fx-border-width: 2px; -fx-border-color: white; -fx-border-radius: 10; -fx-background-color: rgb(56,52,50);");
			        vBox.setAlignment(Pos.CENTER);
			        
			        Label itm_name = new Label("Hmmm.. Sorry... You don't have enough item, more money ?!" );
			        itm_name.setStyle("-fx-text-fill: white; -fx-margin-bottom: 10px;");
			        
			        HBox hBox = new HBox(10, takeButton2);
			        hBox.setAlignment(Pos.CENTER);
			        
			        vBox.getChildren().addAll(
			        		itm_name,
			                hBox
			        );

			        borderPane.setCenter(vBox);

		   	        
		   	        Scene dialogScene = new Scene(borderPane, 300, 200);

		   	        Stage dialog = new Stage();
		   	        dialog.setScene(dialogScene);
		   	        dialog.setTitle("Tips : You a to fight... AGAIN ! ");

		   	        dialog.setOnCloseRequest(event -> {
		   	        	//Ferme la Stage dialog quand on clique sur la croix rouge
		   	        	isOpen = false;
		   	        	dialog.close();
		   	        });

		   	        //ON TEST LA PORTE 
		   	        takeButton2.setOnAction(event -> {

		   		        isOpen = false;
		   	            dialog.close();
		   	        });

		   	        //Platform.runLater(() -> dialog.showAndWait());
		   	        if (isOpen == false) {
		   	        	dialog.initModality(Modality.APPLICATION_MODAL);
		   	        	dialog.initStyle(StageStyle.UNDECORATED);
		   	        	Platform.runLater(() -> dialog.showAndWait());
		   	        	isOpen = true;
		   	        }
			}

   		
   	}	 
}
