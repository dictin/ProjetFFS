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

    /**
     * Constructeur du menu principal
     * @param controller le contrôleur principal
     */
    public MainMenu(final MasterController controller){
        super(controller, "main_menu");
        shopIcon = new GotoMenuButton(controller, "shop_button", new Dimension(250,100), 2);
        inventoryIcon = new GotoMenuButton(controller, "inventory_button", new Dimension(250,100), 8);
        creationButton = new GotoMenuButton((controller),"creation_button", new Dimension(250,100), 8);
        this.add(inventoryIcon);
        this.add(shopIcon);
        this.add(creationButton);
        shopIcon.setLocation(0, 25);
        inventoryIcon.setLocation(0, 160);
        creationButton.setLocation(0,400);
    }

    /**
     * Méthode qui actualise les images des menus
     */
    public void actualiser(){
        if (shopIcon.isAnimatedNow()){
            shopIcon.actualiser();
        }
        else if(inventoryIcon.isAnimatedNow()){
            inventoryIcon.actualiser();
        }
        else if(creationButton.isAnimatedNow()){
            creationButton.actualiser();
        }
        this.invalidate();
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
    }
}
