package game.inventory;

import game.character.Player;
import javafx.scene.control.Button;

public class UseButtonInventory extends Button {
	private String itemName;
	
	
	public UseButtonInventory(String itemName, String value) {
		this.itemName = itemName;
		this.setText(value);
	}
	
	public void pushButton(Player player) {
		switch(this.itemName) {
		case("potionBlue"):
			player.setHealth(player.getHealth()+2);
			break;
		
		default:
			break;
		}
	}

	
	
}
