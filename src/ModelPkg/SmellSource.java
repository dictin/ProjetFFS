package ModelPkg;

/**
 * Created by Xav on 27/04/14.
 */
public class SmellSource extends Smell{

    public SmellSource(long animalID, int intensity, int team, SmellType type) {
        super(animalID, intensity, team, type);
    }

    public void fade() {
        super.setIntensity(super.getIntensity()-10);

       // System.out.println("fade");
       // System.out.println(super.getIntensity());
    }

    public enum SmellType2{
            food, animal, foe, item;
        }
}

