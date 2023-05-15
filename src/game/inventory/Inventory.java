package game.inventory;

import game.character.Player;
import game.item.AbstractItem;
import game.map.Map;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
    
    public void updateStageInventory(Player player, Map map) {
    	
    	BorderPane bp = new BorderPane();
    	bp.setPrefSize(600, 300);
    	bp.setMaxSize(600, 300);
    	bp.setMinSize(600, 300);
    	bp.setStyle("-fx-padding: 5px; -fx-background-color: rgb(56,52,68);");
    	
    	//System.out.println("mise a jour de la gridpane inventory");
        HBox infoPlayer = new HBox();
        infoPlayer.setPrefHeight(50);
        infoPlayer.setPrefWidth(500);
        infoPlayer.setStyle("-fx-border-width: 2px; -fx-border-color: white; -fx-border-radius: 10; -fx-background-color: rgb(56,52,50);");
        
        Text PlayerName = new Text("PERSONNAGE");
        PlayerName.setFont(Font.font("Nom de la police", FontWeight.BOLD, 20));
        PlayerName.setFill(Color.YELLOW);
        HBox.setMargin(PlayerName, new Insets(0, 0, 0, 10));
        
        ImageView imageView = new ImageView(new Image("file:res/images/perso.png"));
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        HBox.setMargin(imageView, new Insets(10, 10, 10, 10));
        
        infoPlayer.getChildren().add(imageView);
        infoPlayer.getChildren().add(PlayerName);
        infoPlayer.setAlignment(Pos.CENTER_LEFT);
        BorderPane.setMargin(infoPlayer, new Insets(0, 0, 10, 0));

        //Créez la grille d'items
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setStyle("-fx-border-width: 2px; -fx-border-color: white; -fx-border-radius: 10; -fx-background-color: rgb(56,52,50);");
        BorderPane.setMargin(gridPane, new Insets(0, 10, 0, 0));

        VBox ItemInfo = new VBox();
        ItemInfo.setPrefSize(300, 200);
        ItemInfo.setMaxSize(300, 200);
        ItemInfo.setStyle("-fx-border-width: 2px; -fx-border-color: white; -fx-border-radius: 10; -fx-background-color: rgb(56,52,50);");

        int row = 0;
        int compteur = 0;
        for(AbstractItem item : items) {	
            if (item != null) {
                ImageView item_view = item.getImageView();
                item_view.setFitHeight(50);
                item_view.setFitWidth(50);
                StackPane itemBox = new StackPane(item_view);
                itemBox.setStyle("-fx-border-width: 2px;-fx-border-color: white; -fx-border-radius: 10; -fx-background-color: rgb(56,52,50);");
                
                
                itemBox.setOnMouseClicked(event ->{
                    ItemInfo.getChildren().clear();
                    Text itemName = new Text(item.getName());
                    itemName.setFont(Font.font("Nom de la police", FontWeight.BOLD, 15));
                    itemName.setFill(Color.WHITE);
                    VBox.setMargin(itemName, new Insets(0, 0, 0, 10));
                    ImageView itemView = new ImageView(item.getImageView().getImage().getUrl());
                    Text itemDescription = new Text(item.getDescription());
                    itemDescription.setFill(Color.WHITE);
                    StackPane descBox = new StackPane(itemDescription);
                    descBox.setStyle("-fx-border-width: 2px; -fx-border-color: rgb(56,52,50); -fx-border-radius: 10; -fx-background-color:  rgb(56,52,68);");
                    VBox.setMargin(itemDescription, new Insets(0, 0, 0, 10));
                    
                    UseButtonInventory itemBtn = new UseButtonInventory(item);
                    itemBtn.setStyle("-fx-background-color: rgb(56,52,68); -fx-text-fill: white; -fx-border-color: white; -fx-border-width: 2px; -fx-border-radius: 5;");
         
                    VBox.setMargin(itemBtn, new Insets(10, 0, 0, 10));
                    
                    
                    itemBtn.setOnAction(e -> {
                    	switch(item.getBtnText()) {
                    	case"Drink":
                    		itemBtn.pushButton(player,map);
                            this.remove(item, player,map);
                    		break;
                    	default:
                    		break;
                    	}
       
                        
                    });
                    
                    
                    itemView.setFitHeight(100);
                    itemView.setFitWidth(100);
                    
                    Separator separator = new Separator();
                    separator.setPrefWidth(ItemInfo.getWidth());
                    separator.getStyleClass().add("-fx-border-style: solid; -fx-border-width: 0 0 1 0; -fx-border-color: white;");
                    
                    ItemInfo.getChildren().add(itemName);
                    ItemInfo.getChildren().add(separator);
                    ItemInfo.getChildren().add(itemView);
                    ItemInfo.getChildren().add(descBox);
                    ItemInfo.getChildren().add(itemBtn);
                });
                gridPane.add(itemBox, compteur%4, row);
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
	}
	
	public void remove(AbstractItem item,Player player,Map map) {
		
		int compteur = 0;
		
		for(AbstractItem itemIn : this.items) {
			if (itemIn == item) {
				for(int i = compteur; i < this.getNbItem();i++) {
					this.getItem()[i] = this.getItem()[i+1];
				}
			}
			compteur++;
		}
		this.nbItem--;;
		this.updateStageInventory(player,map);
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
