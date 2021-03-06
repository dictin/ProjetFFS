package ModelPkg.PkgEvents;

/**
 * Created by Xav on 08/05/14.
 */
public abstract class LingeringGameEvents extends GameEvent {
    /**
     * Constructeur pour les événements avec une durée
     * @param type type de l'événement (bon, mauvais ou neutre)
     * @param duration durée de l'événement
     */
    public LingeringGameEvents(GameEventType type, int duration) {
        super(type, duration);
    }

    public abstract void firstTimeActivation();

    public abstract void lingeringActivation();
}
