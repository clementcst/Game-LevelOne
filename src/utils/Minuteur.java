package utils;

import java.awt.Color;

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
        // Cr�er un label pour afficher le temps
        timeLabel = new Label();
        timeLabel.setFont(new Font("Arial", 24));
        timeLabel.setTextFill(Paint.valueOf("#FFF")); // d�finit la couleur du texte en rouge

        timeLabel.setAlignment(Pos.CENTER);
        timeLabel.setPrefSize(64,32);
        //timeLabel.setStyle("-fx-color:white;");

        // Ajouter le label � la GridPane
        this.add(timeLabel, 0, 0);

        // Configurer la GridPane
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(10));
        this.setHgap(10);

        // Cr�er la timeline pour le minuteur
        timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        

        // Ajouter une KeyFrame toutes les secondes
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Mettre � jour le label avec le temps restant
            	
                int remainingTime = Integer.parseInt(timeLabel.getText()) - 1;
                timeLabel.setText(Integer.toString(remainingTime));
                //System.out.println(remainingTime);
                // Arr�ter le minuteur si le temps est �coul�
                if (remainingTime == 0) {
                    timeline.stop();
                    timeLabel.setText("Temps �coul� !");
                }
            }
        }));
    }

    public void start(int durationInSeconds) {
        // D�finir la dur�e du minuteur en secondes
        timeLabel.setText(Integer.toString(durationInSeconds));

        // D�marrer la timeline
        timeline.playFromStart();
    }
}
