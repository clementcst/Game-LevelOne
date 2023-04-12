package game.map;

import game.character.Player;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class Map {
	
    private int width;
    private int height;
    private GridPane gridpane;
    private GridPane gridpane2;
    private Pane pane;
	private StackPane stackpane;
    private Player player;


    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.gridpane = new GridPane();
        gridpane.setPrefSize(width, height);
        this.gridpane2 = new GridPane();
        gridpane2.setPrefSize(width, height);
        this.player = new Player("clement",0,0,"res/images/perso.png");
        this.stackpane = new StackPane();
        this.pane = new Pane();
    }
    
    public GridPane getGridpane2() {
		return gridpane2;
	}

	public void setGridpane2(GridPane gridpane2) {
		this.gridpane2 = gridpane2;
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
        
    	//Creation de la gridpane pour les obstacles
    	
    	String[][] Fond = new String[38][22];
    	
    	//initialisation du background
    	for(int i = 0 ; i < 38; i++) {
    		for(int j = 0 ; j < 22; j++) {
    			if (i==0 || i==1 || i==37 || i==36 || j==0 || j==1 || j==21 || j==20) Fond[i][j] = "black";
    			else Fond[i][j] = "ground";
    		}
    	}
    	

    	//remplissage a partir de la matrice pour le background
    	for (int row = 0; row < 38; row++) {
            for (int col = 0; col < 22; col++) {
            	
            	switch(Fond[row][col]) {
            	case "black" :
            		gridpane.add(new ImageView(new Image("res/images/black.png")),row,col);
                  break;
            	case "ground" :
                    gridpane.add(new ImageView(new Image("res/images/ground1.png")),row,col);
                  break;
                default:
                	   System.out.println("zeub, ya rien en " + row + col);
                	   ImageView background6 = new ImageView(new Image("res/images/ground1.png"));
                       background6.setFitWidth(32);
                       background6.setFitHeight(32);
                       gridpane.add(background6,row,col);
                	break;
            	}      
            }
        }    
    	
    	
    	//Creation de la gridpane pour les obstacles

    	for (int row = 0; row < 38; row++) {
    		for (int col = 0; col < 22; col++) {
    			Region region = new Region();
    			region.setPrefSize(32, 32); 
    			gridpane2.add(region, col, row);
    		}
		}
    	
    	
    	
    	gridpane2.add(new ImageView(new Image("res/images/black.png")), 10, 10);
	
    
        stackpane.getChildren().add(gridpane);
        stackpane.getChildren().add(gridpane2);
        stackpane.getChildren().add(pane);
        pane.getChildren().add(this.getPlayer().getSprite());
        
 
        return new Scene(stackpane, width, height);
    }

	
}