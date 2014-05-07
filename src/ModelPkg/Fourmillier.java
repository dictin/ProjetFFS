package ModelPkg;

import ControllerPkg.MasterController;

import java.awt.*;

public class Fourmillier extends Animal {
    private MasterController masterController;


    public Fourmillier(int team, int[] meanStats, String subSpecies, Point startingPosition, long uniqueID, SmellType smellType,final MasterController masterController){
        super(team, meanStats, subSpecies, startingPosition, uniqueID, smellType,masterController);
        this.masterController=masterController;
    }

    public void sacrifice(){

    }

}
