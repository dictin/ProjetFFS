package ControllerPkg;

import ModelPkg.MapData;
import ModelPkg.Smell;
import ModelPkg.WildObjects.FoodSource;
import ViewPkg.VisualCase;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class VisualCaseHandler implements MouseListener {


    MasterController controller;

    public VisualCaseHandler(MasterController controller){
        this.controller = controller;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        ArrayList<Smell> smells=MapData.getCase(((VisualCase) e.getSource()).getCaseCoord()).getSortedSmellArrayList();
        System.out.println("START");
        for (int i=0; i<smells.size();i++){
            System.out.println(smells.get(i).getType()+":"+smells.get(i).getIntensity());
        }

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
