package ModelPkg;



import ModelPkg.WildObjects.FoodSource;
import ModelPkg.WildObjects.WildObject;
import ObserverPkg.Observable;
import ObserverPkg.Observer;

import java.awt.*;
import java.util.ArrayList;

public class Case implements Observable {

    private boolean passable=true;


    private Point position;
    private Animal occupant=null;
    private WildObject terrain;
    private ArrayList<Smell> smellArrayList = new ArrayList<Smell>();
    private ArrayList<SmellSource> smellSourceArrayList = new ArrayList<SmellSource>();
    private ArrayList<Observer> observers = new ArrayList<Observer>();

    /**
     * Constructeur d'une case
     * @param location position de la case sur le terrain
     * @param occupant animal qui occupe la case
     * @param terrain objet sauvage qui occupe la case
     */
    public Case(Point location, Animal occupant , WildObject terrain){
        this.position = location;
        this.occupant = occupant;
        this.terrain = terrain;

    }

    /**
     * Constructeur utilisé lorsque l'on veut cloner une case
     * @param point position de la case
     * @param passable si la case est traversable ou non
     */
    public Case(Point point, boolean passable){
        this.position= new Point(point.x, point.y);
        this.passable=passable;
    }

    /**
     *
     * @param thing
     * @return
     */
    public boolean caseContains(String thing){
        boolean answer=false;
        try{
        if (thing.equals("fourmilier")&&this.getOccupant() instanceof Fourmillier){
            answer=true;
        }
        else if (thing.equals("predator")&&this.getOccupant() instanceof Critter){
                answer=true;
        }
        else if (thing.equals("item")&&this.getWildObject().getType()==8){
            answer=true;
        }
        else if (thing.equals("food")&&this.getWildObject().getType()==7) {
            answer=true;
        }
    }
        catch(NullPointerException npe){
        }
        return answer;
    }

    /**
     * Méthode qui retourne la position de la case
     * @return la position de la case
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Méthode qui retourne l'animal qui occupe la case
     * @return animal qui occcupe la case
     */
    public Animal getOccupant() {
        return occupant;
    }

    /**
     * Méthode qui retourne l'objet sauvage qui occupe la case
     * @return l'objet sauvage qui occupe la case
     */
    public WildObject getWildObject(){
        return this.terrain;
    }

    /**
     * Méthode qui modifie l'animal qui occupe la case
     * @param occupant
     */
    public void setOccupant(Animal occupant) {
        this.occupant = occupant;
        this.passable=this.occupant!=null?true:false;
        this.updateObservers();
    }

    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void updateObservers() {
        for(int i = 0; i< observers.size(); i++){
            this.observers.get(i).update();
        }
    }

    /**
     * Méthode qui retourne une liste d'odeur ordonnée
     * @return une lsite d'odeur ordonnée
     */
    public ArrayList<Smell> getSortedSmellArrayList() {
        if (smellArrayList.isEmpty()){
            ArrayList<Smell> emptySmellArrayList = new ArrayList<>();
            emptySmellArrayList.add(new Smell(-1,0,-1, SmellType.NOTHING));
            return emptySmellArrayList;
        }
        else {
            ArrayList<Smell> unsortedSmellArrayList = new ArrayList<>();
            ArrayList<Smell> sortedSmellArrayList = new ArrayList<>();

            for (int i=0; i<smellArrayList.size(); i++){
                unsortedSmellArrayList.add(smellArrayList.get(i).clone());
            }

            while(!unsortedSmellArrayList.isEmpty()){
                int strongestSmell = 0;
                int strongestIndex = 0;
                for(int i = 0; i < unsortedSmellArrayList.size(); i++){
                    if (unsortedSmellArrayList.get(i).getIntensity()>= strongestSmell){
                        strongestSmell = unsortedSmellArrayList.get(i).getIntensity();
                        strongestIndex = i;
                    }
                }
                sortedSmellArrayList.add(unsortedSmellArrayList.remove(strongestIndex));
            }


            return sortedSmellArrayList;
        }
    }

    /**
     * Méthode qui efface l'odeur la plus faible entre 2 odeurs de même type
     * @param smell le type d'odeur
     */
    public void eraseInferiorSmellOfSameID(Smell smell) {
        Boolean inferiorSmellNotFound=true;
        for (int i=0; i<smellArrayList.size()&&inferiorSmellNotFound;i++){
            Smell comparedSmell=smellArrayList.get(i);
            if (comparedSmell.getID()==smell.getID()){
                smellArrayList.remove(comparedSmell);
                inferiorSmellNotFound=false;
            }
        }
        if (inferiorSmellNotFound){
        }
    }

    /**
     * Méthode qui retourne une liste d'odeur source ordonnée
     * @return renvoit la liste d'odeurs source
     */
    public ArrayList<SmellSource> getSortedSmellSourceArrayList() {
        ArrayList<SmellSource> unsortedSmellSourceArrayList=new ArrayList<>();
        ArrayList<SmellSource> sortedSmellSourceArrayList = new ArrayList<>();

        for (int i=0; i<smellSourceArrayList.size(); i++){
            unsortedSmellSourceArrayList.add(smellSourceArrayList.get(i).clone());
        }

            while(!unsortedSmellSourceArrayList.isEmpty()){
                int strongestSmellIntensity = 0;
                int strongestIndex = 0;
                boolean strongestFound=false;
                for(int i = 0; i < unsortedSmellSourceArrayList.size()&&!strongestFound; i++){
                    if (unsortedSmellSourceArrayList.get(i).getIntensity()>= strongestSmellIntensity){
                        strongestSmellIntensity = unsortedSmellSourceArrayList.get(i).getIntensity();
                        strongestIndex = i;
                        strongestFound=true;
                    }
                }
                sortedSmellSourceArrayList.add(unsortedSmellSourceArrayList.remove(strongestIndex));
            }
        return sortedSmellSourceArrayList;
    }

    /**
     * Méthode qui diminue l'odeur plus on s'éloigne de la source
     */
    public void fadeSourceSmells() {
        for(int i=0; i<smellSourceArrayList.size();i++){
            SmellSource smellSource=smellSourceArrayList.get(i);
            smellSource.fade();
            if (smellSource.getIntensity()<=0){
                smellSourceArrayList.remove(i);
                i--;
            }
        }
    }

    /**
     * Méthode qui supprime la source d'une odeur
     * @param smellSource la source d'odeur que l'on veut supprimer
     */
    public void removeSmellSource(SmellSource smellSource) {
        if (!smellSourceArrayList.isEmpty()){
        boolean matchNotFound=true;
        for (int i=0; i<smellSourceArrayList.size()&&matchNotFound; i++){
            if (smellSourceArrayList.get(i).getID()==smellSource.getID()){
                matchNotFound=false;
                smellSourceArrayList.remove(i);
            }
        }
        }
    }

    /**
     * Méthode qui ajoute une odeur à la liste d'odeur ordonné
     * @param smell l'odeur à rajouter
     */
    public void addToSortedSmellArrayList(Smell smell) {
        this.smellArrayList.add(smell);
    }

    /**
     * Méthode qui ajoute une source d'odeur à la liste de source d'odeur ordonné
     * @param smellSource la source d'odeur que l'on veut ajouté à la liste
     */
    public void addToSortedSmellSourceArrayList(SmellSource smellSource) {
        this.smellSourceArrayList.add(smellSource);
    }

    /**
     *Méthode qui vide la liste d'odeur
     */
    public void clearSortedSmellArrayList() {
        smellArrayList=new ArrayList<>();
    }

    /**
     * Méthode qui modifie l'objet sauvage de la case
     * @param wildObject nouvel objet sauvage de la case
     */
    public void setWildObject(WildObject wildObject) {
        this.terrain = wildObject;
        this.updateObservers();
    }
//Delete
    /**
     *
     * @param sortedArrayList
     */
    public void setSortedArrayList(ArrayList<Smell> sortedArrayList) {
        this.smellArrayList = sortedArrayList;
    }

    /**
     * Méthode qui diminue la quantité de nourriture dans une source de nourriture
     */
    public void decreaseFoodQuantity() {
        FoodSource foodSource = ((FoodSource)getWildObject());
        foodSource.decreaseFoodQuantity();
    }

    /**
     * Méthode qui vérifie si la source de nourriture est vide
     * @return si la source est vide ou non
     */
    public boolean emptyFoodSource(){
        FoodSource foodSource = ((FoodSource)getWildObject());
        boolean noMoreFood;
        noMoreFood = foodSource.isEmpty() ? true : false;
        return noMoreFood;
    }

    /**
     * Méthode qui modifie si la cas est traversable ou non
     * @param passable si la case est traversable ou non
     */
    public void setPassable(boolean passable){
        this.passable=passable;
    }

    /**
     * Méthode qui retourne si la case est traversable ou non
     * @return si la case est traversable ou non
     */
    public boolean getPassable() {
        return passable;
    }

    /**
     * Méthode qui vérifie si la case a un objet sauvage et qui modifie si la case est traversable en conséquence
     * @return si la case est traversable ou non
     */
    public boolean updateAndGetPassable(){
        if (this.getWildObject().getType()!=WildObject.EMPTY_ID||this.getOccupant()!=null){
            setPassable(false);
        }
        else{
            setPassable(true);
        }
        return this.passable;
    }

    /**
     * Méthode qui modifie affecte 0 à une source d'odeur dans la liste
     * @param index position de la source d'odeur dans la liste  à modifier
     */
    public void set0ToSmellSourceIntensityIndex(int index) {
        getSortedSmellSourceArrayList();
        this.smellSourceArrayList.get(index).setIntensity(0);
    }

    /**
     * Méthode qui modifie affecte 0 à une odeur dans la liste
     * @param index position de l'odeur dans la liste à modifier
     */
    public void set0ToSmellIntensityIndex(int index) {
        getSortedSmellArrayList();
        this.smellArrayList.get(index).setIntensity(0);
    }

    /**
     * Méthode qui modifie l'objet sauvage sur la case
     * @param terrain nouveau objet sauvage sur la case
     */
    public void setTerrain(WildObject terrain) {
        this.terrain = terrain;
        this.updateObservers();
        }

    /**
     *Fonction qui clone une case
     * @return une nouvelle case
     */
    public Case semiClone() {
        Case clone=new Case(getPosition(), getOccupant(), getWildObject());
        return clone;
    }
}