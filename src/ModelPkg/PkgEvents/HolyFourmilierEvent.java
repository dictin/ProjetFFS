package ModelPkg.PkgEvents;

import ControllerPkg.MasterController;
import ModelPkg.Fourmillier;
import ModelPkg.MapData;
import ModelPkg.SmellType;

import javax.swing.*;
import java.awt.*;

public class HolyFourmilierEvent extends InstantaneousGameEvent {
    /**
     * Constructeur pour l'événement de bénédiction des dieux. Il s'agit d'un bon événement avec une durée.
     */
    private MasterController masterController;
    private Fourmillier spawnedAngelFourmilier;
    int duration = 0;
    public HolyFourmilierEvent(MasterController masterController) {
        super(GameEventType.GOOD);
        this.masterController = masterController;
    }

    @Override
    public void firstTimeActivation() {
        MapData.addNewsList("Le chaman nous envoit du renfort!");
        long uniqueID = System.currentTimeMillis();
        Point spawnPoint=MapData.getSpawnPoint();
        spawnedAngelFourmilier=new Fourmillier(0, MapData.getFourmilierActualRaceStats1Tab(), "Angel", spawnPoint, uniqueID, SmellType.ALLY, masterController);
        MapData.getAnimalList().add(spawnedAngelFourmilier);
        MapData.getCase(spawnPoint).setOccupant(spawnedAngelFourmilier);
    }
}
