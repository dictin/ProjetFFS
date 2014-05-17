package ControllerPkg;

import ModelPkg.MapData;
import ModelPkg.WildObjects.FoodSource;
import ViewPkg.VisualCase;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class VisualCaseHandler implements MouseListener {


    MasterController controller;

    public VisualCaseHandler(MasterController controller){
        this.controller = controller;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Quantity :" + ((FoodSource) MapData.getCase(((VisualCase) e.getSource()).getCaseCoord()).getWildObject()).getFoodQuantity());

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
