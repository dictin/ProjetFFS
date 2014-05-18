package ModelPkg.PkgEvents;

public class LingeringAlmightyBlessing extends LingeringGameEvents {
    /**
     * Constructeur pour l'événement de bénédiction des dieux. Il s'agit d'un bon événement avec une durée.
     */
    public LingeringAlmightyBlessing() {
        super(GameEventType.GOOD, 2);
    }

    @Override
    public void firstTimeActivation() {
    }

    @Override
    public void lingeringActivation() {
        int heal=getGravity()*10;
    }
}
