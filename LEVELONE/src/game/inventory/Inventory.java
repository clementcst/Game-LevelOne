package game.inventory;

import game.item.AbstractItem;
import game.item.Weapon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
/*import javafx.collections.FXCollections;*/
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
/*import javafx.scene.control.SelectionMode;*/
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Inventory {
	
	//instances de l'inventaire pour la partie graphique 
	private Stage stage;
	private BorderPane borderPane;
	//private Album album;
	private ImageView centre;
	
	//instances de l'inventaire pour la gestion des items
	protected AbstractItem[] item;
	protected int nbItem;
	
	
	public Inventory() {	
		//constructeur partie items
		this.nbItem = 0;
		this.item = new AbstractItem[6];
		
		for(int i = 0 ; i < 6; i++) {
			this.item[i] =(AbstractItem) new Weapon("");
		}
		
		//constructeur partie graphique
		this.borderPane = new BorderPane();
		this.borderPane.setMaxSize(600, 400);
		this.borderPane.setCenter(new Pane(creerCentre("manchots")));
		
		
		this.stage = new Stage();
		this.stage.setTitle("Album photo");
		this.stage.setScene(new Scene(this.borderPane));
		this.stage.setResizable(false);
		this.stage.sizeToScene();
		this.stage.initModality(Modality.APPLICATION_MODAL);
	}
	
	//getters setters partie items 
	public AbstractItem[] getItem() {
		return item;
	}

	public void setItem(AbstractItem[] item) {
		this.item = item;
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
	
	public void updateBorderPane() {
		String [] name_items = new String[6];
		for(int i = 0 ; i < 6; i++) {
			name_items[i] = this.item[i].getName();
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
	}


	public HBox creerCentre(String imageName) {
		this.centre = new ImageView(new Image("file:res/inventory/"+imageName+".jpg"));
		this.centre.setFitWidth(400);
		this.centre.setFitHeight(400);
		HBox hb = new HBox(this.centre);
		hb.setPrefSize(600, 450);
		return hb;
	}
	
	
	
	public void push(AbstractItem item) {
		this.item[this.nbItem] = item;
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
			System.out.println(item[i].getName());
		}
	}
	
}
