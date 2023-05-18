package game.popUp;

import java.util.ArrayList;
import java.util.List;

import game.character.Player;
import game.item.AbstractItem;
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

public class ActionOnDoor {
	private static boolean isOpen = false;
	
	
	public static void displayActionDoorChoice(Node node,Player player, GridPane gridpane,Map map) {
		 
		player.stopAnimation();
		
		
		for(AbstractItem item : player.getInventory().getItem()) {
	    	System.out.println(player.getInventory().getItem().length);
			if(item != null && item.getName().equals("key")) {
	    		 //possibilité d'ouvrir
	    		 
	    		 Button takeButton2 = new Button("Use your Key ! ");
	    		 takeButton2.setStyle("-fx-background-color: rgb(56,52,68); -fx-text-fill: white; -fx-border-color: white; -fx-border-width: 2px; -fx-border-radius: 5;");
	    		 
	    		 Button goBack = new Button("Go back");
		    	goBack.setStyle("-fx-background-color: rgb(56,52,68); -fx-text-fill: white; -fx-border-color: white; -fx-border-width: 2px; -fx-border-radius: 5;");
		
	    		 
	    		  BorderPane borderPane = new BorderPane();
	    	        //borderPane.setMaxSize(600, 400);
	    	        borderPane.setStyle("-fx-padding: 5px; -fx-background-color: rgb(56,52,68);");

	    	        VBox vBox = new VBox(10);
	    	        vBox.setStyle("-fx-border-width: 2px; -fx-border-color: white; -fx-border-radius: 10; -fx-background-color: rgb(56,52,50);");
	    	        vBox.setAlignment(Pos.CENTER);
	    	        
	    	        Label itm_name = new Label(item.getName());
	    	        itm_name.setStyle("-fx-text-fill: white; -fx-margin-bottom: 10px;");
	    	        
	    	        HBox hBox = new HBox(10, takeButton2);
	    	        hBox.setAlignment(Pos.CENTER);
	    	        
	    	        HBox hBox2 = new HBox(10, goBack);
	    	        hBox2.setAlignment(Pos.CENTER);
	    	        
	    	        vBox.getChildren().addAll(
	    	        		itm_name,
	    	                item.getImageView(),
	    	                hBox,
	    	                hBox2
	    	        );

	    	        borderPane.setCenter(vBox);
	    	        
	    	        Scene dialogScene = new Scene(borderPane, 300, 200);

	    	        Stage dialog = new Stage();
	    	        dialog.setScene(dialogScene);
	    	        dialog.setTitle("Pick Up Items Decision");

	    	        dialog.setOnCloseRequest(event -> {
	    	        	//Ferme la Stage dialog quand on clique sur la croix rouge
	    	        	isOpen = false;
	    	        	dialog.close();
	    	        });
	    	        
	    	        goBack.setOnAction(event -> {
	    	    		//Ferme la Stage dialog quand on clique sur la croix rouge
	    	    			isOpen = false;
	        	        	dialog.close();;
	    	    	});

	    	        
	    	        //ON TEST LA PORTE 
	    	        takeButton2.setOnAction(event -> {

	    	         List<Node> nodesToRemove = new ArrayList<>();

	    	         // Trouver les noeuds à supprimer
	    	         for (Node nodet : gridpane.getChildren()) {
	    	        
	    	             if (nodet instanceof ImageView){
	    	            	Image obstacleImage = ((ImageView) nodet).getImage();
	    	             	String obstacleImagePath = obstacleImage.getUrl();
	    	             	if(obstacleImagePath.contains("door")) {
	    	             			nodesToRemove.add(nodet);
	    	             	}
	    	             } 
	    	         }

	    	         // Supprimer les noeuds de la grille en utilisant la copie de la liste
	    	         for (Node nodeToRemove : nodesToRemove) {
	    	             gridpane.getChildren().remove(nodeToRemove);
	    	         }
	    	         	player.getInventory().remove(item, player,map);
	    	         
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
	    	     break;
	    	 
	    	 }	 
	   }
		
		
		 Button takeButton2 = new Button("Exit");
   		 takeButton2.setStyle("-fx-background-color: rgb(56,52,68); -fx-text-fill: white; -fx-border-color: white; -fx-border-width: 2px; -fx-border-radius: 5;");
   		 
   		  BorderPane borderPane = new BorderPane();
   	        borderPane.setStyle("-fx-padding: 5px; -fx-background-color: rgb(56,52,68);");

   	        VBox vBox = new VBox(10);
	        vBox.setStyle("-fx-border-width: 2px; -fx-border-color: white; -fx-border-radius: 10; -fx-background-color: rgb(56,52,50);");
	        vBox.setAlignment(Pos.CENTER);
	        
	        Label itm_name = new Label("Hmmm.. You need a special item !" );
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
   	        dialog.setTitle("Tips : You a to fight ");

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
   	        	Platform.runLater(() -> dialog.showAndWait());
   	        	isOpen = true;
   	        }
		
   		
   	}	 
}
