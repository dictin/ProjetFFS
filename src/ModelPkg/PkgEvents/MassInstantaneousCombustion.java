package ModelPkg.PkgEvents;

import ControllerPkg.MasterController;
import ModelPkg.Animal;
import ModelPkg.MapData;

import java.util.ArrayList;


public class MassInstantaneousCombustion extends InstantaneousGameEvent{

    /**
     * Instance de MasterController
     */
    private MasterController masterController;

    /**
     * Constructeur pour l'événement de chaleur extrême. Cet événement est mauvais et instantanné.
     * @param masterController
     */
    public MassInstantaneousCombustion(MasterController masterController) {
        super(GameEventType.BAD);
        this.masterController = masterController;
    }
/**
 * Méthode qui élimine une certaine quantité de fourmilier
 */
    @Override
    public void firstTimeActivation() {
        int victimNumber=getGravity();
        MapData.addNewsList("La température est de 100C! Tous aux abris!");
        ArrayList<Animal> animalArrayList = MapData.getAnimalList();
        if (animalArrayList.size() <= victimNumber){
            //dispose everything
            masterController.removeAllFourmilier();
        }
        else{
            int populationAfter = animalArrayList.size()-victimNumber;
            while((animalArrayList.size()) != populationAfter){
              masterController.disposeAnimal(animalArrayList.remove(0));
            }
        }


    }
}
