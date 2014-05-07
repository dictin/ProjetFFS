package ModelPkg;

/**
 * Created by Xav on 27/04/14.
 */
public class Smell implements Cloneable {
    private long iD;
    private int intensity;
    public static final int FOOD_ODOR = 1;
    public static final int ENEMY_ODOR = 2;
    public static final int ALLY_ODOR = 3;
    public static final int HIVE_ODOR = 4;


    private int team;
    private SmellType type;

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    //TODO remplacer le String ID par un int
    public Smell(long animalID, int intensity, int team, SmellType type){
        this.iD =animalID;
        this.intensity=intensity;
        this.team=team;
        this.type=type;
        this.team=team;
        this.type=type;
    }

    public void dissipateIntensity(int percentageMultiplier){
        this.intensity=intensity*percentageMultiplier/100;
    }

    public int getIntensity(){
        return this.intensity;
    }

    public long getID() {
        return iD;
    }

    public SmellType getType() {
        return type;
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
