package game.inventory;

import game.item.AbstractItem;
import game.item.Weapon;
import game.textures.Constants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
/*import javafx.collections.FXCollections;*/
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
/*import javafx.scene.control.SelectionMode;*/
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Inventory {
	
	//instances de l'inventaire pour la partie graphique 
	private Stage stage;
	private BorderPane borderPane;
	//private Album album;
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
		
		
        //gridPane.setFillHeight(gridPane, null);
        //for()
        /*ImageView Adam = new ImageView(new Image(Constants.topBorder.getImagePath()));
        Adam.setFitHeight(50);
        Adam.setFitWidth(50);
        gridPane.add(Adam, 0, 0);*/
        
        /*for (int col = 0; col < 4; col++) {
    		for (int row = 0; row < 3; row++) {
    			Region region = new Region();
    			region.setPrefSize(50, 50);
    			region.setMinSize(50,50);
    			region.setMaxSize(50,50);
    			region.setStyle("-fx-border-width: 1px; -fx-border-color: black;");
    			gridPane.add(region, col, row);
    		}
		}*/
		this.borderPane = new BorderPane();
		this.borderPane.setMaxSize(600, 400);
		this.updateBorderPane();
		
		
		this.stage = new Stage();
		this.stage.setTitle("Inventaire");
		this.stage.setScene(new Scene(this.borderPane));
		this.stage.setResizable(false);
		this.stage.sizeToScene();
		this.stage.initModality(Modality.APPLICATION_MODAL);
	}
	
	public void updateBorderPane() {
		VBox infoPlayer = new VBox();
		infoPlayer.setPrefHeight(50);
		infoPlayer.setPrefWidth(500);
		infoPlayer.setStyle("-fx-border-width: 1px; -fx-border-color: red;");
		
		
		//Créez la grille d'items
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        
        System.out.println(this.nbItem);
        
        for (int col = 0; col < 4; col++) {
    		for (int row = 0; row < 3; row++) {
    			Region region = new Region();
    			region.setPrefSize(50, 50);
    			region.setMinSize(50,50);
    			region.setMaxSize(50,50);
    			region.setStyle("-fx-border-width: 1px; -fx-border-color: black;");
    			gridPane.add(region, col, row);
    		}
		}
        
        int col = 0;
		int row = 0;
		int compteur = 0;
        for(AbstractItem item : items) {
        	if (item != null) {
	        	ImageView item_view = item.getImageView();
	        	item_view.setFitHeight(50);
	        	item_view.setFitWidth(50);
	        	item_view.setStyle("-fx-border-width: 1px; -fx-border-color: black;");
	        	if(compteur == 0) gridPane.add(item_view, 0, 0);
	        	else {
	        		gridPane.add(item_view, compteur%4, row);
	        	}
	        	compteur++;
	        	
	        	if(compteur%4 == 0) {
	        		row++;
	        	}
        	}
        }
        
        VBox ItemInfo = new VBox();
        Text nom_item = new Text("Bonjour, comment allez-vous ?");
        ItemInfo.getChildren().add(nom_item);
        ItemInfo.getChildren().add(new ImageView(new Image(Constants.topBorder.getImagePath())));
        Text description_item = new Text("Desc");
        ItemInfo.getChildren().add(description_item);
        ItemInfo.setPrefSize(300, 200);
        ItemInfo.setStyle("-fx-border-width: 1px; -fx-border-color: blue;");

		
		//constructeur partie graphique
		this.borderPane.setTop(infoPlayer);
		this.borderPane.setCenter(gridPane);
		this.borderPane.setRight(ItemInfo);
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
	
	/*public void updateBorderPane() {
		String [] name_items = new String[6];
		for(int i = 0 ; i < 6; i++) {
			name_items[i] = this.items[i].getName();
		}
		
		ObservableList<String> items = FXCollections.observableArrayList();
		
		items.addAll(name_items);
		ListView<String> liste = new ListView<String>(items);
		
		liste.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent event) {
		        String selectedItem = liste.getSelectionModel().getSelectedItem();
		        //creerCentre(selectedItem);
		        getBorderPane().setCenter(new Pane(creerCentre(selectedItem)));
		        //System.out.println(selectedItem);
		    }
		});

		
		this.borderPane.setLeft(liste);
		//this.borderPane.setCenter(new Pane(creerCentre()));
	}*/
	
	


	public HBox creerCentre(String imageName) {
		this.centre = new ImageView(new Image("file:res/inventory/"+imageName+".jpg"));
		this.centre.setFitWidth(400);
		this.centre.setFitHeight(400);
		HBox hb = new HBox(this.centre);
		hb.setPrefSize(600, 450);
		return hb;
	}

	public void push(AbstractItem item) {
		this.items[this.nbItem] = item;
		this.nbItem++;
		System.out.println("on ajoute dans l'inventaire");
		
		/*ListView<String> liste = creerListe();
		liste.setPrefWidth(150);
		liste.setPrefHeight(400);*/
		
		//this.borderPane.setLeft(creerListe());
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
