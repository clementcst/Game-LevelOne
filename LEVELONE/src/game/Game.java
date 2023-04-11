package game;

import game.map.Map;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;


public class Game extends Application {

    private static final int SCENE_WIDTH = 1200;
    private static final int SCENE_HEIGHT = 700;
    
    //private Player player;

    @Override
    public void start(Stage primaryStage) throws Exception {
        
    	Map map = new Map(SCENE_WIDTH, SCENE_HEIGHT);
        Scene scene = map.createMap();
        
        
        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            switch (keyCode) {
                case Z:
                	map.getPlayer().getSprite().setY(map.getPlayer().getSprite().getY() - 10);
                    break;
                case S:
                	map.getPlayer().getSprite().setY(map.getPlayer().getSprite().getY() + 10);
                    break;
                case Q:
                	map.getPlayer().getSprite().setX(map.getPlayer().getSprite().getX() - 10);
                    break;
                case D:
                	map.getPlayer().getSprite().setX(map.getPlayer().getSprite().getX() + 10);
                    break;
                default:
                    break;
            }
            System.out.println("X :" + map.getPlayer().getSprite().getX() + "Y :" + map.getPlayer().getSprite().getY());
        });

        primaryStage.setTitle("LEVELONE");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
    	launch(args);
    }
}
