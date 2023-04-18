package game.character;

//Classe pour représenter les monstres
public class Monster {
 private String name;
 private int health;
 private int strength;
 private boolean isMoving;
 private boolean hasVision;

 public Monster(String name, int health, int strength, boolean isMoving, boolean hasVision) {
     this.name = name;
     this.health = health;
     this.strength = strength;
     this.isMoving = isMoving;
     this.hasVision = hasVision;
 }

 // Getters et setters
 public String getName() {
     return name;
 }

 public void setName(String name) {
     this.name = name;
 }

 public int getHealth() {
     return health;
 }

 public void setHealth(int health) {
     this.health = health;
 }

 public int getStrength() {
     return strength;
 }

 public void setStrength(int strength) {
     this.strength = strength;
 }

 public boolean isMoving() {
     return isMoving;
 }

 public void setMoving(boolean moving) {
     isMoving = moving;
 }

 public boolean hasVision() {
     return hasVision;
 }

 public void setHasVision(boolean hasVision) {
     this.hasVision = hasVision;
 }

 // Comportements des monstres
 public void move() {
     // Code pour déplacer le monstre
 }

 public void attack(Player player) {
     // Code pour attaquer le joueur
 }

 // ...
}