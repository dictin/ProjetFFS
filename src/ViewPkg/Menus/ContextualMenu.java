package ViewPkg.Menus;

import ControllerPkg.Controller;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Xav on 03/03/14.
 */
public abstract class ContextualMenu extends JComponent {

    private Image backgroundImage;
    private String menuName;
    private Dimension menuZone=new Dimension(1000,1000);

    public ContextualMenu(final Controller controller, String menuName){
        this.menuName=menuName;
        backgroundImage=Toolkit.getDefaultToolkit().getImage(menuName+ ".gif");

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
        graphics.drawImage(backgroundImage, 0, 0, this);
    }
}