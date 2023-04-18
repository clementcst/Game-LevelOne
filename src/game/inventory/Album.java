package game.inventory;



import java.io.File;
import java.util.ArrayList;

import game.item.Item;

public class Album {

	private ArrayList<Photo> lesPhotos; // bibliothèque d'images
	private int indexCourant; // index de l'image courante dans l'inventaire

	/**
	 * Initalise l'album en rajoutant les photos correspondantes a tous les fichiers
	 * du dossier repertoire
	 * 
	 * @param repertoire
	 *            le dossier contenant les photos de l'album
	 */
	public Album(Inventory inventory) {
		this.lesPhotos = new ArrayList<Photo>();
		File repImages = new File("res/inventory");
		for(Item item : inventory.getItem()) {
			for (String s : repImages.list()) {
				if (item.getName() != "" && s.contains(item.getName())) {
					this.addPhoto("file:res/inventory/"+s);
				}
			}
		}
		this.indexCourant = 0;
	}

	/**
	 * Retourne le nombre de photos dans l'album
	 * 
	 * @return le nombre de photos dans l'album
	 */
	public int getSize() {
		return this.lesPhotos.size();
	}

	/**
	 * Ajoute une photo dans l'album
	 * 
	 * @param fullpathname
	 *            chemin complet du fichier
	 */
	public void addPhoto(String fullpathname) {
		this.lesPhotos.add(new Photo(fullpathname));
	}

	/**
	 * Retourne la photo d'index i si i est dans [0, this.getSize()[. Sinon,
	 * retourne null.
	 * 
	 * @return la photo d'index i si i est dans [0, this.getSize()[. Sinon, retourne
	 *         null.
	 * @param i
	 *            l'index de la photo a retourner
	 */
	public Photo getPhoto(int i) {
		if ((i < this.lesPhotos.size()) && (i >= 0)) {
			return this.lesPhotos.get(i);
		} else {
			return null;
		}
	}

	/**
	 * Retourne l'index de la photo courante de l'album
	 * 
	 * @return l'index de la photo courante de l'album
	 */
	public int getIndexCourant() {
		return this.indexCourant;
	}

	/**
	 * Modifie la photo courante
	 * 
	 * @param cindex
	 *            l'index de la photo qui devient la photo courante
	 */
	public void setIndexCourant(int cindex) {
		if ((cindex < this.lesPhotos.size()) && (cindex >= 0)) {
			this.indexCourant = cindex;
		}
	}

	/**
	 * Retourne la photo courante de l'album.
	 * 
	 * @return la photo courante de l'album.
	 */
	public Photo getPhotoCourante() {
		return this.lesPhotos.get(indexCourant);
	}

	/**
	 * redimensionne la photo courante de l'album avec zoom pour facteur de zoom.
	 * 
	 * @param zoom
	 *            le facteur de zoom a appliquer a la photo courante
	 */
	/*public void redimensionnerPhotoCourante(Float zoom) {
		this.getPhotoCourante().redimensionner(zoom);
	}*/

}