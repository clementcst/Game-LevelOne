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
	
	public Texture(String image,int width, int height) {
		this.imagePath="file:res/images/"+image;
		this.imageView = new ImageView(new Image(this.imagePath));
		imageView.setFitWidth(width);
		imageView.setFitHeight(height);
	}

	public ImageView getImageView() {
		ImageView imageView = new ImageView(new Image(this.imagePath));
		imageView.setFitWidth(this.imageView.getFitWidth());
		imageView.setFitHeight(this.imageView.getFitHeight());
		return imageView;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	

	

}
