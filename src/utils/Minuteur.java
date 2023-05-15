package utils;


import game.popUp.ActionEndGame;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class Minuteur extends GridPane {
    private Timeline timeline;
    private Label timeLabel;

    public Minuteur() {
        // Créer un label pour afficher le temps
        timeLabel = new Label();
        timeLabel.setFont(new Font("Arial", 24));
        timeLabel.setTextFill(Paint.valueOf("#FFF")); // définit la couleur du texte en rouge

        timeLabel.setAlignment(Pos.CENTER);
        timeLabel.setPrefSize(64,32);
        //timeLabel.setStyle("-fx-color:white;");

        // Ajouter le label à la GridPane
        this.add(timeLabel, 0, 0);

        // Configurer la GridPane
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(10));
        this.setHgap(10);

        // Créer la timeline pour le minuteur
        timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        

        // Ajouter une KeyFrame toutes les secondes
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Mettre à jour le label avec le temps restant
            	
                int remainingTime = Integer.parseInt(timeLabel.getText()) - 1;
                timeLabel.setText(Integer.toString(remainingTime));
                //System.out.println(remainingTime);
                // Arrêter le minuteur si le temps est écoulé
                if (remainingTime == 0) {
                    timeline.stop();
                    timeLabel.setText("Temps écoulé !");
                    ActionEndGame.displayEndGame(false);
                }
            }
        }));
    }

    public void start(int durationInSeconds) {
        // Définir la durée du minuteur en secondes
        timeLabel.setText(Integer.toString(durationInSeconds));

        // Démarrer la timeline
        timeline.playFromStart();
    }
}
