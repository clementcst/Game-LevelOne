package game.textures;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Texture {
	private String imagePath;
	private Image image;
	
	
	public Texture(String imagePath) {
		this.imagePath=imagePath;
		this.image= new Image(this.imagePath);
	}

	public String getImagePath() {
		return imagePath;
	}

	
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	

	public ImageView getImageView() {
		ImageView imageView = new ImageView(this.image);
		imageView.setFitWidth(32);
		imageView.setFitHeight(32);
        return imageView;
	}
}
