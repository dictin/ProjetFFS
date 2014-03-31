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
        JLabel background = new JLabel();
        JButton fourmilier1 = new JButton(":D");
        JButton fourmilier2 = new JButton(":)");
        JButton fourmilier3 = new JButton("^^");
        JButton fourmilier4 = new JButton(";)");
        fourmilier1.setSize(100,100);
        fourmilier1.setLocation(15,15);
        this.add(fourmilier1);

        background.setVisible(true);
        background.setOpaque(true);
        background.setSize(300,800);
        background.setBackground(Color.ORANGE);
        background.setLocation(10,10);
        this.add(background);
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
    }

    @Override
    public void actualiser() {

    }
}
