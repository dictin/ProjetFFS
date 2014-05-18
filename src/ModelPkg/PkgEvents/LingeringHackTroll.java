package ModelPkg.PkgEvents;


import ModelPkg.MapData;

public class LingeringHackTroll extends LingeringGameEvents{

    public LingeringHackTroll() {
        super(GameEventType.GOOD, 2);
    }
    @Override
    public void firstTimeActivation() {
        MapData.addNewsList("Erreur! Un virus essaye de prendre le contrôle!");
    }

    @Override
    public void lingeringActivation() {

    }
}
