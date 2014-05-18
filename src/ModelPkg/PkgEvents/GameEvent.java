package ModelPkg.PkgEvents;

public abstract class GameEvent {

    private GameEventType type;
    private String description; //Description qui va apparaître pour le joueur
    private int gravity=0;
    private int duration=0;

    /**
     * GameEventType est un enum avec les différents type d'événement possible
     */
    public static enum GameEventType{
        GOOD,
        BAD,
        NEUTRAL;
    }

    /**
     * Constructeur de GameEvent
     * @param type la sorte d'événement
     * @param duration la durée de l'événement
     */
    public GameEvent(GameEventType type, int duration){
        this.type=type;
        this.duration=duration;
    }

    /**
     * Méthode qui retourne le nom de l'événement
     * @return le nom de l'événement
     */
    public String getDescription() {
        return description;
    }

    /**
     * Méthode qui retourne la gravité de l'événement
     * @return la gravité de l'événement
     */
    public int getGravity() {
        return gravity;
    }

    /**
     * Méthode qui modifie la gravité de l'événement
     * @param gravity nouvelle gravité de l'événement
     */
    public void setGravity(int gravity) { this.gravity = gravity; }

    /**
     * Méthode qui retourne la durée de l'événement
     * @return la durée de l'événement
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Méthode qui diminue la durée de l'événement
     */
    public void decreaseDuration() {
        this.duration-=1;
    }


    public abstract void firstTimeActivation();
}
