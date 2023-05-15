package game.popUp;

import game.character.Player;
import game.item.Items;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ActionChoice {

	private static boolean isOpen = false;
	
	public static void displayActionChoice(Node node,Player player, GridPane gridpane,Items items,ImageView imageView) {
	 	Button takeButton = new Button("Take");
	 	takeButton.setStyle("-fx-background-color: rgb(56,52,68); -fx-text-fill: white; -fx-border-color: white; -fx-border-width: 2px; -fx-border-radius: 5;");
	 	
	 	Button dontTakeButton = new Button("Don't Take");
	 	dontTakeButton.setStyle("-fx-background-color: rgb(56,52,68); -fx-text-fill: white; -fx-border-color: white; -fx-border-width: 2px; -fx-border-radius: 5;");

        BorderPane borderPane = new BorderPane();
        //borderPane.setMaxSize(600, 400);
        borderPane.setStyle("-fx-padding: 5px; -fx-background-color: rgb(56,52,68);");

        VBox vBox = new VBox(10);
        vBox.setStyle("-fx-border-width: 2px; s-fx-border-color: white; -fx-border-radius: 10; -fx-background-color: rgb(56,52,50);");
        vBox.setAlignment(Pos.CENTER);
        
        Label itm_name = new Label(items.getName());
        itm_name.setStyle("-fx-text-fill: white; -fx-margin-bottom: 10px;");
        
        HBox hBox = new HBox(10, takeButton, dontTakeButton);
        hBox.setAlignment(Pos.CENTER);
        
        vBox.getChildren().addAll(
        		itm_name,
                imageView,
                hBox
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

        takeButton.setOnAction(event -> {
            gridpane.getChildren().remove(node);
	        player.getInventory().push(items);
	        isOpen = false;
            dialog.close();
        });

        dontTakeButton.setOnAction(event -> {
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
