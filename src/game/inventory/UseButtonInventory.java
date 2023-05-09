package game.inventory;

import game.character.Player;
import game.map.Map;
import javafx.scene.control.Button;

public class UseButtonInventory extends Button {
	private String itemName;
	
	
	public UseButtonInventory(String itemName, String value) {
		this.itemName = itemName;
		this.setText(value);
	}
	
	public void pushButton(Player player, Map map) {
		switch(this.itemName) {
		case("potionBlue"):
			player.setHealth(player.getHealth()+2,map);
			break;
		
		default:
			break;
		}
	}

	
	
}
