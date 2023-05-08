package game.pnj;

import game.character.Player;
import game.item.AbstractItem;
import game.textures.Constants;
import game.textures.Texture;
import javafx.scene.image.ImageView;

//Classe pour représenter les monstres
public class Monster extends AbstractPnj{
 private int health;
 private int strength;
 private boolean hasVision;
 private  ImageView imageView;

	 public Monster(String name, int health, int strength, boolean hasVision, Texture image) {
		 super(name, image);
		 this.health = health;
		 this.strength = strength;				 
		 this.hasVision = hasVision;
		 this.imageView = image.getImageView();
		 this.imageView.setFitHeight(32);
		 this.imageView.setFitWidth(32);
		 this.imageView.setUserData(this);
	 }
	 

	public ImageView getImageView() {
			return imageView;
	}


	public void setImageView(ImageView imageView) {
			this.imageView = imageView;
	}


	 public int getHealth() {
	     return health;
	 }
	
	 public void setHealth(int health) {
	     this.health = health;
	 }
	
	 public int getStrength() {
	     return strength;
	 }
	
	 public void setStrength(int strength) {
	     this.strength = strength;
	 }
	
	 public boolean hasVision() {
	     return hasVision;
	 }
	
	 public void setHasVision(boolean hasVision) {
	     this.hasVision = hasVision;
	 }
	
	 // Comportements des monstres
	 public void move() {
	     // Code pour déplacer le monstre
	 }
	
	 public void attack(Player player) {
	     // Code pour attaquer le joueur
	 }

 // ...
}