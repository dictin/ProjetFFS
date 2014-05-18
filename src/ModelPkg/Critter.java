package ModelPkg;

import ControllerPkg.MasterController;

import java.awt.*;

public class Critter extends Animal {
    private MasterController masterController;

    /**
     * Constructeur de Critter. Critter est un animal tout comme fourmilier, mais qui n'est pas dans la meme équipe
     * @param team équipe du critter
     * @param meanStats tableau avec les statistique de base du critter
     * @param species nom de l'espèce
     * @param startingPosition position de base du critter
     * @param animalID identification du critter
     * @param smellType type d'odeur du critter
     * @param masterController  le contrôleur principal
     */
    public Critter(int team, int[] meanStats, String species, Point startingPosition, long animalID, SmellType smellType,final MasterController masterController){
        //TODO remove placeholder int in constructor.
        super(team, meanStats, species, startingPosition, animalID, smellType,masterController);
        this.masterController=masterController;
    }

}
