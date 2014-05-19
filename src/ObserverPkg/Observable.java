package ObserverPkg;

public interface Observable {


    /**
     * Cette méthod ajoute un nouvel observateur à la classe observable
     * @param observer observateur à ajouter
     */
    public void addObserver(Observer observer);

    /**
     * Cette méthode fait parcourir tous les observateur d'une classe et les met à jour
     */
    public void updateObservers();
}
