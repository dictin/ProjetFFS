package ViewPkg.Menus;

import ControllerPkg.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Xav on 03/03/14.
 */
public class ContextualMenu extends JComponent {

    private Image backgroundImage;
    private String menuName;
    private Dimension menuZone=new Dimension();

    public ContextualMenu(final Controller controller, String menuName){
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                ((ContextualMenu)e.getSource()).setVisible(false);
            }
        });
        this.menuName=menuName;
        backgroundImage=Toolkit.getDefaultToolkit().getImage(menuName+ ".gif");

        this.setVisible(false);
    }

    public String getMenuName() {
        return menuName;
    }


    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.drawImage(backgroundImage, 0, 0, this);
    }
}