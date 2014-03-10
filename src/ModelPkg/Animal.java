package ModelPkg;

import java.awt.*;
import java.util.Random;

public abstract class Animal {

    private Point position;
    private String name;
    //NameGen = génération du nom ex: Eustache IIème du nom
    private int nameGen;
    private int health;
    private int speed;
    private int attack;
    private Smell smell;
    private int smellSensitivity;
    private int smellStrength;
    private int defence;
    private int endurance;
    private int grabQuantity;
    private int team; // -1: player, 1: enemy 1, 2: enemy 2


    public Animal(int team){
    //Création du nom de l'animal
        Random random = new Random(20);
        int noName = random.nextInt();
        this.name = Name.getName(noName);
        this.nameGen = Name.getGen(noName);
        this.team = team;

    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public  void smellSurroundings(){

    }

    public void attact(Point location){

    }

    public void grab(Point location){

    }

    public void drop(Point location){

    }

    public Point getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public int getNameGen() {
        return nameGen;
    }

    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }

    public int getAttack() {
        return attack;
    }

    public int getSmellSensitivity() {
        return smellSensitivity;
    }

    public int getSmellStrength() {
        return smellStrength;
    }

    public int getDefence() {
        return defence;
    }

    public int getEndurance() {
        return endurance;
    }

    public int getGrabQuantity() {
        return grabQuantity;
    }

    public Smell getSmell() {
        return smell;
    }

}
