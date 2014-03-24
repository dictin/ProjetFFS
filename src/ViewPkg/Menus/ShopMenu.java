package ViewPkg.Menus;

import ControllerPkg.ItemController;
import ControllerPkg.MasterController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Xav on 05/03/14.
 */
public class ShopMenu extends ContextualMenu{

    ItemController itemController;
    JComponent shopInfoMenu = new ShopMenuInfo();

    JComponent shopList = new JList();

    public ShopMenu(final MasterController controller){
        super(controller, "shop_menu");
        this.itemController = controller.getItemController();
        //shopList.sets
        this.setBackground(Color.green);
        this.shopInfoMenu.setLocation(0, 275);
        this.add(this.shopInfoMenu);

    }

    @Override
    public void actualiser() {
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.GREEN);
        g.fillRect(0,0,320,325);
    }
}