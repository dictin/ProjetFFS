package ObserverPkg;

public interface Observable {
//TODO Does this need a javadoc (see Observer too)
    public void addObserver(Observer observer);
    public void updateObservers();
}
