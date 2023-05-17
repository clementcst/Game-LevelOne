package game.pnj;

import game.textures.Texture;
import javafx.scene.image.ImageView;

public class Merchant extends AbstractPnj {
	private ImageView imageView;

	public Merchant(String name, Texture image) {
		super(name, image, false);
		this.imageView = image.getImageView();
		this.imageView.setFitHeight(30);
		this.imageView.setFitWidth(30);
		
	}

	public ImageView getImageView() {
		return imageView;
	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}

}
