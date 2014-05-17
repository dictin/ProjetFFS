package ModelPkg;



import ModelPkg.WildObjects.WildObject;
import ObserverPkg.Observable;
import ObserverPkg.Observer;

import java.awt.*;
import java.util.ArrayList;

public class Case implements Observable {

    public Point getPosition() {
        return position;
    }

    private Point position;
    private Animal occupant=null;
    private WildObject terrain;
    private ArrayList<Smell> smellArrayList = new ArrayList<Smell>();
    private ArrayList<SmellSource> smellSourceArrayList = new ArrayList<SmellSource>();
    private ArrayList<Observer> observers = new ArrayList<Observer>();


    public Case(Point location, Animal occupant , WildObject terrain){
        this.position = location;
        this.occupant = occupant;
        this.terrain = terrain;

    }

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

    public Animal getOccupant() {
        return occupant;
    }

    public WildObject getWildObject(){
        return this.terrain;
    }

    public void setOccupant(Animal occupant) {
        if (occupant==null){
            System.out.println("Set to null");
        }
        this.occupant = occupant;
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
            System.out.println("ERROR");
        }
    }

    public ArrayList<SmellSource> getSortedSmellSourceArrayList() {
        ArrayList<SmellSource> unsortedSmellSourceArrayList=new ArrayList<>();
        ArrayList<SmellSource> sortedSmellSourceArrayList = new ArrayList<>();

        for (int i=0; i<smellSourceArrayList.size(); i++){
            unsortedSmellSourceArrayList.add(smellSourceArrayList.get(i).clone());
        }

            while(!unsortedSmellSourceArrayList.isEmpty()){
                int strongestSmellIntensity = 0;
                int strongestIndex = 0;
                for(int i = 0; i < unsortedSmellSourceArrayList.size(); i++){
                    if (unsortedSmellSourceArrayList.get(i).getIntensity()>= strongestSmellIntensity){
                        strongestSmellIntensity = unsortedSmellSourceArrayList.get(i).getIntensity();
                        strongestIndex = i;
                    }
                }
                sortedSmellSourceArrayList.add(unsortedSmellSourceArrayList.remove(strongestIndex));
            }
        return sortedSmellSourceArrayList;
    }

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

    public void addToSortedSmellArrayList(Smell smell) {
        this.smellArrayList.add(smell);
    }

    public void addToSortedSmellSourceArrayList(SmellSource smellSource) {
        this.smellSourceArrayList.add(smellSource);
    }

    public void clearSortedSmellArrayList() {
        smellArrayList=new ArrayList<>();
    }

    public Case semiClone(){
        Case clone=new Case(getPosition(), getOccupant(), getWildObject());
        return clone;
    }

    public void setTerrain(WildObject terrain) {
        this.terrain = terrain;
    }
}