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
            switch (keyCode) {
                case Z:
                	map.getPlayer().getAnimationUp().play();
                	break;
                case S:
                	map.getPlayer().getAnimationDown().play();
                	break;
                case Q:
                	map.getPlayer().getAnimationLeft().play();
                	break;
                case D:
                	map.getPlayer().getAnimationRight().play();
                	break;
                case I:
                    map.getPlayer().getInventory().updateStageInventory(map.getPlayer());
                	map.getPlayer().getInventory().getStage().show();
                	break;
                default:
                    break;
            }
            map.updateMap();
        });
        
        
        
        scene.setOnKeyReleased(event -> {
            KeyCode keyCode = event.getCode();
            switch (keyCode) {
                case Z:
                	map.getPlayer().getAnimationUp().stop();
                	break;
                case S:
                	map.getPlayer().getAnimationDown().stop();
                	break;
                case Q:
                	map.getPlayer().getAnimationLeft().stop();
                	break;
                case D:
                	map.getPlayer().getAnimationRight().stop();
                	break;
                default:
                    break;
            }
            map.updateMap();
        });
        
        
            

        scene.setOnMousePressed(event -> {	
            if (event.isPrimaryButtonDown() && !map.getPlayer().isAttacking()) {
                if (!map.getPlayer().getWeapon().equals(null)) {
                	map.getPlayer().setAttacking(true);
                    map.getPlayer().attack(map);
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
