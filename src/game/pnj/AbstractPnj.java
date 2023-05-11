package game.pnj;

import game.textures.Texture;


public abstract class AbstractPnj {
	protected String name;
	protected boolean isMoving;
	
	
	public AbstractPnj(String name, Texture image) {
		this.name = name;
		this.isMoving = false;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public boolean isMoving() {
		return isMoving;
	}


	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}
	
	

}
