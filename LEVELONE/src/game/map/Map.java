package game.map;

import java.util.HashMap;

import game.character.Player;
import game.reader.CsvReader;
import game.textures.Texture;
import game.textures.Constants;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import utils.Minuteur;


public class Map {

	private int height;
	private int width;
    private GridPane gridpaneBackground;
    private GridPane gridpaneObstacle;
    private GridPane gridpaneInteract;
    private GridPane gameInfo;
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
        this.gameInfo = new GridPane();
        gameInfo.setPrefSize(width, height);
        this.player = new Player("clement",10,0,"file:res/images/perso.png");
        this.player.getSprite().setX(70);
        this.player.getSprite().setY(70);
        this.stackpane = new StackPane();
        this.pane = new Pane();
        

    }
    

    
    public GridPane getGameInfo() {
		return gameInfo;
	}

	public void setGameInfo(GridPane gameInfo) {
		this.gameInfo = gameInfo;
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
	
	public void InitGridpaneBackground() {
		//Creation de la gridpane pour les obstacles
    	
    	String[][] Fond = new String[38][22];
    	
    	//initialisation du background
    	for(int i = 0 ; i < 38; i++) {
    		for(int j = 0 ; j < 22; j++) {
    			if (i==0  || i==1 || i==37 || i==36 || j==0  || j==1 || j==21 || j==20) Fond[i][j] = "black";
    			else Fond[i][j] = "ground";
    		}
    	}

    	//System.out.println(Constants.black.getImageView());

    	//remplissage a partir de la matrice pour le background
    	for (int row = 0; row < 38; row++) {
            for (int col = 0; col < 22; col++) {
            	
            	switch(Fond[row][col]) {
            	case "black" :
            		gridpaneBackground.add(Constants.black.getImageView(),row,col);
                  break;
            	case "ground" :
            		gridpaneBackground.add(Constants.sol.getImageView(),row,col);
                  break;
                default: 	 
                	break;
            	}      
            }
        }
	}
	
	public void InitGridpaneObstacle() {
		
		HashMap<String, ImageView> imageMap = new HashMap<>();
		imageMap.put("topLeftCorner", Constants.topLeftCorner.getImageView());
		imageMap.put("topRightCorner", Constants.topRightCorner.getImageView());
		imageMap.put("bottomLeftCorner", Constants.bottomLeftCorner.getImageView());
		imageMap.put("bottomRightCorner", Constants.bottomRightCorner.getImageView());
		imageMap.put("topBorder", Constants.topBorder.getImageView());
		imageMap.put("bottomBorder", Constants.bottomBorder.getImageView());
		imageMap.put("leftBorder", Constants.leftBorder.getImageView());
		imageMap.put("rightBorder", Constants.rightBorder.getImageView());
		/*imageMap.put("topLeftCorner", Constants.topLeftCorner.getImageView());
		imageMap.put("topRightCorner", Constants.topRightCorner.getImageView());
		imageMap.put("topLeftCorner", Constants.topLeftCorner.getImageView());
		imageMap.put("topRightCorner", Constants.topRightCorner.getImageView());
		imageMap.put("topLeftCorner", Constants.topLeftCorner.getImageView());
		imageMap.put("topRightCorner", Constants.topRightCorner.getImageView());
		imageMap.put("topLeftCorner", Constants.topLeftCorner.getImageView());
		imageMap.put("topRightCorner", Constants.topRightCorner.getImageView());*/
		
		
		//objects.put("wall", Constants.wall.getImageView());//
		// Ajoutez toutes les autres variables avec leurs noms correspondants

		
		for (int col = 0; col < 22; col++) {
    		for (int row = 0; row < 38; row++) {
    			Region region = new Region();
    			region.setPrefSize(32, 32);
    			region.setMinSize(32,32);
    			region.setMaxSize(32,32);
    			gridpaneObstacle.add(region, row, col);
    		}
		}
		
		//Cl�ment affichage csv en cours 
		
		/*String[][] obstacles = CsvReader.ReadFile("res/files/obstacles.csv");
		
    	for(int i = 0 ; i < 37;i++) {
    		for(int j = 0;j<21;j++) {	
    			if(obstacles[i][j] != null) {
    				gridpaneObstacle.add(new ImageView(imageMap.get(obstacles[i][j]).getImage()),j+1,i+1);
    			}
    		}
    	}*/


    	//gridpaneObstacle.add(Constants.topLeftCorner.getImageView(),0,0);
    	

    	
    	//ajout des bords exterieur de la map
    	this.add2Grid(2,36,Constants.topBorder,1,"horizontale");
    	this.add2Grid(2,36,Constants.bottomBorder,20,"horizontale");
    	this.add2Grid(2,20,Constants.leftBorder,1,"verticale");
    	this.add2Grid(2,20,Constants.rightBorder,36,"verticale");
    	
    	//ajout des coins exterieur
    	gridpaneObstacle.add(Constants.topLeftCorner.getImageView(), 1, 1);
    	gridpaneObstacle.add(Constants.topRightCorner.getImageView(), 36, 1);
    	gridpaneObstacle.add(Constants.bottomLeftCorner.getImageView(), 1, 20);
    	gridpaneObstacle.add(Constants.bottomRightCorner.getImageView(), 36, 20);
    	
    	
    	//creation block 1
    	gridpaneObstacle.add(Constants.block.getImageView(),7, 2);
    	this.add2Grid(3,7,Constants.verticalLine,7,"verticale");
    	gridpaneObstacle.add(Constants.bottomVerticalLine.getImageView(),7, 7);
    	
    	//creation block 2
    	gridpaneObstacle.add(Constants.block.getImageView(),2,10);
    	gridpaneObstacle.add(Constants.block.getImageView(),2,11);
    	gridpaneObstacle.add(Constants.block.getImageView(),10,10);
    	gridpaneObstacle.add(Constants.block.getImageView(),10,11);
    	this.add2Grid(3,10,Constants.horizontalLine,10,"horizontale");
    	this.add2Grid(3,10,Constants.horizontalLine,11,"horizontale");
    	
    	//creation block 3
    	gridpaneObstacle.add(Constants.topVerticalLine.getImageView(),4,14);
    	gridpaneObstacle.add(Constants.verticalLine.getImageView(),4,15);
    	gridpaneObstacle.add(Constants.bottomVerticalLine.getImageView(),4,16);
    	
    	//creation block 4
    	gridpaneObstacle.add(Constants.block.getImageView(),15,19);
    	gridpaneObstacle.add(Constants.leftHorizontalLine.getImageView(),8,15);
    	gridpaneObstacle.add(Constants.cornerRight.getImageView(),15,15);
    	this.add2Grid(9,15,Constants.horizontalLine,15,"horizontale");
    	this.add2Grid(16,19,Constants.verticalLine,15,"verticale");
    	
    	//creation block 4 bis
    	gridpaneObstacle.add(Constants.block.getImageView(),12,5);
    	gridpaneObstacle.add(Constants.block.getImageView(),12,6);
    	gridpaneObstacle.add(Constants.block.getImageView(),13,4);
    	gridpaneObstacle.add(Constants.block.getImageView(),14,4);
    	gridpaneObstacle.add(Constants.block.getImageView(),15,5);
    	gridpaneObstacle.add(Constants.block.getImageView(),15,6);
    	gridpaneObstacle.add(Constants.block.getImageView(),13,7);
    	gridpaneObstacle.add(Constants.block.getImageView(),14,7);
    	
    	gridpaneObstacle.add(Constants.cornerLeft.getImageView(),13,5);
    	gridpaneObstacle.add(Constants.cornerRight.getImageView(),14,5);
    	gridpaneObstacle.add(Constants.cornerLeftBottom.getImageView(),13,6);
    	gridpaneObstacle.add(Constants.cornerRightBottom.getImageView(),14,6);
    	
    	//creation block 5
    	gridpaneObstacle.add(Constants.block.getImageView(),17,11);
    	gridpaneObstacle.add(Constants.block.getImageView(),17,12);
    	gridpaneObstacle.add(Constants.block.getImageView(),13,11);
    	gridpaneObstacle.add(Constants.block.getImageView(),13,12);
    	this.add2Grid(14,17,Constants.horizontalLine,11,"horizontale");
    	this.add2Grid(14,17,Constants.horizontalLine,12,"horizontale");
    	
    	//creation block 6
    	gridpaneObstacle.add(Constants.block.getImageView(),18,19);
    	gridpaneObstacle.add(Constants.block.getImageView(),19,19);
    	this.add2Grid(6,19,Constants.verticalLine,18,"verticale");
    	this.add2Grid(6,19,Constants.verticalLine,19,"verticale");
    	gridpaneObstacle.add(Constants.block.getImageView(),18,5);
    	gridpaneObstacle.add(Constants.block.getImageView(),19,5);
    	gridpaneObstacle.add(Constants.block.getImageView(),18,4);
    	gridpaneObstacle.add(Constants.block.getImageView(),19,4);
    	
    	//creation block 7
    	this.add2Grid(20,25,Constants.horizontalLine,4,"horizontale");
    	this.add2Grid(20,25,Constants.horizontalLine,5,"horizontale");
    	gridpaneObstacle.add(Constants.block.getImageView(),25,4);
    	gridpaneObstacle.add(Constants.block.getImageView(),26,4);
    	gridpaneObstacle.add(Constants.block.getImageView(),25,5);
    	gridpaneObstacle.add(Constants.block.getImageView(),26,5);
    	
    	//creation block 8
    	this.add2Grid(6,9,Constants.verticalLine,25,"verticale");
    	this.add2Grid(6,9,Constants.verticalLine,26,"verticale");
    	gridpaneObstacle.add(Constants.block.getImageView(),25,9);
    	gridpaneObstacle.add(Constants.block.getImageView(),26,9);
    	gridpaneObstacle.add(Constants.block.getImageView(),25,10);
    	gridpaneObstacle.add(Constants.block.getImageView(),26,10);
    	
    	//creation block 9
    	this.add2Grid(27,32,Constants.horizontalLine,9,"horizontale");
    	this.add2Grid(27,32,Constants.horizontalLine,10,"horizontale");
    	gridpaneObstacle.add(Constants.block.getImageView(),32,9);
    	gridpaneObstacle.add(Constants.block.getImageView(),33,9);
    	gridpaneObstacle.add(Constants.block.getImageView(),32,10);
    	gridpaneObstacle.add(Constants.block.getImageView(),33,10);
    	
    	//creation block 10
    	this.add2Grid(6,9,Constants.verticalLine,32,"verticale");
    	this.add2Grid(6,9,Constants.verticalLine,33,"verticale");
    	gridpaneObstacle.add(Constants.block.getImageView(),32,4);
    	gridpaneObstacle.add(Constants.block.getImageView(),33,4);
    	gridpaneObstacle.add(Constants.block.getImageView(),32,5);
    	gridpaneObstacle.add(Constants.block.getImageView(),33,5);
    	
    	//creation block 11
    	gridpaneObstacle.add(Constants.block.getImageView(),29,2);
    	this.add2Grid(2,6,Constants.verticalLine,29,"verticale");
    	gridpaneObstacle.add(Constants.bottomVerticalLine.getImageView(),29,6);
    	
    	//creation block 12
    	gridpaneObstacle.add(Constants.cornerLeft.getImageView(),22,13);
    	gridpaneObstacle.add(Constants.cornerRight.getImageView(),23,13);
    	gridpaneObstacle.add(Constants.cornerLeftBottom.getImageView(),22,14);
    	gridpaneObstacle.add(Constants.cornerRightBottom.getImageView(),23,14);
    	this.add2Grid(9,13,Constants.verticalLine,22,"verticale");
    	this.add2Grid(15,17,Constants.verticalLine,23,"verticale");
    	gridpaneObstacle.add(Constants.topVerticalLine.getImageView(),22,8);
    	gridpaneObstacle.add(Constants.bottomVerticalLine.getImageView(),23,17);

    	//creation block 13
    	gridpaneObstacle.add(Constants.block.getImageView(),27,15);
    	gridpaneObstacle.add(Constants.block.getImageView(),27,16);
    	gridpaneObstacle.add(Constants.block.getImageView(),35,15);
    	gridpaneObstacle.add(Constants.block.getImageView(),35,16);
    	this.add2Grid(28,35,Constants.horizontalLine,15,"horizontale");
    	this.add2Grid(28,35,Constants.horizontalLine,16,"horizontale");
	}
	
	
	public void InitGridpaneInteract() {
		
		for (int col = 0; col < 22; col++) {
    		for (int row = 0; row < 38; row++) {
    			Region region = new Region();
    			region.setPrefSize(32, 32);
    			region.setMinSize(32,32);
    			region.setMaxSize(32,32);
    			gridpaneInteract.add(region, row, col);
    		}
		}
		//ajout des monstres
    	gridpaneInteract.add(Constants.stan.getImageView(),8,2);
    	gridpaneInteract.add(Constants.stan2.getImageView(),22,15);
    	gridpaneInteract.add(Constants.potion.getImageView(),4,4);
    	gridpaneInteract.add(Constants.�p�e.getImageView(),5,5);
    	gridpaneInteract.add(Constants.diamond.getImageView(),6,6);

    	
	}
	

	public void InitGridpaneGameInfo() {
		

	
		for (int col = 0; col < 22; col++) {
    		for (int row = 0; row < 38; row++) {
    			Region region = new Region();
    			region.setPrefSize(32, 32);
    			region.setMinSize(32,32);
    			region.setMaxSize(32,32);
    			gameInfo.add(region, row, col);
    		}
		}

    	for(int i = 0 ; i < this.getPlayer().getHealth() ; i++) {
    		gameInfo.add(Constants.heart.getImageView(),i,0);
    	}
    	for(int i = this.getPlayer().getHealth(); i < this.getPlayer().getHealth()+2; i++) {
			gameInfo.add(Constants.black.getImageView(),i,0);
		}
    	
    	Minuteur minuteur = new Minuteur();
    	gameInfo.add(minuteur,36,0);
    	GridPane.setColumnSpan(minuteur,3);
    	minuteur.start(360);
	}
	
	public void majGridpaneGameInfo()
	{
	
		for(int i = 0 ; i < getPlayer().getHealth() ; i++) {
    		gameInfo.add(Constants.heart.getImageView(),i,0);
    	}
		
		for(int i = this.getPlayer().getHealth(); i < 15; i++) {
			gameInfo.add(Constants.black.getImageView(),i,0);
		}
	}
    public Scene createMap() {
        
    	InitGridpaneBackground();
    	InitGridpaneObstacle();
    	InitGridpaneInteract();
    	InitGridpaneGameInfo();
    
    	//empilement des panes
        stackpane.getChildren().add(gridpaneBackground);
        stackpane.getChildren().add(gridpaneObstacle);
        stackpane.getChildren().add(pane);
        stackpane.getChildren().add(gridpaneInteract);
        stackpane.getChildren().add(gameInfo);
        pane.getChildren().add(this.getPlayer().getSprite());

        return new Scene(stackpane, width, height);
    }
    
    public void updateMap() {  	
    	System.out.println("update de la map...");
    	System.out.println("Position du personnage : {"+getPlayer().getSprite().getX()+";"+getPlayer().getSprite().getY()+"}");
    	System.out.println("PV :"+getPlayer().getHealth());
    	
    	this.majGridpaneGameInfo();
    }

	
}