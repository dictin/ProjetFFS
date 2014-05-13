package ModelPkg.PkgEvents;

/**
 * Created by Xav on 13/05/14.
 */
public class InstantaneousCombustion extends InstantaneousGameEvent{
    public InstantaneousCombustion() {
        super(GameEventType.BAD);
    }

    @Override
    public void firstTimeActivation() {
        int victimNumber=getGravity()*2;

        //TODO kill <victimNumber> friendly unit
        //TODO tva news
    }
}
