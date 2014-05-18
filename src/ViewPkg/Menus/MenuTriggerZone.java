package ViewPkg.Menus;

import ControllerPkg.MasterController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Xav on 05/03/14.
 */
public class MenuTriggerZone extends JComponent{

    private MasterController controller;
    private int currentState=0;

    /**
     * Constructeur du MenuTriggerZone
     * @param controller le contrôleur principal
     */
    public MenuTriggerZone(final MasterController controller){
        this.controller=controller;
        this.setSize(new Dimension(350, 650));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                controller.enterMenuTriggerZone();
            }
        });
        this.setBackground(Color.BLUE);
    }
}