package ViewPkg.Menus;

import ControllerPkg.MasterController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Chloé on 14-03-31.
 */
public class CreationMenu extends ContextualMenu {

    public CreationMenu(MasterController controller){
        super(controller, "creation_menu");
        JLabel background = new JLabel("Creation!");
        background.setVisible(true);
        background.setOpaque(true);
        background.setBackground(Color.ORANGE);
        background.setLocation(5,5);
        this.add(background);
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
    }

    @Override
    public void actualiser() {

    }
}
