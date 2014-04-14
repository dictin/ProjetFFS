package ViewPkg.Menus;

import ControllerPkg.MasterController;
import ControllerPkg.ShopListHandler;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Xav on 17/03/14.
 */
public class InventoryMenu extends ContextualMenu{

    private GotoMenuButton consumables;
    private GotoMenuButton reusables;
    private JLabel consumablesLabel = new JLabel("Objets cosumables!");
    private JLabel reusablesLabel = new JLabel ("Objets réutilisables!");

    JList<String> shopList;
    JScrollPane scrollPane;

    public InventoryMenu(MasterController controller) {
        super(controller, "inventory_menu");

        consumablesLabel.setSize(400,35);
        consumablesLabel.setLocation(25,0);
        consumablesLabel.setFont(new Font("Arial", Font.PLAIN, 30));



        consumables = new GotoMenuButton(controller, "consumables_button", new Dimension(this.getWidth()-10, this.getHeight()/2-10), Color.cyan);
        consumables.setLocation(5, 5);
        this.add(consumables);

        reusables = new GotoMenuButton(controller, "reusables_button", consumables.getSize(), Color.RED);
        reusables.setLocation(5, 5 + 10 + reusables.getHeight());
        this.add(reusables);

        this.shopList = new JList<String>();
        this.shopList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.shopList.setLayoutOrientation(JList.VERTICAL);
        this.shopList.setVisibleRowCount(-1);
        this.shopList.setFixedCellHeight(25);
        this.shopList.setSize(new Dimension(300, 220));
        this.shopList.setLocation(10, 12);
        this.shopList.addListSelectionListener(new ShopListHandler(controller.getShopInfoController().getShopItemInfoData()));

        scrollPane = new JScrollPane(this.shopList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollPane.setLocation(10,40);
        this.scrollPane.setSize(300, 220);
        this.consumables.add(this.scrollPane);
        this.consumables.add(consumablesLabel);


    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
    }

    @Override
    public void actualiser() {

    }
}
