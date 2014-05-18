package ModelPkg;

import ControllerPkg.MasterController;

import java.awt.*;

public class Fourmillier extends Animal {
    private MasterController masterController;

    /**
     * Constructeur des fourmiliers.
     * @param team équipe du fourmilier
     * @param meanStats tableau avec les statistiques de base du fourmilier
     * @param subSpecies nom de l'espèce de fourmilier
     * @param startingPosition position de départ du fourmilier
     * @param uniqueID identification du fourmilier
     * @param smellType type d'odeur du fourmilier
     * @param masterController le contrôleur principal
     */
    public Fourmillier(int team, int[] meanStats, String subSpecies, Point startingPosition, long uniqueID, SmellType smellType,final MasterController masterController){
        super(team, meanStats, subSpecies, startingPosition, uniqueID, smellType,masterController);
        this.masterController=masterController;
    }



}
