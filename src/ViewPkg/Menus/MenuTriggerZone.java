package ViewPkg.Menus;

import ControllerPkg.Controller;

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

    public MenuTriggerZone(final Controller controller){
        this.controller=controller;
        this.setSize(new Dimension(350, 650));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                controller.openMainMenu();
            }

            @Override
            public void mouseExited(MouseEvent e){
                controller.closeAllMenus();
            }
        });
        this.setBackground(Color.blue);
    }
}
