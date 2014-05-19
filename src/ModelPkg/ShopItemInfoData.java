package ModelPkg;

import ObserverPkg.Observable;
import ObserverPkg.Observer;

import java.util.ArrayList;

public class ShopItemInfoData implements Observable {

    private String name = "";
    private int cost = 0;
    private int modifiedStatID = 0;
    private int modifiedValue = 0;
    private boolean isPermanent = false;

    private ArrayList<Observer> observers = new ArrayList<Observer>();

    /**
     * Constructeur des infromation sur le magasin
     * @param name le nom de l'objet
     * @param cost le coût de l'objet
     * @param modifiedStatID la statistique que modifie cet objet
     * @param modifiedValue la quantité que cet objet modifie dans cette statistique
     * @param isPermanent si l'objet est permanent ou non
     */
    public void changeValues(String name, int cost, int modifiedStatID, int modifiedValue, boolean isPermanent){
        this.name = name;
        this.cost = cost;
        this.modifiedStatID = modifiedStatID;
        this.modifiedValue = modifiedValue;
        this.isPermanent = isPermanent;
        this.updateObservers();

    }

    /**
     * Méthode qui retourne le nom de l'objet
     * @return le nom de L'objet
     */
    public String getName() {
        return name;
    }

    /**
     * Méthode qui retourne le coût de l'objet
     * @return le coût de l'objet
     */
    public int getCost() {
        return cost;
    }

    /**
     * Méthode qui retourne la statistique que l'objet modifie
     * @return la statistique que l'objet modifie
     */
    public int getModifiedStatID() {
        return modifiedStatID;
    }

    /**
     * Méthode qui retourne la quantité que l'objet modifie dans la statistique
     * @return la quantité que l'objet modifie dans la statistique
     */
    public int getModifiedValue() {
        return modifiedValue;
    }

    /**
     * Méthode qui retourne si l'objet est permanent ou non
     * @return si l'objet est permanent ou non
     */
    public boolean isPermanent() {
        return isPermanent;
    }

    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);

    }

    @Override
    public void updateObservers() {
        for (int i =0; i< this.observers.size(); i++){
            this.observers.get(i).update();
        }

    }
}
