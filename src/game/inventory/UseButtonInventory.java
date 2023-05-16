package game.inventory;

import game.character.Player;
import game.item.AbstractItem;
import game.map.Map;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.GridPane;
import utils.Minuteur;
import utils.MinuteurInvincibility;
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
			MinuteurInvincibility otherMinuteur = new MinuteurInvincibility(map);
			map.getGameInfo().add(otherMinuteur,32,0);
			GridPane.setColumnSpan(otherMinuteur,3);
			otherMinuteur.start(15);

			ColorAdjust colorAdjust = new ColorAdjust();
			colorAdjust.setHue(0.8888);
			
			// Valeur de teinte pour le jaune (60 degrés sur une échelle de 0 à 1)
			colorAdjust.setSaturation(1); // Saturation à 100%

			// Application de l'effet de filtre de couleur sur l'image
			player.getSprite().setEffect(colorAdjust);
			break;
		default:
			break;
		}
	}

	
	
}
