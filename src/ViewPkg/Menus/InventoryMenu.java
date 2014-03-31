package ViewPkg.Menus;

import ControllerPkg.MasterController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Xav on 17/03/14.
 */
public class InventoryMenu extends ContextualMenu{

    private GotoMenuButton consumablesButton;
    private GotoMenuButton reusablesButton;
    private JLabel consumables = new JLabel("Objets cosumables!");
    private JLabel reusables = new JLabel ("Objets réutilisables!");

    public InventoryMenu(MasterController controller) {
        super(controller, "inventory_menu");
        consumables.setSize(400, 200);
        consumables.setLocation(30, 65);
        consumables.setFont(new Font("Arial", Font.PLAIN, 30));
        reusables.setSize(400,200);
        reusables.setLocation(30,405);
        reusables.setForeground(Color.white);
        reusables.setFont(new Font("Arial", Font.PLAIN, 30));
        this.add(consumables);
        this.add(reusables);
        consumablesButton= new GotoMenuButton(controller, "consumables_button", new Dimension(this.getWidth()-10, this.getHeight()/2-10), Color.cyan);
        this.add(consumablesButton);
        consumablesButton.setLocation(5, 5);
        reusablesButton= new GotoMenuButton(controller, "reusables_button", consumablesButton.getSize(), Color.RED);
        this.add(reusablesButton);
        reusablesButton.setLocation(5, 5 + 10 + reusablesButton.getHeight());


    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
    }

    @Override
    public void actualiser() {

    }
}
