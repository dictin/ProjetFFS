package ViewPkg.Menus;

import ControllerPkg.MasterController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Xav on 03/03/14.
 */
public class MainMenu extends ContextualMenu{

    private GotoMenuButton shopIcon;
    private GotoMenuButton inventoryIcon;
    private GotoMenuButton creationButton;


    public MainMenu(final MasterController controller){
        super(controller, "main_menu");
        shopIcon = new GotoMenuButton(controller, "shop_button", new Dimension(250,100), Color.BLACK);
        inventoryIcon = new GotoMenuButton(controller, "inventory_button", new Dimension(250,100),Color.YELLOW);
        creationButton = new GotoMenuButton((controller),"creation_button", new Dimension(250,100), Color.CYAN);
        this.add(inventoryIcon);
        this.add(shopIcon);
        this.add(creationButton);
        shopIcon.setLocation(0, 25);
        inventoryIcon.setLocation(0, 160);
        creationButton.setLocation(0,400);

    }

    public void actualiser(){
        shopIcon.actualiser();
        this.invalidate();
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics graphics){

        super.paintComponent(graphics);
        graphics.setColor(Color.BLUE);
        graphics.fillRect(0,0,300,300);
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,350,300,200);

    }
}
