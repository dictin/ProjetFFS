package ModelPkg.PkgEvents;

/**
 * Created by Chloé on 14-03-17.
 */
public abstract class Events {


    private String name;
    private String description; //Description qui va apparaître pour le joueur
    private int probability;

    public Events(String name, String description){
        this.name = name;
        this.description = description;
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
    public void execute(){

    }

}
