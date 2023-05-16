package game.popUp;

import game.Game;
import game.PrimaryStageHolder;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.Minuteur;

public class ActionEndGame {
	
	private static boolean isOpen = false;
	
	
	public static void displayEndGame(Boolean victory) {
		
		Minuteur.pause();
	 	Button restartGame = new Button("Restart");
	 	restartGame.setStyle("-fx-background-color: rgb(56,52,68); -fx-text-fill: white; -fx-border-color: white; -fx-border-width: 2px; -fx-border-radius: 5;");
	 	
	 	Button exitGame = new Button("Exit");
	 	exitGame.setStyle("-fx-background-color: rgb(56,52,68); -fx-text-fill: white; -fx-border-color: white; -fx-border-width: 2px; -fx-border-radius: 5;");

        BorderPane borderPane = new BorderPane();
        //borderPane.setMaxSize(600, 400);
        borderPane.setStyle("-fx-padding: 5px; -fx-background-color: rgb(56,52,68);");

        VBox vBox = new VBox(10);
        vBox.setStyle("-fx-border-width: 2px; -fx-border-color: white; -fx-border-radius: 10; -fx-background-color: rgb(56,52,50);");
        vBox.setAlignment(Pos.CENTER);
        
        HBox hBox = new HBox(10, restartGame, exitGame);
        hBox.setAlignment(Pos.CENTER);
        
        if(victory) {
        	Label end_message = new Label("Bravo, vous avez gagné !");
        	end_message.setStyle("-fx-text-fill: white; -fx-margin-bottom: 10px;");
        	vBox.getChildren().addAll(
            		end_message,
                    hBox
            );
        }
        else {
        	Label end_message = new Label("Dommage, vous avez perdu.");
        	end_message.setStyle("-fx-text-fill: white; -fx-margin-bottom: 10px;");
        	vBox.getChildren().addAll(
            		end_message,
                    hBox
            );
        }

        borderPane.setCenter(vBox);

        Scene dialogScene = new Scene(borderPane, 300, 200);

        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.setScene(dialogScene);
        dialog.setTitle("Fin de partie !");

        dialog.setOnCloseRequest(event -> {
        	event.consume();
        });

        restartGame.setOnAction(event -> {
            System.out.println("Restart game");
            dialog.close();
            Stage actualStage = PrimaryStageHolder.getPrimaryStage();
            try {
            	actualStage.close();
            	isOpen = false;
				Game newGame = new Game();
				Stage newPrimaryStage = new Stage();
				newGame.start(newPrimaryStage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });

        exitGame.setOnAction(event -> {
            System.out.println("Exit game");
            System.exit(0);
        });

        //Platform.runLater(() -> dialog.showAndWait());
        if (isOpen == false) {
        	dialog.initModality(Modality.APPLICATION_MODAL);
        	Platform.runLater(() -> dialog.showAndWait());
        	isOpen = true;
        }
  }
}
