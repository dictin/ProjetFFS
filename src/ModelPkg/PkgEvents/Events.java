package ModelPkg.PkgEvents;

/**
 * Created by Chloé on 14-03-17.
 */
public abstract class Events {


    private String name;
    private String description; //Description qui va apparaître pour le joueur
    private int probability;
    private int lastEvent; //Temps auquel le dernier événement s'est terminé
    private int chrono; //Temps depuis le dernier événement
    private int duration;

    public Events(String name, String description, int prob){
        this.name = name;
        this.description = description;
        this.probability = prob;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public int getProbability() {
        return probability;
    }
    public int getChrono() {
        return chrono;
    }
    public int getDuration() {
        return duration;
    }

    public void setChrono(){
       int actualTime =  Time.getTurn();
       int chrono = actualTime-lastEvent;
       this.chrono = chrono;
    }

    public void execute(){

    }

}
