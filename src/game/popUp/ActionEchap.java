package game.popUp;

import game.Game;
import game.PrimaryStageHolder;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.Minuteur;

public class ActionEchap {
	
	private static boolean isOpen = false;
	
	public static void displayPause() {
		
		Minuteur.pause();
		Minuteur.addTime(-50);
		Button goBack = new Button("Go back");
		goBack.setStyle("-fx-background-color: rgb(56,52,68); -fx-text-fill: white; -fx-border-color: white; -fx-border-width: 2px; -fx-border-radius: 5;");
		
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
        
        HBox hBox = new HBox(10, goBack, restartGame, exitGame);
        hBox.setAlignment(Pos.CENTER);
        
        vBox.getChildren().addAll(
        		hBox
        );

        borderPane.setCenter(vBox);

        Scene dialogScene = new Scene(borderPane, 300, 200);

        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.setScene(dialogScene);
        dialog.setTitle("Pause");

        dialog.setOnCloseRequest(event -> {
        	event.consume();
        });
        
        goBack.setOnAction(event -> {
        	//Ferme la Stage dialog quand on clique sur la croix rouge
        	isOpen = false;
        	Minuteur.resume();
        	dialog.close();
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
