package ModelPkg.PkgEvents;

/**
 * Created by Xav on 08/05/14.
 */
public abstract class LingeringGameEvents extends GameEvent {
    public LingeringGameEvents(GameEventType type, int duration) {
        super(type, duration);
    }

    public abstract void firstTimeActivation();

    public abstract void lingeringActivation();
}
