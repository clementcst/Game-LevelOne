package game.item;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class AbstractItem implements Item {
	protected String name;
	protected  String description;
	protected  ImageView imageView;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public ImageView getImageView() {
		return imageView;
	}
	public void setImageView(String ImagePath) {
		this.imageView = new ImageView(new Image(ImagePath));
	}
	
	
	
}
