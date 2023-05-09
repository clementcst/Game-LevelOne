package game.item;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class AbstractItem{
	protected String name;
	protected String description;
	protected ImageView imageView;
	
	public AbstractItem(String name, String description) {
		this.name = name;
		this.description = description;
		this.imageView = new ImageView(new Image("file:res/images/"+name+".png"));
		this.imageView.setFitHeight(16);
		this.imageView.setFitWidth(16);
		this.imageView.setUserData(this);
	}
	
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
