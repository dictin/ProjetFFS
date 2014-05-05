package ControllerPkg;

import ModelPkg.CreationHashTable;
import ModelPkg.Fourmillier;
import ModelPkg.MapData;
import ModelPkg.Smell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreationController implements ActionListener {
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
            Fourmillier spawnedFourmilier=new Fourmillier(0, new int[]{13,13,13}, animalType, spawnPoint, Smell.ALLY_ODOR);
            MapData.getAnimalList().add(spawnedFourmilier);
            MapData.getCase(spawnPoint).setOccupant(spawnedFourmilier);

        }


    }
}
