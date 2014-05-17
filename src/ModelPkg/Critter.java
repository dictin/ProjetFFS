package ModelPkg;

import ControllerPkg.MasterController;

import java.awt.*;

public class Critter extends Animal {
    private MasterController masterController;

    public Critter(int team, int[] meanStats, String species, Point startingPosition, long animalID, SmellType smellType,final MasterController masterController){
        //TODO remove placeholder int in constructor.
        super(team, meanStats, species, startingPosition, animalID, smellType,masterController);
        this.masterController=masterController;
    }

}
