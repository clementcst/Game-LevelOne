package game.pnj;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import game.textures.Constants;
import game.textures.Texture;


public abstract class AbstractPnj {
	protected String name;
	protected  ImageView imageView;
	protected boolean isMoving;
	
	
	public AbstractPnj(String name, Texture image) {
		this.name = name;
		this.imageView = image.getImageView();
		this.imageView.setFitHeight(32);
		this.imageView.setFitWidth(32);
		this.isMoving = false;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public ImageView getImageView() {
		return imageView;
	}


	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}


	public boolean isMoving() {
		return isMoving;
	}


	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}
	
	

}
