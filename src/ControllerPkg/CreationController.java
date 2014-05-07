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
            animalType = CreationHashTable.getAssociatedValue(source);
            Point spawnPoint=MapData.getSpawnPoint();
            long uniqueID = System.currentTimeMillis();
            Fourmillier spawnedFourmilier=new Fourmillier(0, new int[]{13,13,13}, animalType, spawnPoint, uniqueID, SmellType.ALLY);
            MapData.getAnimalList().add(spawnedFourmilier);
            MapData.getCase(spawnPoint).setOccupant(spawnedFourmilier);
            this.masterController.getPlayerDataController().newBorn();

             MapData.addNewsList(spawnedFourmilier.getName()+ " est né!!");

        }


    }
}
