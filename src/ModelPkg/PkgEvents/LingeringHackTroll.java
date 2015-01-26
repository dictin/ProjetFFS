package ModelPkg.PkgEvents;


import ControllerPkg.MasterController;
import ModelPkg.MapData;

public class LingeringHackTroll extends LingeringGameEvents{

    /**
     * Instance de MasterController
     */
    private MasterController masterController;
    /**
     * Constructeur pour l'événement de piratage. Cet événement est mauvais et avec une durée.
     */
    public LingeringHackTroll(MasterController masterController) {
        super(GameEventType.GOOD, 2);
        this.masterController= masterController;
    }

    /**
     * Méthode qui fait apparaitre une image qui empêche le joueur d'avoir accès aux menus.
     */
    @Override
    public void firstTimeActivation() {
        MapData.addNewsList("Erreur! Un virus essaye de prendre le contrôle!");
        masterController.activateHackView(true);
        this.setDuration(this.getGravity());
    }
    /**
     * Méthode qui vérifie si l'événement est terminé.
     */
    @Override
    public void lingeringActivation() {
        if (this.getDuration()==0){
            masterController.activateHackView(false);
        }
    }
}
