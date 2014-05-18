package ModelPkg;

/**
 * Created by Xav on 27/04/14.
 */
public class SmellSource extends Smell implements Cloneable{

    /**
     * Constructeur de smellSource
     * @param animalID identification de l'animal
     * @param intensity intensité de l'odeur
     * @param team équipe
     * @param type type d'odeur
     */
    public SmellSource(long animalID, int intensity, int team, SmellType type) {
        super(animalID, intensity, team, type);
    }

    /**
     * Méthode qui diminue l'intensité de l'odeur
     */
    public void fade() {
        setIntensity(getIntensity() - 10);
    }
//Delete?
    /**
     *
     */
    public enum SmellType2{
            food, animal, foe, item;
    }
    /**
     *
     * @return
     */
    @Override
    public SmellSource clone(){
        SmellSource clonedSmell=new SmellSource(this.getID(), this.getIntensity(), this.getTeam(), this.getType());
        return clonedSmell;
    }
}

