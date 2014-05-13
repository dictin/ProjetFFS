package ControllerPkg;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SliderListener implements PropertyChangeListener{

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        JSlider source = (JSlider)evt.getSource();
        System.out.println("hihihihi");

    }
}
