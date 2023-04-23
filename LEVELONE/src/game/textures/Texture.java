package game.textures;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Texture {
	private String imagePath;
	private ImageView imageView;
	
	
	public Texture(String image) {
		this.imagePath="file:res/images/"+image;
		this.imageView = new ImageView(new Image(this.imagePath));
		imageView.setFitWidth(32);
		imageView.setFitHeight(32);
	}

	public ImageView getImageView() {
		ImageView imageView = new ImageView(new Image(this.imagePath));
		imageView.setFitWidth(32);
		imageView.setFitHeight(32);
		return imageView;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	

	

}
