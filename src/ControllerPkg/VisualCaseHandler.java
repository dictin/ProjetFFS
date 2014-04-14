package ControllerPkg;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class VisualCaseHandler implements MouseListener {


    MasterController controller;

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
