package ControllerPkg;

import ModelPkg.Fourmillier;
import ModelPkg.MapData;
import ViewPkg.VisualCase;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class VisualCaseHandler implements MouseListener {


    MasterController controller;

    public VisualCaseHandler(MasterController controller){
        this.controller = controller;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point coords = ((VisualCase)e.getSource()).getCaseCoord();
        MapData.getCase(coords).setOccupant(new Fourmillier(0, new int[]{13,13,13}, "test"));

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.controller.setGridToActive();


    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
