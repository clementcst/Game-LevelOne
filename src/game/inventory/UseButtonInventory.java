package game.inventory;

import game.character.Player;
import game.item.AbstractItem;
import game.map.Map;
import javafx.scene.control.Button;
import utils.Minuteur;

public class UseButtonInventory extends Button {
	private String itemName;
	
	
	public UseButtonInventory(AbstractItem item) {
		this.itemName = item.getName();
		this.setText(item.getBtnText());
	}
	
	public void pushButton(Player player, Map map) {
		switch(this.itemName) {
		case("potionBlue"):
			player.getWeapon().setDamage(player.getWeapon().getDamage()+1);
			break;
		case("potionRed"):
			player.setHealth(player.getHealth()+2,map);
			break;
		case("potionGreen"):
			Minuteur.addTime(+50);
			break;
		default:
			break;
		}
	}

	
	
}
