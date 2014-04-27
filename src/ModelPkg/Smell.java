package ModelPkg;

import java.awt.*;

/**
 * Created by Xav on 27/04/14.
 */
public class Smell implements Cloneable {
    private int iD;
    private Point coords;
    private int intensity;
    private int team;
    private SmellType2 type;

    public int getID() {
        return iD;
    }

    public enum SmellType2{
        food, animal, foe, item;
    }
    //TODO remplacer le String ID par un int
    public Smell(int animalID, Point coords, int intensity, int team, SmellType2 type){
        this.iD =animalID;
        this.coords=coords;
        this.intensity=intensity;
        this.team=team;
        this.type=type;
    }

    public void dissipateIntensity(int percentageMultiplier){
        this.intensity=intensity*percentageMultiplier/100;
    }

    public int getIntensity(){
        return this.intensity;
    }

    @Override
    public Smell clone() {
        try {
            return (Smell)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            System.out.println("Clone not supported. Error.");
            return null;
        }
    }
}
