package ModelPkg.PkgEvents;

public abstract class GameEvent {

    private GameEventType type;
    private String description; //Description qui va apparaître pour le joueur
    private int gravity=0;
    private int duration=0;

    public static enum GameEventType{
        GOOD,
        BAD,
        NEUTRAL;
    }

    public GameEvent(GameEventType type, int duration){
        this.type=type;
        this.duration=duration;
    }

    public String getDescription() {
        return description;
    }
    public int getGravity() {
        return gravity;
    }
    public void setGravity(int gravity) { this.gravity = gravity; }
    public int getDuration() {
        return duration;
    }
    public void decreaseDuration() {
        this.duration-=1;
    }


    public abstract void firstTimeActivation();
}
