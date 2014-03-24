package ModelPkg;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public abstract class Animal {

    private Point position;
    private Integer[] meanStats;
    private Integer[] mainStats;
    private String name;
    //NameGen = génération du nom ex: Eustache IIème du nom
    private int nameGen;
    private int health;
    private Smell smell;
    private String species;


    //The stats are paired by opposition. One plus the opposed should be 25 before any bonuses.
    private int speed;
    private int endurance;

    private int attack;
    private int defence;

    private int smellSensitivity;
    private int smellStrength;

    private int grabQuantity;
<<<<<<< HEAD
    private int team; // -1: player, 1: enemy 1, 2: enemy 2
    private int action =1; // par défaut, ils cherchent de la nourriture
=======
    private int equipQuantity;

    private int team; // -1: player, 1: enemy 1, 2: enemy 2
    private String spriteName;
    private Image sprite;
>>>>>>> origin/View-test-branch

    public Animal(int team, Integer[] meanStats, String species){
    //Création du nom de l'animal
        Random random = new Random(20);
        int noName = random.nextInt();
        this.name = Name.getName(noName);
        this.nameGen = Name.getGen(noName);
        this.team = team;
        this.meanStats=meanStats;
        this.mainStats=rollStats();

        this.speed=mainStats[0];
        this.endurance=25-mainStats[0];
        this.attack=mainStats[1];
        this.defence=25-mainStats[1];
        this.smellSensitivity=mainStats[2];
        this.smellStrength=25-mainStats[2];
        this.grabQuantity=mainStats[3];
        this.equipQuantity=25-mainStats[3];

        sprite=Toolkit.getDefaultToolkit().getImage(species+".gif");
    }

    private Integer[] rollStats(){
        Integer[] finalStats= {0,0,0,0};
        Random coinFlip = new Random();
        for (int i=0; i<meanStats.length; i++){
            if (coinFlip.nextBoolean()){
                finalStats[i]=meanStats[i];
            }
            else{
                int multiplier=0;
                int numberOfFailures=1;
                boolean coinFlipFail=true;
                if(!coinFlip.nextBoolean()){
                    multiplier=-1;
                }
                else{
                    multiplier=1;
                }
                while (coinFlipFail){
                    numberOfFailures++;
                    coinFlipFail=coinFlip.nextBoolean();
                }
                finalStats[i]=meanStats[i]+multiplier*numberOfFailures;
                if (finalStats[i]<1){
                    finalStats[i]=1;
                }
                else if(finalStats[i]>25){
                    finalStats[i]=25;
                }
            }
        }
        return finalStats;
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
    public void setAction(Case [][] table){
        //Doit déterminé si l'animal veut soit trouver de la nourriture, soit un ennemi, soit la base ect.
        //Pour le moment, l'action par défault est 1:Chercher de la nourriture

    }
    public int getAction(){
        return(action);
    }

    public void setMouvement(){
        Point direction;
        boolean doItAgain = flase;
        //Si Behavior est appelé et qu'il retourne 0,0, alors on rappel Behavior avec la méthode Drunk pour qu'il retourne un mouvement aléatoire
        do{
            if(!doItAgain){
            direction = Behavior.search(table,this.smellSensitivity,action); //TODO Création d'un tableau 2D avec les senteurs à proximité de l'animal
            doItAgain = !doItAgain;
            }
            else{
             direction = Behavior.drunk();
            }
            }while(direction.getX() != 0 && direction.getY() != 0);
    }

    public void attack(Point location,Case [][] table){
        //Vérification qu'il y a toujours un ennemi à côté de lui
        for(int ligne = 0; ligne < 3; ligne++){
            for(int colonne = 0; colonne < 3 ; colonne++){
                //Si la senteur de la case est un ennemi et que l'odeur est à 100, cela signifie qu'il y a un ennemi à côté de lui
                if(table[ligne][colonne].getSortedSmellArrayList().get(0).getType() ==2 && table[ligne][colonne].getSortedSmellArrayList().get(0).getIntensity() ==100);
            }
        }

        Animal animal = MapData.getCase(location).getOccupant();
        int damageAmount = (int)Math.ceil(this.attack/animal.getDefence());               //TODO Balancing of this algorithm
        animal.getHit(damageAmount);


    }

    public void grab(Point location){

    }

    public void drop(Point location){

    }

    public void getHit(int amount){
        this.health-= amount;
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
