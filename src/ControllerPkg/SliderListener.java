package ControllerPkg;


import ModelPkg.Laboratory;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SliderListener implements ChangeListener{

    @Override
    public void stateChanged(ChangeEvent e) {
        System.out.println("!");
        Laboratory.setMoving(true);
    }
}
