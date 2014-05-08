package ModelPkg.PkgEvents;

/**
 * Created by Xav on 08/05/14.
 */
public class InstantaneousHumanitarianHelp extends InstantaneousGameEvent {

    public InstantaneousHumanitarianHelp() {
        super(GameEventType.GOOD);
    }

    public void firstTimeActivation() {
        //TODO TVA yay africa sent us food
        int foodReceived=getGravity()*200;
        //TODO add food received
    }
}
