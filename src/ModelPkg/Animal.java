package ModelPkg;

import ControllerPkg.MasterController;

import java.awt.*;
import java.util.Random;

public abstract class Animal {

    private boolean wantsToGoHome=false;
    private int activationFrequency;
    private int birthday;
    private Point position;
    private Case occupiedCase;
    private int[] meanStats;
    private int[] mainStats;
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
    private int carriedFood = 0;
    private int equipQuantity;

    private int team; // -1: player, 1: enemy 1, 2: enemy 2
    private int action =1; // par défaut, ils cherchent de la nourriture

    private String spriteName;
    private Image sprite;

    private int behaviorID;


    public Animal(int team, int[] meanStats, String species, Point startingPosition){
    //Création du nom de l'animal
        this.birthday= MasterController.getTime();
        Random random = new Random();
        int noName = random.nextInt(20);
        this.name = Name.getName(noName);
        this.nameGen = Name.getGen(noName);

        this.name +=" le "+this.nameGen;
        System.out.println(this.name);

        this.team = team;
        this.meanStats=meanStats;
        this.mainStats=rollStats();

        this.position=startingPosition;
        this.occupiedCase=MapData.getCase(position);
        this.speed=mainStats[0];
        this.activationFrequency=480/this.speed;
        this.endurance=25-mainStats[0];
        this.health = 100;
        this.attack=mainStats[1];
        this.defence=25-mainStats[1];
        this.smellSensitivity=mainStats[2];
        this.smellStrength=25-mainStats[2];
        this.grabQuantity=mainStats[3];
        this.equipQuantity=25-mainStats[3];


        System.out.println("stats:");
        System.out.println("speed: "+speed);
        System.out.println("endurance: "+endurance);
        sprite=Toolkit.getDefaultToolkit().getImage("IMG/"+species+".png");
    }

    private int[] rollStats(){
        int[] finalStats= {0,0,0,0};
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

    public void grab(Point location){

    }

    public void drop(Point location){

    }

    public void decreaseHealth(int amount){
        this.health-= amount;
        if (health<=0){
            this.isDead();
            MasterController.disposeAnimal(this);
            System.out.println("I am dead.");
        }
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

    public Image getSprite() {
        return sprite;
    }

    public void eatFood(){
        if (MapData.getCase(position).getWildObject().getType()==7){
            //eatFoodInCase(MapData.getCase(position));
        }
        if (health!=100){
            //eatCarriedFood();
        }
    }

    public void activateAsFoe(){

    }

    public void activate(int time){
        //TODO reset smellSource of case
        if (time!=birthday&&(time-birthday)%activationFrequency==0){
            System.out.println(position);
            Case[][] subsection=MapData.getSubsection2(position);
            //TODO balance health decrease/activation
            //TODO remove once done testing
            //System.out.println("C'est mon tour!Il est: "+time);
            //System.out.println(birthday);
            //System.out.println(speed);
            //System.out.println(activationFrequency);
            if (team!=1){
                activateAsFoe();
            }
            else{
            if (carriedFood!=0){
                wantsToGoHome=true;
            }
            else{
                wantsToGoHome=false;
            }
            if (health<=25){
                //L'animal est particulièrement en mauvais état et doit se guérrir.
                if (carriedFood!=0){
                    while(health<100&&carriedFood!=0){
                        carriedFood-=1;
                        health+=5;
                    }
                }
                //if case contient nourriture
                if (occupiedCase.caseContains("food")&&health!=100){
                    //TODO eat food from case
                }
                for (int i=0; i<3; i++){
                    for (int j=0; j<3; j++){
                        if (subsection[i][j].caseContains("food")){
                            //TODO add this case to an array, once all checked, move to one with MOST food.
                        }
                    }
                }
            }

            //If not unhealthy
            else{
                if (wantsToGoHome){
                    //TODO
                    /*
                    If smells home, go where it smells most.
                    If it smells friends, go where it smells most many, but least intense
                    If it smells only himself, follow own tracks (least intense, not last case it was in)
                    50% added chance of failing morale
                     */
            }
                else{
                    //TODO
                    /*
                    depends of adjacent cases contents:

                    If two or more enemies and at least one adjacent friend:
                        roll morale if succeeds, attack enemy with weakest health

                    Else if two or more ennemies and no ally:
                        Flee from enemy with most HP/flee both if possible?

                    Else if at least one item:
                        Go to item, get item
                    Else if a single enemy:
                        roll morale, if succeeds, attack enemy, else, flee enemy
                    Else if other cases smell something other than his own odor:
                        only smells enemies:
                            roll for morale, if succeed, follow strongest smell, set behavior to attack
                     */
                }
            if (MapData.getCase(position).getWildObject().getType()==7&&carriedFood<grabQuantity){
                //TODO pick up as much food as possible, remove wildObject as needed
            }
            }
        }
            decreaseHealth((25-endurance)/2);
    }
}
    public boolean isDead(){
        if (health<=0){
            return true;
        }
        else {
            return false;
        }
    }

    public Case getOccupiedCase() {
        return occupiedCase;
    }
}