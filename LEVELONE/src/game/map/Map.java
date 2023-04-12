package game.map;

import game.character.Player;
import game.textures.Texture;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;


public class Map {
	
    private int width;
    private int height;
    private GridPane gridpaneBackground;
    private GridPane gridpaneObstacle;
    private GridPane gridpaneInteract;
    private Pane pane;
	private StackPane stackpane;
    private Player player;


    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.gridpaneBackground = new GridPane();
        gridpaneBackground.setPrefSize(width, height);
        this.gridpaneObstacle = new GridPane();
        gridpaneObstacle.setPrefSize(width, height);
        this.gridpaneInteract = new GridPane();
        gridpaneInteract.setPrefSize(width, height);
        this.player = new Player("clement",0,0,"res/images/perso.png");
        this.player.getSprite().setX(70);
        this.player.getSprite().setY(70);
        this.stackpane = new StackPane();
        this.pane = new Pane();
    }
    
    public GridPane getGridpaneObstacle() {
		return gridpaneObstacle;
	}

    public void setGridpaneObstacle(GridPane gridpaneObstacle) {
		this.gridpaneObstacle = gridpaneObstacle;
	}
	
	public GridPane getGridpaneBackground() {
		return gridpaneBackground;
	}

	public void setGridpaneBackground(GridPane gridpaneBackground) {
		this.gridpaneBackground = gridpaneBackground;
	}

	public GridPane getGridpaneInteract() {
		return gridpaneInteract;
	}

	public void setGridpaneInteract(GridPane gridpaneInteract) {
		this.gridpaneInteract = gridpaneInteract;
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
	

	public void add2Grid(int a, int b, Texture texture, int x, String orientation) {
		if (orientation.equals("horizontale")) {
			for(int i = a; i < b; i++)  this.getGridpaneObstacle().add(texture.getImageView(), i, x);
    	}else {
    		for(int i = a; i < b; i++)  this.getGridpaneObstacle().add(texture.getImageView(), x, i);
    	}
	}
	

    public Scene createMap() {
        
    	//Initialisation des textures :
    	Texture sol = new Texture("res/images/ground1.png");
    	Texture black = new Texture("res/images/black.png");
    	
    	//bordure exterieur
    	Texture topBorder = new Texture("res/images/topBorder.png");
    	Texture leftBorder = new Texture("res/images/leftBorder.png");
    	Texture rightBorder = new Texture("res/images/rightBorder.png");
    	Texture bottomBorder = new Texture("res/images/bottomBorder.png");
    	Texture bottomLeftCorner = new Texture("res/images/bottomLeftCorner.png");
    	Texture bottomRightCorner = new Texture("res/images/bottomRightCorner.png");
    	Texture topRightCorner = new Texture("res/images/topRightCorner.png");
    	Texture topLeftCorner = new Texture("res/images/topLeftCorner.png");
    	
    	//Line et block int�rieur
    	Texture topVerticalLine = new Texture("res/images/topVerticalLine.png");
    	Texture verticalLine = new Texture("res/images/verticalLine.png");
    	Texture bottomVerticalLine = new Texture("res/images/bottomVerticalLine.png");
    	Texture leftHorizontalLine = new Texture("res/images/leftHorizontalLine.png");
    	Texture horizontalLine = new Texture("res/images/horizontalLine.png");
    	//Texture rightHorizontalLine = new Texture("res/images/rightHorizontalLine.png");
    	
    	Texture cornerLeft = new Texture("res/images/cornerLeft.png");
    	Texture cornerRight = new Texture("res/images/cornerRight.png");
    	Texture cornerRightBottom = new Texture("res/images/cornerRightBottom.png");
    	Texture cornerLeftBottom = new Texture("res/images/cornerLeftBottom.png");
    	
    	Texture block = new Texture("res/images/block.png");
    	
    	Texture stan = new Texture("res/images/pigKing.png");
    	Texture stan2 = new Texture("res/images/pigMob.png");
    	
    	System.out.println(black.getImageView().getFitWidth());
    	System.out.println(black.getImageView().getFitHeight());
    	
    	
    	//Creation de la gridpane pour les obstacles
    	
    	String[][] Fond = new String[38][22];
    	
    	//initialisation du background
    	for(int i = 0 ; i < 38; i++) {
    		for(int j = 0 ; j < 22; j++) {
    			if (i==0  || i==1 || i==37 || i==36 || j==0  || j==1 || j==21 || j==20) Fond[i][j] = "black";
    			else Fond[i][j] = "ground";
    		}
    	}


    	//remplissage a partir de la matrice pour le background
    	for (int row = 0; row < 38; row++) {
            for (int col = 0; col < 22; col++) {
            	
            	switch(Fond[row][col]) {
            	case "black" :
            		gridpaneBackground.add(black.getImageView(),row,col);
                  break;
            	case "ground" :
            		gridpaneBackground.add(sol.getImageView(),row,col);
                  break;
                default: 	 
                	break;
            	}      
            }
        }    
    	
    	//Creation de la gridpane pour les obstacles
    	for (int col = 0; col < 22; col++) {
    		for (int row = 0; row < 38; row++) {
    			Region region = new Region();
    			region.setPrefSize(32, 32);
    			region.setMinSize(32,32);
    			region.setMaxSize(32,32);
    			gridpaneObstacle.add(region, row, col);
    		}
		}
    	
    	for (int col = 0; col < 22; col++) {
    		for (int row = 0; row < 38; row++) {
    			Region region = new Region();
    			region.setPrefSize(32, 32);
    			region.setMinSize(32,32);
    			region.setMaxSize(32,32);
    			gridpaneInteract.add(region, row, col);
    		}
		}
    	
    	//ajout des bords exterieur de la map
    	this.add2Grid(2,36,topBorder,1,"horizontale");
    	this.add2Grid(2,36,bottomBorder,20,"horizontale");
    	this.add2Grid(2,20,leftBorder,1,"verticale");
    	this.add2Grid(2,20,rightBorder,36,"verticale");
    	
    	//ajout des coins exterieur
    	gridpaneObstacle.add(topLeftCorner.getImageView(), 1, 1);
    	gridpaneObstacle.add(topRightCorner.getImageView(), 36, 1);
    	gridpaneObstacle.add(bottomLeftCorner.getImageView(), 1, 20);
    	gridpaneObstacle.add(bottomRightCorner.getImageView(), 36, 20);
    	
    	
    	//creation block 1
    	gridpaneObstacle.add(block.getImageView(),7, 2);
    	this.add2Grid(3,7,verticalLine,7,"verticale");
    	gridpaneObstacle.add(bottomVerticalLine.getImageView(),7, 7);
    	
    	//creation block 2
    	gridpaneObstacle.add(block.getImageView(),2,10);
    	gridpaneObstacle.add(block.getImageView(),2,11);
    	gridpaneObstacle.add(block.getImageView(),10,10);
    	gridpaneObstacle.add(block.getImageView(),10,11);
    	this.add2Grid(3,10,horizontalLine,10,"horizontale");
    	this.add2Grid(3,10,horizontalLine,11,"horizontale");
    	
    	//creation block 3
    	gridpaneObstacle.add(topVerticalLine.getImageView(),4,14);
    	gridpaneObstacle.add(verticalLine.getImageView(),4,15);
    	gridpaneObstacle.add(bottomVerticalLine.getImageView(),4,16);
    	
    	//creation block 4
    	gridpaneObstacle.add(block.getImageView(),15,19);
    	gridpaneObstacle.add(leftHorizontalLine.getImageView(),8,15);
    	gridpaneObstacle.add(cornerRight.getImageView(),15,15);
    	this.add2Grid(9,15,horizontalLine,15,"horizontale");
    	this.add2Grid(16,19,verticalLine,15,"verticale");
    	
    	//creation block 4 bis
    	gridpaneObstacle.add(block.getImageView(),12,5);
    	gridpaneObstacle.add(block.getImageView(),12,6);
    	gridpaneObstacle.add(block.getImageView(),13,4);
    	gridpaneObstacle.add(block.getImageView(),14,4);
    	gridpaneObstacle.add(block.getImageView(),15,5);
    	gridpaneObstacle.add(block.getImageView(),15,6);
    	gridpaneObstacle.add(block.getImageView(),13,7);
    	gridpaneObstacle.add(block.getImageView(),14,7);
    	
    	gridpaneObstacle.add(cornerLeft.getImageView(),13,5);
    	gridpaneObstacle.add(cornerRight.getImageView(),14,5);
    	gridpaneObstacle.add(cornerLeftBottom.getImageView(),13,6);
    	gridpaneObstacle.add(cornerRightBottom.getImageView(),14,6);
    	
    	//creation block 5
    	gridpaneObstacle.add(block.getImageView(),17,11);
    	gridpaneObstacle.add(block.getImageView(),17,12);
    	gridpaneObstacle.add(block.getImageView(),13,11);
    	gridpaneObstacle.add(block.getImageView(),13,12);
    	this.add2Grid(14,17,horizontalLine,11,"horizontale");
    	this.add2Grid(14,17,horizontalLine,12,"horizontale");
    	
    	//creation block 6
    	gridpaneObstacle.add(block.getImageView(),18,19);
    	gridpaneObstacle.add(block.getImageView(),19,19);
    	this.add2Grid(6,19,verticalLine,18,"verticale");
    	this.add2Grid(6,19,verticalLine,19,"verticale");
    	gridpaneObstacle.add(block.getImageView(),18,5);
    	gridpaneObstacle.add(block.getImageView(),19,5);
    	gridpaneObstacle.add(block.getImageView(),18,4);
    	gridpaneObstacle.add(block.getImageView(),19,4);
    	
    	//creation block 7
    	this.add2Grid(20,25,horizontalLine,4,"horizontale");
    	this.add2Grid(20,25,horizontalLine,5,"horizontale");
    	gridpaneObstacle.add(block.getImageView(),25,4);
    	gridpaneObstacle.add(block.getImageView(),26,4);
    	gridpaneObstacle.add(block.getImageView(),25,5);
    	gridpaneObstacle.add(block.getImageView(),26,5);
    	
    	//creation block 8
    	this.add2Grid(6,9,verticalLine,25,"verticale");
    	this.add2Grid(6,9,verticalLine,26,"verticale");
    	gridpaneObstacle.add(block.getImageView(),25,9);
    	gridpaneObstacle.add(block.getImageView(),26,9);
    	gridpaneObstacle.add(block.getImageView(),25,10);
    	gridpaneObstacle.add(block.getImageView(),26,10);
    	
    	//creation block 9
    	this.add2Grid(27,32,horizontalLine,9,"horizontale");
    	this.add2Grid(27,32,horizontalLine,10,"horizontale");
    	gridpaneObstacle.add(block.getImageView(),32,9);
    	gridpaneObstacle.add(block.getImageView(),33,9);
    	gridpaneObstacle.add(block.getImageView(),32,10);
    	gridpaneObstacle.add(block.getImageView(),33,10);
    	
    	//creation block 10
    	this.add2Grid(6,9,verticalLine,32,"verticale");
    	this.add2Grid(6,9,verticalLine,33,"verticale");
    	gridpaneObstacle.add(block.getImageView(),32,4);
    	gridpaneObstacle.add(block.getImageView(),33,4);
    	gridpaneObstacle.add(block.getImageView(),32,5);
    	gridpaneObstacle.add(block.getImageView(),33,5);
    	
    	//creation block 11
    	gridpaneObstacle.add(block.getImageView(),29,2);
    	this.add2Grid(2,6,verticalLine,29,"verticale");
    	gridpaneObstacle.add(bottomVerticalLine.getImageView(),29,6);
    	
    	//creation block 12
    	gridpaneObstacle.add(cornerLeft.getImageView(),22,13);
    	gridpaneObstacle.add(cornerRight.getImageView(),23,13);
    	gridpaneObstacle.add(cornerLeftBottom.getImageView(),22,14);
    	gridpaneObstacle.add(cornerRightBottom.getImageView(),23,14);
    	this.add2Grid(9,13,verticalLine,22,"verticale");
    	this.add2Grid(15,17,verticalLine,23,"verticale");
    	gridpaneObstacle.add(topVerticalLine.getImageView(),22,8);
    	gridpaneObstacle.add(bottomVerticalLine.getImageView(),23,17);

    	//mob design by Stan
    	gridpaneInteract.add(stan.getImageView(),25,17);
    	gridpaneInteract.add(stan2.getImageView(),26,17);
    	
    	//creation block 13
    	gridpaneObstacle.add(block.getImageView(),27,15);
    	gridpaneObstacle.add(block.getImageView(),27,16);
    	gridpaneObstacle.add(block.getImageView(),35,15);
    	gridpaneObstacle.add(block.getImageView(),35,16);
    	this.add2Grid(28,35,horizontalLine,15,"horizontale");
    	this.add2Grid(28,35,horizontalLine,16,"horizontale");
    	

    	//empilement des panes
        stackpane.getChildren().add(gridpaneBackground);
        stackpane.getChildren().add(gridpaneObstacle);
        stackpane.getChildren().add(gridpaneInteract);
        stackpane.getChildren().add(pane);
        pane.getChildren().add(this.getPlayer().getSprite());
        
 
        return new Scene(stackpane, width, height);
    }

	
	
}