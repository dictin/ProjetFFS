package ModelPkg;

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


    /**
     * Constructeur de smell
     * @param animalID identification de l'animal
     * @param intensity intensity de la senteur
     * @param team équipe
     * @param type type d'odeur
     */
    public Smell(long animalID, int intensity, int team, SmellType type){
        this.iD =animalID;
        this.intensity=intensity;
        this.team=team;
        this.type=type;
        this.team=team;

    }

    /**
     * Méthode qui diminue l'intensité de l'odeur
     * @param percentageMultiplier le pourcentage de dimunition de l'odeur
     */
    public void dissipateIntensity(int percentageMultiplier){
        this.intensity=intensity*percentageMultiplier/100;
    }

    /**
     * Méthode qui retourne l'intensité de l'odeur
     * @return  l'intensité de l'odeur
     */
    public int getIntensity(){
        return this.intensity;
    }

    /**
     * Méthode qui retourne l'identification du fourmilier
     * @return l'identification du fourmilier
     */
    public long getID() {
        return iD;
    }

    /**
     * Méthode qui retourne l'équipe
     * @return l'équipe
     */
    public int getTeam() {return team;}

    /**
     * Méthode qui retourne le type d'odeur
     * @return le type d'odeur
     */
    public SmellType getType() {
        return type;
    }


    /**
     *@see java.lang.Cloneable
     * @return
     */
    @Override
    public Smell clone() {
            Smell clonedSmell=new Smell(getID(), getIntensity(), getTeam(), getType());
        return clonedSmell;
    }
}
