package ModelPkg;

import ControllerPkg.MasterController;
import ModelPkg.WildObjects.*;

import java.awt.*;
import java.util.Random;

public abstract class Animal {

    private MasterController masterController;
    private final int MAX_HEALTH = 100;
    private long animalID;
    private Point objective = null;
    private int activationFrequency;
    private int birthday;
    private Point position;
    private Point oldPosition = null;
    private Case occupiedCase;
    private int[] meanStats;
    private int[] mainStats;
    private String name;
    //NameGen = génération du nom ex: Eustache IIème du nom
    private int nameGen;
    private int health;
    private int moral;
    private SmellSource smell;
    private String species;
    private int foodCarried = 0;


    //The stats are paired by opposition. One plus the opposed should be 25 before any bonuses.
    private int speed;
    private int endurance;

    private int attack;
    private int defence;

    private int smellSensitivity;
    private int smellThreshold;
    private int smellStrengthStat;
    private int smellIntensity;

    private int grabQuantity;
    private int carriedFood = 0;
    private int equipQuantity;

    private int team; // -1: player, 1: enemy 1, 2: enemy 2
    private int action =1; // par défaut, ils cherchent de la nourriture

    private String spriteName;
    private Image sprite;

    private MentalStates mentalState = MentalStates.NEUTRAL;
    private ActionTypes actionToCommit = null;

    private boolean toMove = false;


    public Animal(int team, int[] meanStats, String species, Point startingPosition, long animalID, SmellType smellType,final MasterController masterController){
    //Création du nom de l'animal
        this.masterController = masterController;
        this.birthday= MasterController.getTime();
        Random random = new Random();
        int noName = random.nextInt(20);
        this.name = Name.getName(noName);
        this.nameGen = Name.getGen(noName);
        this.animalID = animalID;

        this.name +=" le "+this.nameGen;
        System.out.println(this.name);


        this.team = team;
        this.meanStats=meanStats;
        this.species=species;

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
        this.smellStrengthStat =25-mainStats[2];
        this.grabQuantity=mainStats[3];
        this.equipQuantity=25-mainStats[3];
        this.moral = 25; //TODO determiner comment evaluer le moral;
        //TODO balance this and add a smell
        this.smellIntensity=this.getSmellStrengthStat()*8;
        this.smellThreshold=100/this.getSmellSensitivity();
        //this.smell=new SmellSource(MasterController.getUniqueID(),);



        this.smell = new SmellSource(animalID, this.smellIntensity, this.team, smellType);

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
        System.out.println("Moved");
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
            MasterController.disposeAnimal(this);

            //TODO kill fourmilier
            MapData.addNewsList(this.getName() + " est malheureusement décédé!!");
            masterController.victims();
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

    public int getSmellThreshold() {return smellThreshold;}

    public int getSmellStrengthStat() {
        return smellStrengthStat;
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



    public Image getSprite() {
        return sprite;
    }

    public ActionTypes getActionToCommit() {
        return actionToCommit;
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
        decreaseHealth(((25 - endurance) / 2));
            //TODO reset smellSource of case
            System.out.println("Mon tour");
            VirtualFutureAction virtualFutureAction;
            virtualFutureAction = Behavior.evaluateBestObjective(this.position, this.mentalState, this.moral, this.smellThreshold);
            this.realizeFutureAction(virtualFutureAction);

            if (this.actionToCommit == ActionTypes.GO_TO_LOCATION || this.actionToCommit == ActionTypes.FLEE_TO_LOCATION || this.actionToCommit == ActionTypes.RUN_AT_ENEMY){
                this.toMove = true;
                this.oldPosition = this.position;
                this.position = new Point(this.position.x+objective.x, this.position.y+objective.y);
            }else {
                if (this.actionToCommit == ActionTypes.ATTACK_AT_LOCATION){
                    this.attackOpponent(MapData.getCase(new Point(position.x+objective.x, position.y+objective.y)).getOccupant(), this.attack);
                }else if (this.actionToCommit == ActionTypes.PICKUP_FROM_LOCATION){
                    FoodSource foodSource = ((FoodSource)MapData.getCase(new Point(position.x+objective.x, position.y+objective.y)).getWildObject());
                    int foodAvailable;
                    foodAvailable = foodSource.getFoodQuantity();

                    if (foodAvailable >= this.grabQuantity){
                        this.foodCarried = this.grabQuantity;
                        foodSource.setFoodQuantity(foodAvailable-this.grabQuantity);
                    }else if (foodAvailable < this.grabQuantity){
                        this.foodCarried = foodAvailable;
                        foodSource.setFoodQuantity(0);
                    }

                }else if (this.actionToCommit == ActionTypes.DROP_TO_LOCATION){
                    this.masterController.getPlayerDataController().addFood(this.foodCarried);
                    this.foodCarried = 0;
                }
            }


    }

    private void attackOpponent(Animal animal, int damageAmount) {
        animal.decreaseHealth(damageAmount);

    }

    private void realizeFutureAction(VirtualFutureAction virtualFutureAction) {
        this.objective = virtualFutureAction.getTargetLocation();
        this.actionToCommit = virtualFutureAction.getActionType();
        this.decideFutureMentalState(this.mentalState, this.actionToCommit);
    }

    private void decideFutureMentalState(MentalStates mentalState, ActionTypes actionToCommit) {
        if ((mentalState == MentalStates.SCARED || mentalState == MentalStates.FLEEING) && actionToCommit == ActionTypes.FLEE_TO_LOCATION){
            this.mentalState = MentalStates.FLEEING;
        }else if((mentalState == MentalStates.SCARED || mentalState == MentalStates.FLEEING) && actionToCommit == ActionTypes.GO_TO_LOCATION){
            this.mentalState = MentalStates.NEUTRAL;
        }else if (mentalState == MentalStates.AGRESSIVE && (actionToCommit == ActionTypes.RUN_AT_ENEMY || actionToCommit == ActionTypes.ATTACK_AT_LOCATION)){
            this.mentalState = MentalStates.AGRESSIVE;
        }else if (mentalState == MentalStates.AGRESSIVE && actionToCommit == ActionTypes.FLEE_TO_LOCATION){
            this.mentalState = MentalStates.FLEEING;
        }else if (mentalState == MentalStates.RETURN_TO_BASE && actionToCommit == ActionTypes.DROP_TO_LOCATION){
            this.mentalState = MentalStates.NEUTRAL;
        }

        if (this.health <= this.MAX_HEALTH/4){
            this.mentalState = MentalStates.WEAK;
        }else if (this.carriedFood > 0){
            this.mentalState = MentalStates.RETURN_TO_BASE;
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

    public SmellSource getSmell() {
        return smell;
    }

    public boolean isToMove() {
        return toMove;
    }

    public Point getOldPosition() {
        return oldPosition;
    }

    public void setToMove(boolean toMove) {
        this.toMove = toMove;
    }

    public boolean isToMove(int time) {
        boolean answer=false;
        if (time!=birthday&&(time-birthday)%activationFrequency==0){
            answer=true;
        }
        return answer;
    }
}