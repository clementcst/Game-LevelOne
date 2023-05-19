package game.pnj;

import game.textures.Texture;


public abstract class AbstractPnj {
	protected String name;
	protected boolean isMoving;
	protected boolean canBeHurt;	
	
	public AbstractPnj(String name, Texture image, boolean hurt) {
		this.name = name;
		this.isMoving = false;
		this.canBeHurt = hurt;
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

	public boolean CanBeHurt() {
		return canBeHurt;
	}

	public void setCanBeHurt(boolean canBeHurt) {
		this.canBeHurt = canBeHurt;
	}
	
	

}
