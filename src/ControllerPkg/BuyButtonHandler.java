package ControllerPkg;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuyButtonHandler implements ActionListener {

    JList<String> list;
    ItemController itemController;

    public BuyButtonHandler(JList<String> list, final MasterController controller){
        super();
        this.itemController = controller.getItemController();
        this.list = list;


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        this.list.getSelectedIndex();
        System.out.println(this.list.getSelectedIndex());
        itemController.setObjetBougthList(list.getSelectedIndex());
    }
}
