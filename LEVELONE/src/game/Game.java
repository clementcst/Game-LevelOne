package game;

import game.map.Map;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class Game extends Application {

    private static final int SCENE_WIDTH = 1216;
    private static final int SCENE_HEIGHT = 704;


    @Override
    public void start(Stage primaryStage) throws Exception {
        
    	
    	Map map = new Map(SCENE_WIDTH, SCENE_HEIGHT);  
        Scene scene = map.createMap();

        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            if (!map.getPlayer().isAttacking()) {
	            switch (keyCode) {
	                case Z:
	                	map.getPlayer().moveTop(map.getGridpaneObstacle(),map.getGridpaneInteract());
	                    break;
	                case S:
	                	map.getPlayer().moveBottom(map.getGridpaneObstacle(),map.getGridpaneInteract());
	                    break;
	                case Q:
	                	map.getPlayer().moveLeft(map.getGridpaneObstacle(),map.getGridpaneInteract());
	                    break;
	                case D:
	                	map.getPlayer().moveRight(map.getGridpaneObstacle(),map.getGridpaneInteract());
	                    break;
	                case I:
	                	map.getPlayer().getInventory().getStage().show();
	                	break;
	                default:
	                    break;
	            }
	            map.updateMap();
            }
        });
            
        
        scene.setOnMousePressed(event -> {	
            if (event.isPrimaryButtonDown() && !map.getPlayer().isAttacking()) {
                if (!map.getPlayer().getWeapon().equals(null)) {
                	map.getPlayer().setAttacking(true);
                    map.getPlayer().attack(map.getPlayer(), map.getGridpaneInteract(), map);
                    map.updateMap();
                }
                map.getPlayer().setAttacking(false);
            }
        });
        
        if(map.getPlayer().getHealth() == 0) System.exit(0);

       
        primaryStage.setTitle("LEVELONE");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
    	launch(args);
    }
}
