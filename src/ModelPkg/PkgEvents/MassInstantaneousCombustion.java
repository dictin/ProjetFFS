package ModelPkg.PkgEvents;

import ControllerPkg.MasterController;
import ModelPkg.Animal;
import ModelPkg.MapData;

import java.util.ArrayList;

/**
 * Created by Xav on 13/05/14.
 */
public class MassInstantaneousCombustion extends InstantaneousGameEvent{
    private MasterController masterController;
    public MassInstantaneousCombustion(MasterController masterController) {
        super(GameEventType.BAD);
        this.masterController = masterController;
    }

    @Override
    public void firstTimeActivation() {
        int victimNumber=getGravity()*2;
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
