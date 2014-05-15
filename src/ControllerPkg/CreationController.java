package ControllerPkg;

import ModelPkg.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreationController implements ActionListener {

    private MasterController masterController;

    public CreationController(final MasterController masterController){
        this.masterController = masterController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO add food limitation
        //MapData.getCase(coords).setOccupant(new Fourmillier(0, new int[]{13,13,13}, "Myrmidon"));

        String animalType;
        JButton source = (JButton) e.getSource();
        Point spawn = MapData.getSpawnPoint();

        if (spawn != null){
            if(this.masterController.getPlayerDataController().getFood()>=100){
            animalType = CreationHashTable.getAssociatedValue(source);
            Point spawnPoint=MapData.getSpawnPoint();
            long uniqueID = System.currentTimeMillis();
            Fourmillier spawnedFourmilier=new Fourmillier(0, MapData.getFourmilierStatsTab(), animalType, spawnPoint, uniqueID, SmellType.ALLY,masterController);
            MapData.getAnimalList().add(spawnedFourmilier);
            MapData.getCase(spawnPoint).setOccupant(spawnedFourmilier);
            this.masterController.getPlayerDataController().newBorn();
            this.masterController.getPlayerDataController().spendFood(100);
            MapData.addNewsList(spawnedFourmilier.getName()+ " est né!!");

            }
            else{
                JOptionPane.showMessageDialog(null, "Nourriture insuffisante");
            }
        }


    }
}
