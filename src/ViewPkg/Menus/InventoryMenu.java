package ViewPkg.Menus;

import ControllerPkg.ViewController;

import java.awt.*;

/**
 * Created by Xav on 17/03/14.
 */
public class InventoryMenu extends ContextualMenu{

    private GotoMenuButton consumablesButton;
    private GotoMenuButton reusablesButton;

    public InventoryMenu(ViewController controller) {
        super(controller, "inventory_menu");
        consumablesButton= new GotoMenuButton(controller, "consumables_button", new Dimension(this.getWidth()-10, this.getHeight()/2-10), Color.cyan);
        this.add(consumablesButton);
        consumablesButton.setLocation(5,5);
        reusablesButton= new GotoMenuButton(controller, "reusables_button", consumablesButton.getSize(), Color.green);
        this.add(reusablesButton);
        reusablesButton.setLocation(5, 5+10+reusablesButton.getHeight());

    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.setColor(new Color(42,81,42));

        graphics.fillRect(0,0,300,300);
    }

    @Override
    public void actualiser() {

    }
}
