package ViewPkg.Menus;

import ControllerPkg.ViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Xav on 05/03/14.
 */
public class MenuTriggerZone extends JComponent{

    private ViewController controller;
    private int currentState=0;

    public MenuTriggerZone(final ViewController controller){
        this.controller=controller;
        this.setSize(new Dimension(350, 650));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                controller.enterMenuTriggerZone();
            }
        });
        this.setBackground(Color.blue);
    }
}