package ControllerPkg;

import ModelPkg.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreationController implements ActionListener {

    /**
     * Contrôleur principal
     */
    private MasterController masterController;
    /**
     * boolean qui vérifie si le joueur a assez de nourriture
     */
    private boolean enougthFood = true;
    /**
     * Type du fourmilier sélectionné
     */
    private int animalKind = 0;
    /**
     * Fourmilier qui va être créé
     */
    private Fourmillier spawnedFourmilier;

    /**
     * Constructeur pour le controller des créations de fourmiliers
     * @param masterController controller principale
     */
    public CreationController(final MasterController masterController){
        this.masterController = masterController;
    }

    /**
     *Pour gérer lorsqu'un fourmilier est sélectionné dans le menu création.
     * @param e source
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //MapData.getCase(coords).setOccupant(new Fourmillier(0, new int[]{13,13,13}, "Myrmidon"));

        String animalType;
        JButton source = (JButton) e.getSource();
        Point spawn = MapData.getSpawnPoint();

        if (spawn != null){
            enougthFood = true;
            animalType = CreationHashTable.getAssociatedValue(source);
            Point spawnPoint=MapData.getSpawnPoint();
            long uniqueID = System.currentTimeMillis();
                switch(animalType){
                    case "Myrmidon":
                        animalKind = 0;
                        if(this.masterController.getPlayerDataController().getFood()>= MapData.getCostFourmilier(animalKind) ){
                         spawnedFourmilier=new Fourmillier(0, MapData.getFourmilierActualRaceStats1Tab(), animalType, spawnPoint, uniqueID, SmellType.ALLY,masterController);
                        }
                        else{
                            enougthFood = false;
                        }
                        break;
                    case "TamanduaMexique":
                        animalKind = 1;
                        if(this.masterController.getPlayerDataController().getFood()>=  MapData.getCostFourmilier(animalKind)){
                        spawnedFourmilier=new Fourmillier(0, MapData.getFourmilierFixRaceStats1(), animalType, spawnPoint, uniqueID, SmellType.ALLY,masterController);
                        }
                        else{
                            enougthFood = false;
                        }

                        break;
                    case "TamanduaNord":
                        animalKind = 2;
                        if(this.masterController.getPlayerDataController().getFood()>= MapData.getCostFourmilier(animalKind)){
                        spawnedFourmilier=new Fourmillier(0, MapData.getFourmilierFixRaceStats2(), animalType, spawnPoint, uniqueID, SmellType.ALLY,masterController);
                        }
                        else{
                            enougthFood = false;
                        }
                        break;
                    case "Tamanoir":animalKind = 3;
                        if(this.masterController.getPlayerDataController().getFood()>= MapData.getCostFourmilier(animalKind)){
                        spawnedFourmilier=new Fourmillier(0, MapData.getFourmilierFixRaxceStats3(), animalType, spawnPoint, uniqueID, SmellType.ALLY,masterController);
                        }
                        else{
                            enougthFood = false;
                        }
                        break;
                }
            if(enougthFood){
                MapData.getAnimalList().add(spawnedFourmilier);
                MapData.getCase(spawnPoint).setOccupant(spawnedFourmilier);
                this.masterController.getPlayerDataController().newBorn();
                this.masterController.getPlayerDataController().spendFood(MapData.getCostFourmilier(animalKind));
               // MapData.addNewsList(spawnedFourmilier.getName() + " est né!!");
            }
            else{
                JOptionPane.showMessageDialog(null, "Nourriture insuffisante");
            }
        }


    }
}
