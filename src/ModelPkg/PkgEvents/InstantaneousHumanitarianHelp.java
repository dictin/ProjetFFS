package ModelPkg.PkgEvents;

import ControllerPkg.MasterController;
import ModelPkg.MapData;
import ModelPkg.PlayerData;

import javax.swing.*;
import java.util.Random;


public class InstantaneousHumanitarianHelp extends InstantaneousGameEvent {
    private ImageIcon carePackage = new ImageIcon("IMG/care package.jpg");
    private MasterController masterController;

    /**
     * Constructeur pour l'événement d'aide humanitaire
     * @param masterController constructeur principal qui va être utiliser pour modifier la quantité de nourriture du joueur.
     */
    public InstantaneousHumanitarianHelp(MasterController masterController) {
        super(GameEventType.GOOD);
        this.masterController = masterController;
    }

    /**
     *Détermine aléatoirement quel pays vient en aide au joueur et défini la quantité de nourriture gagné et l'ajoute à celle du joueur
     */
    public void firstTimeActivation() {
         Random rnd=new Random();
        int foodReceived=getGravity()*10;
        int helpCountry=rnd.nextInt(5);
        switch (helpCountry){
        case 0: MapData.addNewsList("Madagascar nous envoit "+foodReceived+" nourritures!");
            break;
            case 1: MapData.addNewsList("La ville de Gotham nous envoit "+foodReceived+" nourritures!");
                break;
            case 2: MapData.addNewsList("Laval nous envoit "+foodReceived+" nourritures!");
                break;
            case 3: MapData.addNewsList("Tahiti nous envoit "+foodReceived+" nourritures!");
                break;
            case 4: MapData.addNewsList("Fondcombe nous envoit "+foodReceived+" nourritures!");
                break;
        }
        masterController.getPlayerDataController().addFood(foodReceived);
    }

}
