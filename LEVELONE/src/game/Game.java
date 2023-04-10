package game;

import game.character.Player;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Game extends Application {

    private static final int SCENE_WIDTH = 800;
    private static final int SCENE_HEIGHT = 600;

    private Pane root;
    //private Player player;

    @Override
    public void start(Stage primaryStage) {
        root = new Pane();
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        
        Player player = new Player("clement",0,0,"res/images/tete.png");
        player.getSprite().setFitWidth(100);
        player.getSprite().setFitHeight(150);
        root.getChildren().add(player.getSprite());
        
        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            switch (keyCode) {
                case UP:
                    player.getSprite().setY(player.getSprite().getY() - 10);
                    break;
                case DOWN:
                    player.getSprite().setY(player.getSprite().getY() + 10);
                    break;
                case LEFT:
                    player.getSprite().setX(player.getSprite().getX() - 10);
                    break;
                case RIGHT:
                    player.getSprite().setX(player.getSprite().getX() + 10);
                    break;
                default:
                    break;
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
    	launch(args);
    }
}
