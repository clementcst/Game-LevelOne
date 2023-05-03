package game;

import game.item.Weapon;
import game.map.Map;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game extends Application {

    private static final int SCENE_WIDTH = 1216;
    private static final int SCENE_HEIGHT = 704;
    private Boolean use_Z = true;
    private Boolean use_Q = true;
    private Boolean use_S = true;
    private Boolean use_D = true;

    @Override
    public void start(Stage primaryStage) throws Exception {
        
    	
    	Map map = new Map(SCENE_WIDTH, SCENE_HEIGHT);  
        Scene scene = map.createMap();
        
        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            switch (keyCode) {
                case Z:
                	if(use_Z) {
                		map.getPlayer().move(map.getGridpaneObstacle(),map.getGridpaneInteract(),  "Up");
                		use_Z = false;
                		Timeline delayTimeline = new Timeline(new KeyFrame(Duration.millis(1000), e -> use_Z = true));
                        delayTimeline.play();
                	}
                	break;
                case S:
                	if(use_S) {
                		map.getPlayer().move(map.getGridpaneObstacle(),map.getGridpaneInteract(), "Down");
                		use_S = false;
                		Timeline delayTimeline = new Timeline(new KeyFrame(Duration.millis(1000), e -> use_S = true));
                        delayTimeline.play();
                	}
                	break;
                case Q:
                	if(use_Q) {
                		map.getPlayer().move(map.getGridpaneObstacle(),map.getGridpaneInteract(), "Left");
                		use_Q = false;
                		Timeline delayTimeline = new Timeline(new KeyFrame(Duration.millis(1000), e -> use_Q = true));
                        delayTimeline.play();
                	}
                	break;
                case D:
                	if(use_D) {
                		map.getPlayer().move(map.getGridpaneObstacle(),map.getGridpaneInteract(), "Right");
                		use_D = false;
                		Timeline delayTimeline = new Timeline(new KeyFrame(Duration.millis(1000), e -> use_D = true));
                        delayTimeline.play();
                	}
                	break;
                case I:
                	map.getPlayer().getInventory().getStage().show();
                	break;
                default:
                    break;
            }
            map.updateMap();
        });
            
        //scene.setOnKeyReleased(event -> {
        //    map.getPlayer().animatedStay(map.getGridpaneObstacle(),standing, true);
        //    map.updateMap();
        //});
        
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
