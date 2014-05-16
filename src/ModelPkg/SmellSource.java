package ModelPkg;

/**
 * Created by Xav on 27/04/14.
 */
public class SmellSource extends Smell implements Cloneable{

    public SmellSource(long animalID, int intensity, int team, SmellType type) {
        super(animalID, intensity, team, type);
    }

    public void fade() {
        setIntensity(getIntensity() - 10);
    }



    public enum SmellType2{
            food, animal, foe, item;
    }

    @Override
    public SmellSource clone(){
        SmellSource clonedSmell=new SmellSource(getID(), getIntensity(), getTeam(), getType());
        System.out.println("Clone intensity not 0:"+(clonedSmell.getIntensity()!=0));
        return clonedSmell;
    }
}

