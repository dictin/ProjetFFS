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
        //TODO TVA news yay we've been blessed
    }

    @Override
    public void lingeringActivation() {
        int heal=getGravity()*10;
        //TODO heal all friendly units by 'heal' hp
    }
}
