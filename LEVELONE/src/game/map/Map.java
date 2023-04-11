package game.map;

import game.character.Player;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class Map {
	
    private int width;
    private int height;
    private GridPane gridpane;
    private Pane pane;
	private StackPane stackpane;
    private Player player;


    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.gridpane = new GridPane();
        gridpane.setPrefSize(width, height);
        this.player = new Player("clement",0,0,"res/images/perso.png");
        this.stackpane = new StackPane();
        this.pane = new Pane();
    }
    
    public Pane getPane() {
		return pane;
	}
    
	public void setPane(Pane pane) {
		this.pane = pane;
	}

	public StackPane getStackpane() {
		return stackpane;
	}

	public void setStackpane(StackPane stackpane) {
		this.stackpane = stackpane;
	}

    
    public GridPane getGridpane() {
		return gridpane;
	}

	public void setGridpane(GridPane gridpane) {
		this.gridpane = gridpane;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

    
    public Scene createMap() {
        
        for (int row = 0; row < 30; row++) {
            for (int col = 0; col < 20; col++) {
            	ImageView background = new ImageView(new Image("res/images/stone.jpg"));
                background.setFitWidth(60);
                background.setFitHeight(60);
                gridpane.add(background,row,col);
            }
        }
        
        stackpane.getChildren().add(gridpane);
        stackpane.getChildren().add(pane);
        pane.getChildren().add(this.getPlayer().getSprite());
        
 
        return new Scene(stackpane, width, height);
    }

	
}