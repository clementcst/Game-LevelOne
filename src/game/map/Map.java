package game.map;

import java.util.HashMap;

import game.character.Player;
import game.item.AbstractItem;
import game.item.Items;
import game.pnj.Merchant;
import game.pnj.Monster;
import game.reader.CsvReader;
import game.textures.Constants;
import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import utils.Minuteur;


public class Map {

	private int height;
	private int width;
    private GridPane gridpaneBackground;
    private GridPane gridpaneObstacle;
    private GridPane gridpaneInteract;
    private GridPane gridpanePnj;
    private GridPane gameInfo;
    private Pane pane;
    //private GridPane pane;
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
        this.gridpanePnj = new GridPane();
        gridpanePnj.setPrefSize(width, height);
        this.pane = new Pane();
        this.player = new Player("clement",19,0,"file:res/images/perso.png",this);
        this.player.getSprite().setLayoutX(70);
        this.player.getSprite().setLayoutY(70);
        this.stackpane = new StackPane();
    
        

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
	
	public GridPane getGridpanePnj() {
		return gridpanePnj;
	}



	public void setGridpanePnj(GridPane gridpanePnj) {
		this.gridpanePnj = gridpanePnj;
	}



	public void InitGridpane(String nameFile,GridPane gridpane) {
		HashMap<String, ImageView> imageMap = new HashMap<>();
		
		//Gripane background
		imageMap.put("black", Constants.black.getImageView());
		imageMap.put("sol", Constants.sol.getImageView());
		//Gripane Obstacles
		imageMap.put("topLeftCorner", Constants.topLeftCorner.getImageView());
		imageMap.put("topRightCorner", Constants.topRightCorner.getImageView());
		imageMap.put("bottomLeftCorner", Constants.bottomLeftCorner.getImageView());
		imageMap.put("bottomRightCorner", Constants.bottomRightCorner.getImageView());
		imageMap.put("topBorder", Constants.topBorder.getImageView());
		imageMap.put("bottomBorder", Constants.bottomBorder.getImageView());
		imageMap.put("leftBorder", Constants.leftBorder.getImageView());
		imageMap.put("rightBorder", Constants.rightBorder.getImageView());
		imageMap.put("block", Constants.block.getImageView());
		
		imageMap.put("verticalLine", Constants.verticalLine.getImageView());
		imageMap.put("bottomVerticalLine", Constants.bottomVerticalLine.getImageView());
		imageMap.put("topVerticalLine", Constants.topVerticalLine.getImageView());
		imageMap.put("horizontalLine", Constants.horizontalLine.getImageView());
		imageMap.put("leftHorizontalLine", Constants.leftHorizontalLine.getImageView());
		imageMap.put("cornerLeft", Constants.cornerLeft.getImageView());
		imageMap.put("cornerRight", Constants.cornerRight.getImageView());
		imageMap.put("cornerLeftBottom", Constants.cornerLeftBottom.getImageView());
		imageMap.put("cornerRightBottom", Constants.cornerRightBottom.getImageView());
		
		imageMap.put("door1", Constants.door1.getImageView());
		imageMap.put("door2", Constants.door2.getImageView());
		imageMap.put("door3", Constants.door3.getImageView());
		imageMap.put("door4", Constants.door4.getImageView());

		imageMap.put("doorOpen1", Constants.doorOpen1.getImageView());
		imageMap.put("doorOpen2", Constants.doorOpen2.getImageView());
		imageMap.put("doorOpen3", Constants.doorOpen3.getImageView());
		imageMap.put("doorOpen4", Constants.doorOpen4.getImageView());
		
		imageMap.put("flag", Constants.flag.getImageView());

		//objects.put("wall", Constants.wall.getImageView());//
		// Ajoutez toutes les autres variables avec leurs noms correspondants

		
	for (int col = 0; col < 38; col++) {
    		for (int row = 0; row < 22; row++) {
    			Region region = new Region();
    			region.setPrefSize(32, 32);
    			region.setMinSize(32,32);
    			region.setMaxSize(32,32);
    			gridpane.add(region, col, row);
    		}
		}

		String[][] matrix = CsvReader.ReadFile("res/files/"+nameFile+".csv");
		
    	for(int i = 0 ; i < 38;i++) {
    		for(int j = 0;j<22;j++) {
    			
    			switch(matrix[j][i]) {
    				case "pigKing" :
    					Items drop = new Items("key", "Ouvre porte");
    					Monster pigKing = new Monster("stan", 4, 2, false, Constants.stan, (AbstractItem) drop, this, i,j);
    					gridpane.add(pigKing.getImageView(),i,j);
    					pigKing.getRandomM().play();
    					break;
    				case "pigMob" :
    					Items drop_pigMob = new Items("diamond","beautiful diamond wit a lot of utilities ;)", "Use With Merchant");
    					Monster pigMob = new Monster("pigMob", 2, 1, false, Constants.stan2, (AbstractItem) drop_pigMob, this, i,j);
    					gridpane.add(pigMob.getImageView(),i,j);
    					pigMob.getRandomM().play();
    					break;
    				case "potionRed" :
    					Items R = new Items("potionRed", "Se boit. Rouge","Drink");
    					gridpane.add(R.getImageView(),i,j);
    					break;
    				case "potionBlue" :
    					Items B = new Items("potionBlue", "Se boit. Bleue","Drink");
    					gridpane.add(B.getImageView(),i,j);
    					break;
    				case "key" :
    					Items K = new Items("key", "Permet d'ouvrir une porte","Use");
    					gridpane.add(K.getImageView(),i,j);
    					break;
    				case "diamond" :
    					Items D = new Items("diamond", "beautiful diamond wit a lot of utilities ;)", "Use With Merchant");
    					gridpane.add(D.getImageView(),i,j);
    					break;
				case "merchant" :
    					Merchant Me = new Merchant("Mearchant",Constants.merchant);
    					gridpane.add(Me.getImageView(),i,j);
    					break;
    				case "nothing" :
    					break;
    				default :
    					gridpane.add(new ImageView(imageMap.get(matrix[j][i]).getImage()),i,j);
    			}
    		}
    	}
	}

	
	
	public void majLife() {
		int i;
    	for(i = 0 ; i < this.getPlayer().getHealth()/2 ; i++) {
    		gameInfo.add(Constants.fullHeart.getImageView(),i,0);
    	}
    	if(this.getPlayer().getHealth()%2 == 1) gameInfo.add(Constants.semiHeart.getImageView(),i++,0);
    	for(int a = i; a < 10; a++) {
			gameInfo.add(Constants.emptyHeart.getImageView(),a,0);
		}
	}
	
	public void InitGridpaneGameInfo(Player player) {
	
		for (int col = 0; col < 22; col++) {
    		for (int row = 0; row < 38; row++) {
    			Region region = new Region();
    			region.setPrefSize(32, 32);
    			region.setMinSize(32,32);
    			region.setMaxSize(32,32);
    			gameInfo.add(region, row, col);
    		}
		}
		majLife();
		
    	
    	Minuteur minuteur = new Minuteur(player);
    	gameInfo.add(minuteur,36,0);
    	GridPane.setColumnSpan(minuteur,3);
    	minuteur.start(50);
	}
	
	
    public Scene createMap() {

    	InitGridpane("background",this.gridpaneBackground);
    	InitGridpane("obstacles",this.gridpaneObstacle);
    	InitGridpane("interact",this.gridpaneInteract);
    	InitGridpane("pnj",this.gridpanePnj);
    	InitGridpaneGameInfo(player);
  
    	//empilement des panes
        stackpane.getChildren().add(gridpaneBackground);
        stackpane.getChildren().add(gridpaneObstacle);
        stackpane.getChildren().add(pane);
        stackpane.getChildren().add(gridpaneInteract);
        stackpane.getChildren().add(gridpanePnj);
        stackpane.getChildren().add(gameInfo);
        pane.getChildren().add(this.getPlayer().getSprite());

        return new Scene(stackpane, width, height);
    }
    

	public void updateMap() {  	
    	/*System.out.println("update de la map...");
    	System.out.println("Position du personnage : {"+getPlayer().getX()+";"+getPlayer().getY()+"}");
    	System.out.println("PV :"+getPlayer().getHealth());*/
    	
    }

	
}
