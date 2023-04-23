package game.inventory;

import game.item.AbstractItem;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Inventory {

    //instances de l'inventaire pour la partie graphique 
    private Stage stage;
    private BorderPane borderPane;
    private ImageView centre;

    //instances de l'inventaire pour la gestion des items
    protected AbstractItem[] items;
    protected int nbItem;

    public Inventory() {    
        //constructeur partie items
        this.nbItem = 0;
        this.items = new AbstractItem[12];

        for(int i = 0 ; i < 12; i++) {
            this.items[i] = null;
        }

        this.borderPane = new BorderPane();
        this.borderPane.setCenter(new GridPane());
        this.borderPane.setMaxSize(600, 400);
        this.stage = new Stage();
        this.stage.setTitle("Inventaire");
        this.stage.setScene(new Scene(this.borderPane));
        this.stage.setResizable(false);
        this.stage.initModality(Modality.APPLICATION_MODAL);
    }
    
    public void updateStageInventory() {
    	this.afficheInventory();
    	BorderPane bp = new BorderPane();
    	bp.setMaxSize(600, 400);
    	
    	//System.out.println("mise a jour de la gridpane inventory");
        HBox infoPlayer = new HBox();
        infoPlayer.setPrefHeight(50);
        infoPlayer.setPrefWidth(500);
        Text PlayerName = new Text("PERSONNAGE");
        ImageView imageView = new ImageView(new Image("file:res/images/perso.png"));
        infoPlayer.setPrefHeight(30);
        infoPlayer.setPrefHeight(30);
        infoPlayer.getChildren().add(imageView);
        infoPlayer.getChildren().add(PlayerName);
        infoPlayer.setStyle("-fx-border-width: 1px; -fx-border-color: red;");

        //Créez la grille d'items
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        VBox ItemInfo = new VBox();
        ItemInfo.setPrefSize(300, 200);
        ItemInfo.setMaxSize(300, 200);
        ItemInfo.setStyle("-fx-border-width: 1px; -fx-border-color: blue;");

        int row = 0;
        int compteur = 0;
        for(AbstractItem item : items) {	
            if (item != null) {
                ImageView item_view = item.getImageView();
                item_view.setFitHeight(50);
                item_view.setFitWidth(50);
                
                item_view.setOnMouseClicked(event ->{
                    ItemInfo.getChildren().clear();
                    ItemInfo.getChildren().add(new Text(item.getName()));
                    ImageView itemView = new ImageView(item.getImageView().getImage().getUrl());
                    itemView.setFitHeight(100);
                    itemView.setFitWidth(100);
                    ItemInfo.getChildren().add(itemView);
                    ItemInfo.getChildren().add(new Text(item.getDescription()));
                });
                gridPane.add(item_view, compteur%4, row);
                compteur++;

                if(compteur%4 == 0) {
                    row++;
                }
            }

     }

        bp.setTop(infoPlayer);
        bp.setCenter(gridPane);
        bp.setRight(ItemInfo);
        
        this.borderPane=bp;
        this.stage.setScene(new Scene(bp));;
    }
	
	//getters setters partie items 
	public AbstractItem[] getItem() {
		return items;
	}

	public void setItem(AbstractItem[] item) {
		this.items = item;
	}

	public int getNbItem() {
		return nbItem;
	}

	public void setNbItem(int nbItem) {
		this.nbItem = nbItem;
	}


	public ImageView getCentre() {
		return centre;
	}

	public void setCentre(ImageView centre) {
		this.centre = centre;
	}
	
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public BorderPane getBorderPane() {
		return borderPane;
	}
	

	public void push(AbstractItem item) {
		this.items[this.nbItem] = item;
		this.nbItem++;
		this.updateStageInventory();
	}
	
	public void afficheInventory() {
		System.out.println("Voici le contenu de l'inventaire :");
		for(int i = 0 ; i < this.nbItem; i++) {
			System.out.println(items[i].getName());
		}
	}
	
	public void setBorderPane(BorderPane borderPane) {
		this.borderPane = borderPane;
	}
}
