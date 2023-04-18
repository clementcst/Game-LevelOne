package game.inventory;

import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Inventory {
	//instances de l'inventaire pour la partie graphique 
	private static BorderPane borderPane;
	private Album album;
	private ImageView centre;
	
	//instances de l'inventaire pour la gestion des items
	
	
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
	
	public BorderPane getBorderPane() {
		return borderPane;
	}
	
	public void setBorderPane(BorderPane borderPane) {
		Inventory.borderPane = borderPane;
	}

	public Inventory() {
		album = new Album("res/inventory");
		Inventory.borderPane = new BorderPane();
		Inventory.borderPane.setMaxSize(600, 400);

		Inventory.borderPane.setCenter(new Pane(creerCentre()));
			
		ListView<String> liste = creerListe();
		liste.setPrefWidth(150);
		liste.setPrefHeight(400);
		Inventory.borderPane.setLeft(liste);
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
		String[] noms = new String[album.getSize()];
		for (int i = 0; i < noms.length; i++) {
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
	
}
