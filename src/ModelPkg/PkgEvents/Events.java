package ModelPkg.PkgEvents;

import ModelPkg.Time;

public abstract class Events {


    private String name;
    private String description; //Description qui va apparaître pour le joueur
    private int gravity;
    private int lastEvent; //Temps auquel le dernier événement s'est terminé
    private int duration;

    public Events(String name, String description, int prob){
        this.name = name;
        this.description = description;
        this.gravity = prob;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public int getGravity() {
        return gravity;
    }
    public int getDuration() {
        return duration;
    }


    public void execute(){

    }

}
