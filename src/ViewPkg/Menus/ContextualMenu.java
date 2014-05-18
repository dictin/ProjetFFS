package ViewPkg.Menus;

import ControllerPkg.MasterController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Xav on 03/03/14.
 */
public abstract class ContextualMenu extends JComponent {

    private String menuName;
    private Dimension menuZone=new Dimension(325,650);

    public ContextualMenu(final MasterController controller, String menuName){
        this.menuName=menuName;

        this.setSize(menuZone);

        this.setVisible(false);
    }

    public String getMenuName() {
        return menuName;
    }

    public abstract void actualiser();

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
    }
}