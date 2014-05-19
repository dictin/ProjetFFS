package ModelPkg.PkgEvents;

import ControllerPkg.MasterController;
import ModelPkg.Fourmillier;
import ModelPkg.MapData;
import ModelPkg.SmellType;

import javax.swing.*;
import java.awt.*;

public class LingeringAlmightyBlessing extends LingeringGameEvents {
    /**
     * Constructeur pour l'événement de bénédiction des dieux. Il s'agit d'un bon événement avec une durée.
     */
    private MasterController masterController;
    private Fourmillier spawnedAngelFourmilier;
    int life;
    int duration = 0;
    public LingeringAlmightyBlessing(MasterController masterController) {
        super(GameEventType.GOOD, 2);
        this.masterController = masterController;
        life = getGravity()+10;
    }

    @Override
    public void firstTimeActivation() {
        MapData.addNewsList("Le chaman nous envoit du renfort!");
        long uniqueID = System.currentTimeMillis();
        String animalType;
        Point spawn = MapData.getSpawnPoint();
        Point spawnPoint=MapData.getSpawnPoint();
        spawnedAngelFourmilier=new Fourmillier(0, MapData.getFourmilierActualRaceStats1Tab(), "Angel", spawnPoint, uniqueID, SmellType.ALLY, masterController);
        MapData.getAnimalList().add(spawnedAngelFourmilier);
        MapData.getCase(spawnPoint).setOccupant(spawnedAngelFourmilier);
    }

    @Override
    public void lingeringActivation() {
        if(life == duration){
            MasterController.disposeAnimal(spawnedAngelFourmilier);
        }else{
            duration++;
        }
    }
}
