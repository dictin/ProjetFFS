package ModelPkg.PkgEvents;

/**
 * Created by Xav on 08/05/14.
 */
public class LingeringAlmightyBlessing extends LingeringGameEvents {
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
