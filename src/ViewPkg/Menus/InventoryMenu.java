package ViewPkg.Menus;

import ControllerPkg.ItemController;
import ControllerPkg.MasterController;
import ControllerPkg.ShopListHandler;
import ModelPkg.PkgItems.Items;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Xav on 17/03/14.
 */
public class InventoryMenu extends ContextualMenu{

    private GotoMenuButton consumables;
    private GotoMenuButton reusables;
    private JLabel consumablesLabel = new JLabel("Objets cosumables!");
    private JLabel reusablesLabel = new JLabel ("Objets réutilisables!");
    private JButton consumablesBuyButton = new JButton("Utiliser");
    private JButton reusablesBuyButton = new JButton("Activer");
    ItemController itemController;

    JList<String> consumableShopList;
    JScrollPane consumableScrollPane;

    JList<String> reusableShopList;
    JScrollPane reusableScrollPane;

    public InventoryMenu(MasterController controller) {
        super(controller, "inventory_menu");
        this.itemController = controller.getItemController();
        consumablesLabel.setSize(400,35);
        consumablesLabel.setLocation(25,0);
        consumablesLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        consumables = new GotoMenuButton(controller, "consumables_button", new Dimension(this.getWidth()-10, this.getHeight()/2-10), Color.cyan);
        consumables.setLocation(5, 5);
        this.add(consumables);
        watata();
        this.consumableShopList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.consumableShopList.setLayoutOrientation(JList.VERTICAL);
        this.consumableShopList.setVisibleRowCount(-1);
        this.consumableShopList.setFixedCellHeight(25);
        this.consumableShopList.setSize(new Dimension(300, 220));
        this.consumableShopList.setLocation(10, 12);
        this.consumableShopList.addListSelectionListener(new ShopListHandler(controller.getShopInfoController().getShopItemInfoData()));

        consumableScrollPane = new JScrollPane(this.consumableShopList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.consumableScrollPane.setLocation(10,40);
        this.consumableScrollPane.setSize(300, 220);
        this.consumables.add(this.consumableScrollPane);
        this.consumables.add(consumablesLabel);

        this.consumablesBuyButton.setSize(100,30);
        this.consumablesBuyButton.setLocation(200,270);
        this.consumables.add(this.consumablesBuyButton);


        //************************************************************************************


        reusablesLabel.setSize(400,35);
        reusablesLabel.setLocation(25,0);
        reusablesLabel.setFont(new Font("Arial", Font.PLAIN, 30));


        reusables = new GotoMenuButton(controller, "reusables_button", consumables.getSize(), Color.RED);
        reusables.setLocation(5, 5 + 10 + reusables.getHeight());
        this.add(reusables);

        this.reusableShopList = new JList<String>();

        this.reusableShopList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.reusableShopList.setLayoutOrientation(JList.VERTICAL);
        this.reusableShopList.setVisibleRowCount(-1);
        this.reusableShopList.setFixedCellHeight(25);
        this.reusableShopList.setSize(new Dimension(300, 220));
        this.reusableShopList.setLocation(10, 12);
        this.reusableShopList.addListSelectionListener(new ShopListHandler(controller.getShopInfoController().getShopItemInfoData()));

        this.reusableScrollPane = new JScrollPane(this.reusableShopList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.reusableScrollPane.setLocation(10,40);
        this.reusableScrollPane.setSize(300, 220);
        this.reusables.add(this.reusableScrollPane);
        this.reusables.add(reusablesLabel);

        this.reusablesBuyButton.setSize(100,30);
        this.reusablesBuyButton.setLocation(200,270);
        this.reusables.add(this.reusablesBuyButton);

    }

    public void watata(){

        System.out.println("Et cest le temps d'afficher l'inventaire!");
        String[] objetsInventory = new String[2];
        //String[] objetsInventory = new String[itemController.getObjetBougthList().size()];
        System.out.println("longueur: "+itemController.getObjetBougthList().size());
        for(int i = 0; i < itemController.getObjetBougthList().size(); i++){
            Items item = itemController.getBougthItem(itemController.getObjetBougth(i));
            objetsInventory[0]= "Allo";

        }

        //objetsInventory[i]= item.getName();
        objetsInventory[0]= "Coucou";
        System.out.println(objetsInventory[0]);
        this.consumableShopList = new JList<String>(objetsInventory);
        this.consumableShopList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.consumableShopList.setLayoutOrientation(JList.VERTICAL);
        this.consumableShopList.setVisibleRowCount(-1);
        this.consumableShopList.setFixedCellHeight(25);
        this.consumableShopList.setSize(new Dimension(300, 220));
        this.consumableShopList.setLocation(10, 12);


    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
    }

    @Override
    public void actualiser() {
        this.invalidate();
        this.repaint();
        watata();

    }
}
