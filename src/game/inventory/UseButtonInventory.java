package game.inventory;

import game.character.Player;
import game.item.AbstractItem;
import game.map.Map;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import utils.Minuteur;
import utils.MinuteurMonsterStop;

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
			if(player.getHealth()+2>20) {
				player.setHealth(20, map);
			}else {
				player.setHealth(player.getHealth()+2,map);
			}
			break;
		case("potionGreen"):
			Minuteur.addTime(+50);
			break;
		case("potionPurple"):
			MinuteurMonsterStop minuteur2 = new MinuteurMonsterStop(map);
    		map.getGameInfo().add(minuteur2,34,0);
    		GridPane.setColumnSpan(minuteur2,3);
    		minuteur2.start(20);
			break;
		case("potionYellow"):
			//a faire Jordan ajoute invicibilité
			break;
		default:
			break;
		}
	}

	
	
}
