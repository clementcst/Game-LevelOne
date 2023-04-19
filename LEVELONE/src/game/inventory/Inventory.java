package game.inventory;

import game.item.Item;
import game.item.Potion;
import game.item.Weapon;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Inventory {
	
	//instances de l'inventaire pour la partie graphique 
	private Stage stage;
	private BorderPane borderPane;
	private Album album;
	private ImageView centre;
	
	//instances de l'inventaire pour la gestion des items
	protected Item[] item;
	protected int nbItem;
	
	
	public Inventory() {	
		//constructeur partie items
		this.nbItem = 0;
		this.item = new Item[5];
		for(int i = 0 ; i < 5; i++) {
			this.item[i] =(Item) new Weapon("");
		}
		
		//this.push(new Weapon("épée"));
		this.push(new Potion("potion"));
		/*this.push(new Weapon("dague"));
		this.push(new Weapon("épée"));*/
		
		//constructeur partie graphique
		this.album = new Album(this);
		this.borderPane = new BorderPane();
		this.borderPane.setMaxSize(600, 400);

		this.borderPane.setCenter(new Pane(creerCentre()));
					
		ListView<String> liste = creerListe();
		liste.setPrefWidth(150);
		liste.setPrefHeight(400);
		
		this.borderPane.setLeft(liste);
		
		this.stage = new Stage();
		this.stage.setTitle("Album photo");
		this.stage.setScene(new Scene(this.borderPane));
		this.stage.setResizable(false);
		this.stage.sizeToScene();
		this.stage.initModality(Modality.APPLICATION_MODAL);
	}
	
	//getters setters partie items 
	public Item[] getItem() {
		return item;
	}

	public void setItem(Item[] item) {
		this.item = item;
	}

	public int getNbItem() {
		return nbItem;
	}

	public void setNbItem(int nbItem) {
		this.nbItem = nbItem;
	}

	//getters setter partie graphique
	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
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
	
	public void setBorderPane(BorderPane borderPane) {
		this.borderPane = borderPane;
	}


	public HBox creerCentre() {
		this.centre = new ImageView(album.getPhotoCourante().getImage());
		this.centre.setFitWidth(400);
		this.centre.setFitHeight(400);
		HBox hb = new HBox(this.centre);
		hb.setPrefSize(600, 450);
		return hb;
	}
	
	
	private ListView<String> creerListe() {

		String[] noms = new String[this.nbItem];
		for (int i = 0; i < this.nbItem; i++) {
			noms[i] = album.getPhoto(i).getNom();
		}
		
		ListView<String> liste = new ListView<>(FXCollections.observableArrayList(noms));
		liste.getSelectionModel().select(album.getIndexCourant());
		liste.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		liste.getSelectionModel().selectedItemProperty().addListener(ov -> {
			album.setIndexCourant(liste.getSelectionModel().getSelectedIndex());
			this.centre.setImage(album.getPhotoCourante().getImage());
			this.centre.setFitWidth(400);
			this.centre.setFitHeight(400);
		});
		return liste;
	}
	
	
	public void push(Item item) {
		this.item[this.nbItem] = item;
		this.nbItem++;
		
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
