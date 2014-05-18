package ControllerPkg;

import ModelPkg.MapData;
import ModelPkg.Smell;
import ViewPkg.VisualCase;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class VisualCaseHandler implements MouseListener {


    MasterController controller;
//TODO What to write here?
    /**
     *
     * @param controller contrôleur principal
     */
    public VisualCaseHandler(MasterController controller){
        this.controller = controller;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
