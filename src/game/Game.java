package game;


import game.map.Map;
import game.pnj.Monster;
import game.popUp.ActionEndGame;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class Game extends Application {

    private static final int SCENE_WIDTH = 1216;
    private static final int SCENE_HEIGHT = 704;


    @Override
    public void start(Stage primaryStage) throws Exception {
 
    	Map map = new Map(SCENE_WIDTH, SCENE_HEIGHT);  
        Scene scene = map.createMap();
        
        for (Node obstacle : map.getGridpanePnj().getChildren()) {        	
        	if (obstacle instanceof ImageView ) {
        		Image obstacleImage = ((ImageView) obstacle).getImage();
		        String obstacleImagePath = obstacleImage.getUrl();
        		if(obstacleImagePath.substring(16).equals("pigKing.png")) {
        			Monster m = (Monster) obstacle.getUserData();
        			m.getRandomM().play();
        			m.playerInVision(map);
        		}
        	}
        }
      
        scene.setOnKeyPressed(event -> {
            System.out.println("PV : "+map.getPlayer().getHealth());
            
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
                    map.getPlayer().getInventory().updateStageInventory(map.getPlayer(),map);
                	map.getPlayer().getInventory().getStage().show();
                	break;
                default:
                    break;
            }
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
        });

        scene.setOnMousePressed(event -> {	
            if (event.isPrimaryButtonDown() && !map.getPlayer().isAttacking()) {
                if (!map.getPlayer().getWeapon().equals(null)) {
                	map.getPlayer().setAttacking(true);
                    map.getPlayer().attack(map);
                }
                map.getPlayer().setAttacking(false);
            }
        });
        
        System.out.println("AVANT : PV DU JOUEUR :"+map.getPlayer().getHealth());
        if(map.getPlayer().getHealth() <= 0) {
        	System.out.println("je suis mort");
        	ActionEndGame.displayEndGame(null, null, null);
        }

       
        primaryStage.setTitle("LEVELONE");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
    	launch(args);
    }
}
