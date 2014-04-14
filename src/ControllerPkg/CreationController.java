package ControllerPkg;

import ModelPkg.CreationHashTable;
import ModelPkg.Fourmillier;
import ModelPkg.MapData;

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
            MapData.getCase(new Point(MapData.getSpawnPoint())).setOccupant(new Fourmillier(0, new int[]{13,13,13}, animalType));
            System.out.println("Banana");

        }


    }
}
