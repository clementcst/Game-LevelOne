package game.inventory;

import game.character.Player;
import game.item.AbstractItem;
import game.map.Map;
import javafx.scene.control.Button;

public class UseButtonInventory extends Button {
	private String itemName;
	
	
	public UseButtonInventory(AbstractItem item) {
		this.itemName = item.getName();
		this.setText(item.getBtnText());
	}
	
	public void pushButton(Player player, Map map) {
		switch(this.itemName) {
		case("potionBlue"):
			player.setHealth(player.getHealth()+2,map);
			break;
		case("potionRed"):
			player.getWeapon().setDamage(player.getWeapon().getDamage()+1);
			break;
		
		default:
			break;
		}
	}

	
	
}
