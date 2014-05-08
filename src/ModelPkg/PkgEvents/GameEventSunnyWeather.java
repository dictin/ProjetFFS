package ModelPkg.PkgEvents;

/**
 * Created by Xav on 08/05/14.
 */
public class GameEventSunnyWeather extends InstantaneousGameEvent {

    public GameEventSunnyWeather() {
        super(GameEventType.NEUTRAL);
    }

    @Override
    public void firstTimeActivation() {
        if (getGravity()==1){
            //TODO add appropriate tvanews
        }
        else if (getGravity()==2){
            //TODO warn about UV rays
        }
        else{
            //TODO Highly recommand sunblock
        }
    }
}
