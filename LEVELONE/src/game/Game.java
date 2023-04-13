package game;



import game.map.Map;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;



public class Game extends Application {

    private static final int SCENE_WIDTH = 1216;
    private static final int SCENE_HEIGHT = 704;

    
    //private Player player;

    @Override
    public void start(Stage primaryStage) throws Exception {
        
    	Map map = new Map(SCENE_WIDTH, SCENE_HEIGHT);
        Scene scene = map.createMap();
        

        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            switch (keyCode) {
                case Z:
                	for(int i = 0; i < 10; i++) {
                		map.getPlayer().getSprite().setY(map.getPlayer().getSprite().getY() - 1);
                    	if(map.getPlayer().testCollision(map.getGridpaneObstacle())) {
                    		map.getPlayer().getSprite().setY(map.getPlayer().getSprite().getY() + 1);
                    	}
                    	if(map.getPlayer().testCollision(map.getGridpaneInteract())) {
                    		System.out.println("AIE LE PERSONNAGE DE STAN M'A FAIT MAL !");
                    	}
                	}
                	
                    break;
                case S:
                	for(int i = 0; i < 10; i++) {
	                	map.getPlayer().getSprite().setY(map.getPlayer().getSprite().getY() + 1);
	                	if(map.getPlayer().testCollision(map.getGridpaneObstacle())) {
	                		map.getPlayer().getSprite().setY(map.getPlayer().getSprite().getY() - 1);
	                	}
	                	if(map.getPlayer().testCollision(map.getGridpaneInteract())) {
	                		System.out.println("AIE LE PERSONNAGE DE STAN M'A FAIT MAL !");
	                	}
                	}
                    break;
                case Q:
                	for(int i = 0; i < 10; i++) {
	                	map.getPlayer().getSprite().setX(map.getPlayer().getSprite().getX() - 1);
	                	if(map.getPlayer().testCollision(map.getGridpaneObstacle())) {
	                		map.getPlayer().getSprite().setX(map.getPlayer().getSprite().getX() + 1);
	                	}
	                	if(map.getPlayer().testCollision(map.getGridpaneInteract())) {
	                		System.out.println("AIE LE PERSONNAGE DE STAN M'A FAIT MAL !");
	                	}
                	}
                    break;
                case D:
                	for(int i = 0; i < 10; i++) {
	                	map.getPlayer().getSprite().setX(map.getPlayer().getSprite().getX() + 1);
	                	if(map.getPlayer().testCollision(map.getGridpaneObstacle())) {
	                		map.getPlayer().getSprite().setX(map.getPlayer().getSprite().getX() - 1);
	                	}
	                	if(map.getPlayer().testCollision(map.getGridpaneInteract())) {
	                		System.out.println("AIE LE PERSONNAGE DE STAN M'A FAIT MAL !");
	                	}
                	}
                    break;
                default:
                    break;
            }
            //System.out.println("X :" + map.getPlayer().getSprite().getX() + "Y :" + map.getPlayer().getSprite().getY());
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
