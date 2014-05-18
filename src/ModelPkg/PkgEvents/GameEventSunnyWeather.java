package ModelPkg.PkgEvents;

import ModelPkg.MapData;

/**
 * Created by Xav on 08/05/14.
 */
public class GameEventSunnyWeather extends InstantaneousGameEvent {
    /**
     * Constructeur de SunnyWeather. Il s'agit des événements neutres
     */
    public GameEventSunnyWeather() {
        super(GameEventType.NEUTRAL);
    }

    /**
     * Méthode pour les événements neutres.
     */
    @Override
    public void firstTimeActivation() {
        if (getGravity()==1){
            MapData.addNewsList("Il ne se passe rien aujourd'hui.");
        }
        else if (getGravity()==2){
            MapData.addNewsList("Il fait un peu nuageux aujourd'hui!");

        }
        else{
            MapData.addNewsList("Il fait très soleil aujourd'hui!");

        }
    }
}
