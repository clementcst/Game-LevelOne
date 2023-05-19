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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.Minuteur;

public class ActionChoice {

    private static boolean isOpen = false;

    public static void displayActionChoice(Node node, Player player, GridPane gridpane, Items items, ImageView imageView) {

        player.stopAnimation();
        Minuteur.pause();
        Button takeButton = createStyledButton("Take");
        Button dontTakeButton = createStyledButton("Don't Take");

        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-padding: 5px; -fx-background-color: rgb(56, 52, 68);");

        VBox vBox = new VBox(10);
        vBox.setStyle("-fx-border-width: 2px; -fx-border-color: white; -fx-border-radius: 10; -fx-background-color: rgb(56, 52, 50);");
        vBox.setAlignment(Pos.CENTER);

        Label itemName = new Label(items.getName());
        itemName.setStyle("-fx-text-fill: white; -fx-margin-bottom: 10px;");

        HBox hBox = new HBox(10, takeButton, dontTakeButton);
        hBox.setAlignment(Pos.CENTER);

        Text itemDescription = new Text(items.getDescription());
        itemDescription.setFill(Color.WHITE);
        StackPane descBox = new StackPane(itemDescription);

        vBox.getChildren().addAll(
                itemName,
                imageView,
                descBox,
                hBox
        );

        borderPane.setCenter(vBox);

        Scene dialogScene = new Scene(borderPane, 300, 200);

        Stage dialog = new Stage();
        dialog.setScene(dialogScene);
        dialog.setTitle("Pick Up Items Decision");

        dialog.setOnCloseRequest(event -> {
            isOpen = false;
            dialog.close();
        });

        takeButton.setOnAction(event -> {
            gridpane.getChildren().remove(node);
            player.getInventory().push(items);
            isOpen = false;
            Minuteur.resume();
            dialog.close();
        });

        dontTakeButton.setOnAction(event -> {
            isOpen = false;
            Minuteur.resume();
            dialog.close();
        });

        if (!isOpen) {
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initStyle(StageStyle.UNDECORATED);
            Platform.runLater(() -> dialog.showAndWait());
            isOpen = true;
        }
    }

    private static Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: rgb(56, 52, 68); -fx-text-fill: white; -fx-border-color: white; -fx-border-width: 2px; -fx-border-radius: 5;");
        return button;
    }
}
