package ModelPkg.PkgEvents;

/**
 * Created by Xav on 08/05/14.
 */
public abstract class InstantaneousGameEvent extends GameEvent {
    public InstantaneousGameEvent(GameEventType type) {
        super(type, 0);
    }


    public abstract void firstTimeActivation();
}
