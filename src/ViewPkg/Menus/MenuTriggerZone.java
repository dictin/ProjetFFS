package ViewPkg.Menus;

import ControllerPkg.Controller;
import ModelPkg.Case;
import ViewPkg.VisualCase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Xav on 05/03/14.
 */
public class MenuTriggerZone extends JComponent{

    private Controller controller;
    private int currentState=0;

    public MenuTriggerZone(final Controller controller){
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