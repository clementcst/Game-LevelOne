package game.character;

import game.inventory.Inventory;
import game.item.Weapon;
import game.map.Map;
import game.pnj.Monster;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

/**
 * Classe repr�sentant le joueur.
 */
public class Player {

    private double x;
    private double y;

    private String name;
    private int health;
    private int money;
    private Weapon weapon;
    private ImageView sprite;
    private Inventory inventory;
    private boolean isAttacking;
    private boolean canBeHurt;

	private Timeline animationRight;
    private Timeline animationUp;
    private Timeline animationLeft;
    private Timeline animationDown;

    private Timeline animationAttackRight;
    private Timeline animationAttackLeft;

    /**
     * Constructeur de la classe Player.
     *
     * @param name       Nom du joueur.
     * @param health     Points de vie du joueur.
     * @param money      Montant d'argent du joueur.
     * @param spritePath Chemin vers l'image repr�sentant le joueur.
     * @param map        Carte du jeu.
     */
    public Player(String name, int health, int money, String spritePath, Map map) {
        // Initialisation des animations de mouvement
        this.animationRight = PlayerAnimation.animateCharacterMovement(this, "RIGHT", map);
        this.animationUp = PlayerAnimation.animateCharacterMovement(this, "UP", map);
        this.animationLeft = PlayerAnimation.animateCharacterMovement(this, "LEFT", map);
        this.animationDown = PlayerAnimation.animateCharacterMovement(this, "DOWN", map);

        // Initialisation des animations d'attaque
        this.animationAttackRight = PlayerAnimation.animateCharacterAttack(this, "RIGHT", map);
        this.animationAttackLeft = PlayerAnimation.animateCharacterAttack(this, "LEFT", map);

        this.name = name;
        this.health = health;
        this.money = money;
        this.isAttacking = false;
        this.canBeHurt = true;
        Image image = new Image(spritePath);
        this.sprite = new ImageView(image);
        this.sprite.setFitWidth(40);
        this.sprite.setFitHeight(40);
        this.x = 70;
        this.y = 70;
        this.inventory = new Inventory();
        this.weapon = new Weapon("�p�e basique", 1, "Permet de taper", "equip weapon");
    }

    // Getters et setters

    /**
     * Obtient l'animation d'attaque vers la droite du joueur.
     *
     * @return Animation d'attaque vers la droite.
     */
    public Timeline getAnimationAttackRight() {
        return animationAttackRight;
    }

    /**
     * D�finit l'animation d'attaque vers la droite du joueur.
     *
     * @param animationAttackRight Animation d'attaque vers la droite.
     */
    public void setAnimationAttackRight(Timeline animationAttackRight) {
        this.animationAttackRight = animationAttackRight;
    }

    /**
     * Obtient l'animation d'attaque vers la gauche du joueur.
     *
     * @return Animation d'attaque vers la gauche.
     */
    public Timeline getAnimationAttackLeft() {
        return animationAttackLeft;
    }

    /**
     * D�finit l'animation d'attaque vers la gauche du joueur.
     *
     * @param animationAttackLeft Animation d'attaque vers la gauche.
     */
    public void setAnimationAttackLeft(Timeline animationAttackLeft) {
    	this.animationAttackLeft = animationAttackLeft;
    }
    
    /**
     * Obtient l'animation de d�placement vers la droite du joueur.
     *
     * @return Animation de d�placement vers la droite.
     */
    public Timeline getAnimationRight() {
        return animationRight;
    }

    /**
     * D�finit l'animation de d�placement vers la droite du joueur.
     *
     * @param animationRight Animation de d�placement vers la droite.
     */
    public void setAnimationRight(Timeline animationRight) {
        this.animationRight = animationRight;
    }

    /**
     * Obtient l'animation de d�placement vers le haut du joueur.
     *
     * @return Animation de d�placement vers le haut.
     */
    public Timeline getAnimationUp() {
        return animationUp;
    }

    /**
     * D�finit l'animation de d�placement vers le haut du joueur.
     *
     * @param animationUp Animation de d�placement vers le haut.
     */
    public void setAnimationUp(Timeline animationUp) {
        this.animationUp = animationUp;
    }

    /**
     * Obtient l'animation de d�placement vers la gauche du joueur.
     *
     * @return Animation de d�placement vers la gauche.
     */
    public Timeline getAnimationLeft() {
        return animationLeft;
    }

    /**
     * D�finit l'animation de d�placement vers la gauche du joueur.
     *
     * @param animationLeft Animation de d�placement vers la gauche.
     */
    public void setAnimationLeft(Timeline animationLeft) {
        this.animationLeft = animationLeft;
    }

    /**
     * Obtient l'animation de d�placement vers le bas du joueur.
     *
     * @return Animation de d�placement vers le bas.
     */
    public Timeline getAnimationDown() {
        return animationDown;
    }

    /**
     * D�finit l'animation de d�placement vers le bas du joueur.
     *
     * @param animationDown Animation de d�placement vers le bas.
     */
    public void setAnimationDown(Timeline animationDown) {
        this.animationDown = animationDown;
    }

    /**
     * Obtient le nom du joueur.
     *
     * @return Nom du joueur.
     */
    public String getName() {
        return name;
    }

    /**
     * D�finit le nom du joueur.
     *
     * @param name Nom du joueur.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtient les points de vie du joueur.
     *
     * @return Points de vie du joueur.
     */
    public int getHealth() {
        return health;
    }

    /**
     * D�finit les points de vie du joueur.
     *
     * @param health Points de vie du joueur.
     * @param map    Carte du jeu.
     */
    public void setHealth(int health, Map map) {
        this.health = health;
        map.majLife();
    }

    /**
     * Obtient le montant d'argent du joueur.
     *
     * @return Montant d'argent du joueur.
     */
    public int getMoney() {
        return money;
    }

    /**
     * D�finit le montant d'argent du joueur.
     *
     * @param money Montant d'argent du joueur.
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * Obtient l'arme du joueur.
     *
     * @return Arme du joueur.
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
	 * D�finit l'arme du joueur.
	 *
	 * @param weapon Arme du joueur.
	 */
	public void setWeapon(Weapon weapon) {
	    this.weapon = weapon;
	}
	
	/**
	 * Obtient l'image du joueur.
	 *
	 * @return Image du joueur.
	 */
	public ImageView getSprite() {
	    return sprite;
	}
	
	/**
	 * D�finit l'image du joueur.
	 *
	 * @param imagePath Chemin vers l'image du joueur.
	 */
	public void setSprite(String imagePath) {
	    Image image = new Image(imagePath);
	    sprite.setImage(image);
	}
	
	/**
	 * Obtient l'inventaire du joueur.
	 *
	 * @return Inventaire du joueur.
	 */
	public Inventory getInventory() {
	    return inventory;
	}
	
	/**
	 * D�finit l'inventaire du joueur.
	 *
	 * @param inventory Inventaire du joueur.
	 */
	public void setInventory(Inventory inventory) {
	    this.inventory = inventory;
	}
	
	/**
	 * Obtient l'�tat d'attaque du joueur.
	 *
	 * @return Vrai si le joueur est en train d'attaquer, sinon faux.
	 */
	public boolean isAttacking() {
	    return isAttacking;
	}
	
	/**
	 * D�finit l'�tat d'attaque du joueur.
	 *
	 * @param isAttacking Vrai si le joueur est en train d'attaquer, sinon faux.
	 */
	public void setAttacking(boolean isAttacking) {
	    this.isAttacking = isAttacking;
	}
	
	/**
	 * Obtient la position en X du joueur.
	 *
	 * @return Position en X du joueur.
	 */
	public double getX() {
	    return this.x;
	}
	
	/**
	 * Obtient la position en Y du joueur.
	 *
	 * @return Position en Y du joueur.
	 */
	public double getY() {
	    return this.y;
	}
	
	/**
	 * D�finit la position en X du joueur.
	 *
	 * @param X Position en X du joueur.
	 */
	public void setX(double X) {
	    this.x = X;
	}
	
	/**
	 * D�finit la position en Y du joueur.
	 *
	 * @param Y Position en Y du joueur.
	 */
	public void setY(double Y) {
	    this.y = Y;
	}
	
	/**
	 * V�rifie si le joueur peut �tre bless�.
	 *
	 * @return Vrai si le joueur peut �tre bless�, sinon faux.
	 */
	public boolean canBeHurt() {
	    return canBeHurt;
	}
	
	/**
	 * D�finit si le joueur peut �tre bless�.
	 *
	 * @param canBeHurt Vrai si le joueur peut �tre bless�, sinon faux.
	 */
	public void setCanBeHurt(boolean canBeHurt) {
	    this.canBeHurt = canBeHurt;
	}
	
	/**
	 * Arr�te toutes les animations du joueur.
	 */
	public void stopAnimation() {
	    this.getAnimationDown().stop();
	    this.getAnimationUp().stop();
	    this.getAnimationLeft().stop();
	    this.getAnimationRight().stop();
	}
	
	/**
	 * Anime le joueur lorsqu'il subit des d�g�ts.
	 */
	public void takingDamage() {
	    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200),
	    	event -> {
	    		this.getSprite().setVisible(!this.getSprite().isVisible());
	    	}));
	    timeline.setCycleCount(Timeline.INDEFINITE);
	    this.setCanBeHurt(false); 
	    timeline.play();
	    PauseTransition pause = new PauseTransition(Duration.seconds(1));
	    pause.setOnFinished(
	    	event -> {
			    timeline.stop();
			    this.getSprite().setVisible(true);
			    this.setCanBeHurt(true);
	    	});
	    pause.play();
	}


	/**
	 * Attaque un monstre.
	 *
	 * @param map Map la map du jeu.
	 */
	 public void attack(Map map) {
	    	Image playerImage = this.getSprite().getImage();
	        String playerImagePath = playerImage.getUrl();
	        //Switch selon le nom de l'img (via l'URL)
	        if(playerImagePath.substring(16).contains("moveR") || playerImagePath.substring(16).contains("moveU")) {
	        	this.getAnimationAttackRight().play();
	        }
	        if(playerImagePath.substring(16).contains("moveL") || playerImagePath.substring(16).contains("moveD")) {
	        	this.getAnimationAttackLeft().play();
	        }
	    	Monster m = PlayerCollision.attackCollision(this, map.getGridpanePnj());
	    	if(m != null && m.CanBeHurt()) {
	    		System.out.println("on attaque le monstre "+m.getName());
				m.setHealth(m.getHealth() - this.getWeapon().getDamage());
				System.out.println("PV:"+m.getHealth());
				if(m.getHealth()<=0) { //Si le monstre n'a plus de vie, on le supprime
					m.drop(map);
				}else {
					m.takingDamage();
				}
	    	}
	    } 
}

