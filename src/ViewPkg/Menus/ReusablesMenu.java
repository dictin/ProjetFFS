package ViewPkg.Menus;

import ControllerPkg.MasterController;

import java.awt.*;


public class ReusablesMenu extends ContextualMenu {

    public ReusablesMenu(MasterController controller, String menuName) {
        super(controller, menuName);
    }

    @Override
    public void actualiser() {
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(Toolkit.getDefaultToolkit().getImage("IMG/"+"SHOP_BACKGROUND.jpg"), 0, 0, this);
    }
}
