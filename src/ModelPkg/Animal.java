package ModelPkg;

import ControllerPkg.MasterController;
import ModelPkg.WildObjects.*;

import java.awt.*;
import java.util.Random;

public abstract class Animal {


    /**
     * Valeur représentant le fait que l'animal cherche l'odeur la plus intense
     */
    public static final String LEAST_INTENSE ="lesser";
    /**
     * Valeur représentant le fait que l'animal cherche l'odeur la moins intense
     */
    public static final String MOST_INTENSE ="greater";
    /**
     * Instance de MasterController
     */
    private MasterController masterController;
    /**
     * Vie maximale de l'animal
     */
    private int maxHealth = 100;
    /**
     * Identifian unique de l'animal
     */
    private long animalID;
    /**
     * Fréquence à laquelle l'animal est permis d'agir
     */
    private int activationFrequency;
    /**
     * Temps de création
     */
    private int birthday;
    /**
     * Position actuelle
     */
    private Point position;
    /**
     * Position lors du dernier tour
     */
    private Point oldPosition = null;
    /**
     * Case occupé
     */
    private Case occupiedCase;
    /**
     * Statistiques moyennes de tous les animaux
     */
    private int[] meanStats;
    /**
     * Statistiques uniques à cette instance d'animal
     */
    private int[] mainStats;
    /**
     * Nom de l'animal
     */
    private String name;
    /**
     * Génération de l'animal
     * Ex: George le IIe du nom
     */
    private int nameGen;
    /**
     * Vie actuelle de l'animal
     */
    private int health;
    /**
     * Moral de l'animal
     * Non implémenté
     */
    private int moral;
    /**
     * Odeur que propage l'animal
     */
    private SmellSource smell;
    /**
     * Nom de l'espèce de l'animal
     */
    private String species;
    /**
     * Nourriture transportée par l'animal
     * Structure dynamique de cette variable non implémentée
     */
    private int foodCarried = 0;


    /**
     * Vitesse de l'animal
     */
    private int speed;
    /**
     * Endurance de l'animal
     */
    private int endurance;

    /**
     * Puissance d'ataque de l'animal
     */
    private int attack;
    /**
     * Défence de l'animal
     */
    private int defence;

    /**
     * Sensibilité aux odeurs
     */
    private int smellSensitivity;
    /**
     * Puisance minimal en-dessous de laquelle l'animal ne sent rien
     */
    private int smellThreshold;
    /**
     * Statistique représentant la puissance de l'odeur émise par l'animal
     */
    private int smellStrengthStat;
    /**
     * Valeur modifiée représentant l'intensité "réelle" de l'odeur émise
     */
    private int smellIntensity;

    /**
     * Quantité d'odeur que peut tenir l'animal
     */
    private int grabQuantity;
    /**
     * Quantité de nourriture présentement tenue par l'animal
     */
    private int carriedFood = 0;

    /**
     * Équipe dans laquelle se trouve l'animal
     */
    private int team; // -1: player, 1: enemy 1, 2: enemy 2


    /**
     * Sprite de l'animal
     */
    private Image sprite;

    /**
     * Action que doit commetre l'animal
     */
    private ActionTypes actionToCommit = null;

    /**
     * L'animal doit-il bouger
     */
    private boolean toMove = false;

    /**
     * Constructeur d'Animal. Animal contient toutes les information sur un fourmilier. Il assigne un nom au fourmilier
     * et lui donne des données de base
     * @param team équipe du fourmilier
     * @param meanStats tableau avec les statistiques de basde du fourmilier
     * @param species nom de l'espèce du fourmilier
     * @param startingPosition position de départ du fourmilier
     * @param animalID identification du fourmilier
     * @param smellType type de senteur du fourmilier
     * @param masterController contrôleur principal
     */
    public Animal(int team, int[] meanStats, String species, Point startingPosition, long animalID, SmellType smellType,final MasterController masterController){
        this.masterController = masterController;
        this.birthday= MasterController.getTime();
        Random random = new Random();
        int noName = random.nextInt(20);
        this.name = Name.getName(noName);
        this.nameGen = Name.getGen(noName);
        this.animalID = animalID;

        this.name +=this.nameGen;


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
        this.grabQuantity=25;
        this.moral = 25;
        this.smellIntensity=this.getSmellStrengthStat()*8;
        this.smellThreshold=100/this.getSmellSensitivity();



        this.smell = new SmellSource(animalID, this.smellIntensity, this.team, smellType);

        

        sprite=Toolkit.getDefaultToolkit().getImage("IMG/"+species+".png");
    }

    /**
     * Méthode pour déterminer aléatoirement les statistiques du fourmilier
     * @return un tableau avec les statistiques de base du fourmilier
     */
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

    /**
     * Méthode pour modifier la position du fourmilier
     * @param position nouvelle position
     */
    public void setPosition(Point position) {
        this.position = position;
    }

    /**
     * Méthode pour modifier la vie du fourmilier
     * @param health nouvelle vie
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     *
     * @param speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     *
     */
    public  void smellSurroundings(){

    }

    /**
     *
     * @param location
     */
    public void grab(Point location){

    }

    /**
     *
     * @param location
     */
    public void drop(Point location){

    }

    /**
     * Méthode pour faire diminué la vie du fourmilier et si sa vie tombe en bas de zéro, supprime le fourmilier
     * @param amount quantité de vie à enlever
     */
    public void decreaseHealth(int amount){
        this.health-= amount;
        if(this.isDead()){
            MasterController.disposeAnimal(this);
            masterController.victims();

        }
    }

    /**
     * Méthode pour avoir la position du fourmilier
     * @return la position du fourmilier
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Méthode pour avoir le nom du fourmilier
     * @return le nom du fourmilier
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public int getNameGen() {
        return nameGen;
    }

    //Number-Crunch Statistics------------------------------------------------------------------------------------------

    /**
     *
     * @return
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * Méthode qui retourne la vie du fourmilier
     * @return la vie du fourmilier
     */
    public int getHealth() {
        return health;
    }

    /**
     *
     * @return
     */
    public int getSpeed() {
        return speed;
    }

    /**
     *
     * @return
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Méthode qui retourne la sensibilité de l'odorat du fourmilier
     * @return la sensibilité de l'odorat du fourmilier
     */
    public int getSmellSensitivity() {
        return smellSensitivity;
    }

    /**
     *
     * @return
     */
    public int getSmellThreshold() {return smellThreshold;}

    /**
     * Méthode pour avoir la force de l'odorat du fourmilier
     * @return la force de l'odorat du fourmilier
     */
    public int getSmellStrengthStat() {
        return smellStrengthStat;
    }

    /**
     *
     * @return
     */
    public int getDefence() {
        return defence;
    }

    /**
     *
     * @return
     */
    public int getEndurance() {
        return endurance;
    }

    /**
     *
     * @return
     */
    public int getGrabQuantity() {
        return grabQuantity;
    }

    /**
     *
     * @return
     */
    public int getActivationFrequency() {
        return activationFrequency;
    }


    //Adjusted Crunch---------------------------------------------------------------------------------------------------

    /**
     *Méthode qui retourne la vie maximale du jourmilier avec les modification des objets
     * @return la vie maximale
     */
    public  int getAdjustedMaxHealth(){
        return this.maxHealth + this.masterController.getPlayerDataController().getStatMod(PlayerData.HP_STATID);
    }

    /**
     * Méthode qui retourne la vitesse du fourmilier avec les modifications des objets
     * @return
     */
    public int getAdjustedSpeed(){
        return this.speed + this.masterController.getPlayerDataController().getStatMod(PlayerData.SPD_STATID);
    }

    /**
     *
     * @return
     */
    public int getAdjustedAttack(){
        return this.attack + this.masterController.getPlayerDataController().getStatMod(PlayerData.ATK_STATID);
    }

    /**
     *
     * @return
     */
    public int getAdjustedSmellSensitivity(){
        return this.smellSensitivity + this.masterController.getPlayerDataController().getStatMod(PlayerData.SMS_STATID);
    }

    /**
     *Retourne smellthreshold en tennant compte des modificateurs relatifs à l'utilisation d'objets
     * @return
     */
    public int getAdjustedSmellThreshold(){
        return this.smellThreshold + (100/this.getAdjustedSmellSensitivity());
    }

    /**
     *
     * @return
     */
    public int getAdjustedSmellStrength(){
        return this.smellStrengthStat + this.masterController.getPlayerDataController().getStatMod(PlayerData.SMT_STATID);
    }

    /**
     *
     * @return
     */
    public int getAdjustedDefence(){
        return this.defence + this.masterController.getPlayerDataController().getStatMod(PlayerData.DEF_STATID);
    }

    /**
     * Méthode qui retourne l'endurance du fourmilier avec les modifications des objets
     * @return la nouvelle endurance du fourmilier
     */
    public int getAdjustedEndurance(){
        return this.endurance + this.masterController.getPlayerDataController().getStatMod(PlayerData.END_STATID);
    }

    /**
     * Méthode qui retourne la nouvelle quantité de nourriture que peuvent ramasser le fourmilier avec les modifications
     * des objets
     * @return la quantité de nourriture que peut ramasser le fourmilier
     */
    public int getAdjustedGBTQ(){
        return this.grabQuantity + this.masterController.getPlayerDataController().getStatMod(PlayerData.GBTQ_STATID);
    }

    /**
     * Retourne activationFrequency en prenant compte des modificateurs liés à l'utilisation d'ojets.
     * @return
     */
    public int getAdjustedActivationFrequency() {
        return 480/this.getAdjustedSpeed();
    }

    //End of Number-Crunch Statistics-----------------------------------------------------------------------------------

    /**
     * Méthode qui retourne l'image du fourmilier
     * @return l'image du fourmilier
     */
    public Image getSprite() {
        return sprite;
    }

    /**
     * Méthode qui détermine  quel type d'action le fourmilier va faire
     * @return le type d'action du fourmilier
     */
    public ActionTypes getActionToCommit() {
        return actionToCommit;
    }
    //Delete
/**
 *
 */
    public void eatFood(){
        if (MapData.getCase(position).getWildObject().getType()==7){
            //eatFoodInCase(MapData.getCase(position));
        }
        if (health!=100){
            //eatCarriedFood();
        }
    }

    /**
     *
     */
    public void activateAsFoe(){

    }

    /**
     *  Méthode qui détermine le bon mouvement à faire en fonction du type de mouvement que le fourmilier veut faire
     * et des odeurs autour de lui
     * @param time le temps du jeu
     */
    public void activate(int time){
        VirtualFutureAction virtualFutureAction=null;
        if (this.health <= this.maxHealth/4){
            if (this.carriedFood > 0){
                this.restore();
                virtualFutureAction = Behavior.skipTurn();

            }else {
                if (Behavior.isCloseTo(WildObject.FOOD_ID, this.position)){
                    virtualFutureAction = Behavior.eatAdjacentFood(this.position);
                }else if (Behavior.doesItSmell(this.filterSmells(), SmellType.FOOD)){
                    virtualFutureAction = Behavior.scanForWildObject(this.position, this.filterSmells(), SmellType.FOOD, MOST_INTENSE);
                }
            }

        }
        //Cherche à rentrer.
        else if(this.carriedFood>0){
            if (Behavior.isCloseTo(WildObject.HIVE_ID, this.position)){
                virtualFutureAction = Behavior.dropToHive(this.position);
            }
            else if(Behavior.doesItSmell(this.filterSmells(), SmellType.HIVE)){
                virtualFutureAction = Behavior.scanForWildObject(this.position, this.filterSmells(), SmellType.HIVE, MOST_INTENSE);
            }
        }
        else {
            if (Behavior.isCloseTo(WildObject.FOOD_ID, this.position)){
                virtualFutureAction = Behavior.pickUpFood(this.position);
            }
            else if (Behavior.doesItSmell(this.filterSmells(), SmellType.FOOD)){
                virtualFutureAction=Behavior.scanForWildObject(this.position, this.filterSmells(), SmellType.FOOD, MOST_INTENSE);
            }
        }


        if (virtualFutureAction==null){
            virtualFutureAction=new VirtualFutureAction(Behavior.drunk(this.position), ActionTypes.GO_TO_LOCATION);
        }
        accomplishMission(virtualFutureAction);
        decreaseHealth(((25 - this.getAdjustedEndurance()) / 2));
    }

    /**
     * Méthode qui permet au fourmilier de se faire sa mission(la nouvelle action à faire)
     * @param mission mouvement et type de mouvement du fourmilier
     */
    public void accomplishMission(VirtualFutureAction mission){
        Point target=new Point(mission.getTargetLocation().x+this.position.x, mission.getTargetLocation().y+this.position.y);
        Case targetCase=MapData.getCase(target);
        switch(mission.getActionType()){
            case DROP_TO_HIVE:
                masterController.getPlayerDataController().addFood(carriedFood);
                this.carriedFood=0;
                break;
            case EAT_FROM_LOCATION:
                if (targetCase.getWildObject() instanceof FoodSource){
                    while (targetCase.getWildObject().getType()!=WildObject.EMPTY_ID&&this.getHealth()<100&&((FoodSource) targetCase.getWildObject()).getFoodQuantity()>0){
                        targetCase.decreaseFoodQuantity();
                        if(targetCase.emptyFoodSource()){
                            masterController.disposeWildObject(target);
                        }
                        if (this.health>=(this.getAdjustedMaxHealth()-10)){
                            this.setHealth(100);
                        }
                        else{
                            this.setHealth(this.getHealth()+10);
                        }
                    }
                }
                break;
            case GO_TO_LOCATION:
                if (mission.getTargetLocation().x==mission.getTargetLocation().y&&mission.getTargetLocation().y==0){
                }
                //if (mission.getTargetLocation().){
                //}
                this.setToMove(true);
                this.setPosition(target);
                break;
            case PICKUP_FROM_LOCATION:
                if (targetCase.getWildObject() instanceof FoodSource){
                    boolean foodSourceDepleted=false;
                    while(carriedFood<this.getAdjustedGBTQ()&&!foodSourceDepleted){
                        targetCase.decreaseFoodQuantity();
                        if(targetCase.emptyFoodSource()){
                            foodSourceDepleted=true;
                            masterController.disposeWildObject(target);

                        }
                        this.carriedFood++;
                    }
                }
                break;
        }

    }

    /**
     * Méthode qui permet au fourmilier de manger de la nourriture pour augmenter sa vie
     */
    private void restore(){
        while (this.carriedFood > 0 && this.health < this.getAdjustedMaxHealth()){
                carriedFood--;
                if(this.health < 90){
                    this.health+=10;
                }else{
                    this.health = this.getAdjustedMaxHealth();
                }
        }

}


    /**
     *
     * @param animal
     * @param damageAmount
     */
    private void attackOpponent(Animal animal, int damageAmount) {
        animal.decreaseHealth(damageAmount);
    }


    /**
     * Méthode qui détermine si le fourmilier est mort ou non
     * @return si le fourmilier est mort
     */
    public boolean isDead(){
        if (health<=0){
            
            return true;
        }
        else {
            return false;
        }
    }

    /**
     *
     * @return
     */
    public Case getOccupiedCase() {
        return occupiedCase;
    }

    /**
     * Méthode qui permet d'avoir l'odeur du fourmilier
     * @return l'odeur du fourmilier
     */
    public SmellSource getSmell() {
        return smell;
    }

    /**
     *
     * @return
     */
    public boolean isToMove() {
        return toMove;
    }

    /**
     *
     * @return
     */
    public Point getOldPosition() {
        return oldPosition;
    }

    /**
     * Méthode qui modifie si le fourmilier doit bougé ou non
     * @param toMove si le fourmilier doit bougé ou non
     */
    public void setToMove(boolean toMove) {
        this.toMove = toMove;
    }

    /**
     * Méthode qui retourne si le fourmilier doit bougé ou non
     * @param time le temps du jeu
     * @return si le fourmilier doit bougé ou non
     */
    public boolean isToMove(int time) {
        boolean answer=false;
        if (time!=birthday&&(time-birthday)%this.getAdjustedActivationFrequency()==0){
            answer=true;
        }
        return answer;
    }

    /**
     * Méthode qui filtre les odeurs en ordre croissant
     * @return un tableau 2D de cases avec des odeurs ordonnées
     */
    private Case[][] filterSmells(){
        Case[][] unfilteredSubsection = MapData.getSubsection2(this.position);
        Case[][] filteredSubsection = new Case[unfilteredSubsection.length][unfilteredSubsection[0].length];
        for (int i = 0; i < unfilteredSubsection.length; i++) {
            for (int j = 0; j < unfilteredSubsection[i].length; j++) {
                filteredSubsection[i][j]=new Case(unfilteredSubsection[i][j].getPosition(), unfilteredSubsection[i][j].updateAndGetPassable());
                for (int k = 0; k < unfilteredSubsection[i][j].getSortedSmellArrayList().size(); k++){
                    if (unfilteredSubsection[i][j].getSortedSmellArrayList().get(k).getIntensity() >= this.getAdjustedSmellThreshold()){
                        filteredSubsection[i][j].addToSortedSmellArrayList(unfilteredSubsection[i][j].getSortedSmellArrayList().get(k));
                    }
                }
            }
        }
        return filteredSubsection;
    }

    /**
     *
     * @return
     */
    public int getTeam() {
        return team;
    }
//Delete
    /**
     *
     * @return
     */
    public int[] getMeanStats() {
        return meanStats;
    }
    //Delete
    /**
     *
     * @return
     */
    public String getSpecies() {
        return species;
    }

    /**
     * Méthode qui retourne l'indentification du fourmilier
     * @return l'identification du fourmilier
     */
    public long getID() {
        return animalID;
    }

    /**
     * Méthode qui renvoit le contrôleur principal
     * @return le contrôleur principal
     */
    public MasterController getMasterController() {
        return masterController;
    }
}