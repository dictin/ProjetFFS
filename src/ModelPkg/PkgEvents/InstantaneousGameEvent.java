package ModelPkg.PkgEvents;

/**
 * Created by Xav on 08/05/14.
 */
public abstract class InstantaneousGameEvent extends GameEvent {
    /**
     * Constructeur pour les événements instantannée
     * @param type type de l'événement (bon, mauvais ou neutre)
     */
    public InstantaneousGameEvent(GameEventType type) {
        super(type, 0);
    }


    public abstract void firstTimeActivation();
}
