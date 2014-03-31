package ControllerPkg;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuyButtonHandler implements ActionListener {

    JList<String> list;

    public BuyButtonHandler(JList<String> list){
        super();
        this.list = list;


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        this.list.getSelectedIndex();
        System.out.println(this.list.getSelectedIndex());
    }
}
