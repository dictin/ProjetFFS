package ModelPkg.PkgEvents;

/**
 * Created by Xav on 13/05/14.
 */
public class MassInstantaneousCombustion extends InstantaneousGameEvent{
    public MassInstantaneousCombustion() {
        super(GameEventType.BAD);
    }

    @Override
    public void firstTimeActivation() {
        int victimNumber=getGravity()*2;

        //TODO kill <victimNumber> friendly unit
        //TODO tva news
    }
}
