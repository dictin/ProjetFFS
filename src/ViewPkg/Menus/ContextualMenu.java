package ViewPkg.Menus;

import ControllerPkg.MasterController;

import javax.swing.*;
import java.awt.*;

public abstract class ContextualMenu extends JComponent {

    /**
     * Nom du menu afin de l'identifier dans le code.
     */
    private String menuName;
    /**
     * Taille du menu.
     */
    private Dimension menuZone=new Dimension(325,650);

    /**
     * Constructeur de ContextualMenu
     * @param controller le contrôleur principal
     * @param menuName nom du menu
     */
    public ContextualMenu(final MasterController controller, String menuName){
        this.menuName=menuName;

        this.setSize(menuZone);

        this.setVisible(false);
    }

    /**
     * Méthode qui retourne le nom du menu
     * @return le nom du menu
     */
    public String getMenuName() {
        return menuName;
    }


    public abstract void actualiser();

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
    }
}