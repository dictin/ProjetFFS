package ViewPkg;

import ControllerPkg.Controller;

import javax.naming.Context;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Xav on 03/03/14.
 */
public class ContextualMenu extends JComponent {

    private Dimension size=new Dimension(350, 650);
    private Image backgroundImage;
    private String menuName;

    public ContextualMenu(final Controller controller, String menuName){
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                ((ContextualMenu)e.getSource()).setVisible(false);
            }
        });
        this.menuName=menuName;
        backgroundImage=Toolkit.getDefaultToolkit().getImage(menuName+ ".gif");

        if (menuName=="shop_menu"){
            GotoMenuButton gtmb=new GotoMenuButton(controller, "quit_button", new Dimension(25,25) );
            this.add(gtmb);
            gtmb.setLocation(0,0);
        }
        this.setVisible(false);
    }

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.drawImage(backgroundImage, 0, 0, this);
    }
}