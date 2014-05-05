package ViewPkg.Menus;

import ControllerPkg.BuyButtonHandler;
import ControllerPkg.ItemController;
import ControllerPkg.MasterController;
import ControllerPkg.ShopListHandler;

import javax.swing.*;
import java.awt.*;

public class ShopMenu extends ContextualMenu{

    ItemController itemController;
    JComponent shopInfoMenu;

    JList<String> shopList;
    JScrollPane scrollPane;
    JButton buyButton = new JButton("Acheter");

    public ShopMenu(final MasterController controller){
        super(controller, "shop_menu");
        this.itemController = controller.getItemController();
        this.shopInfoMenu = new ShopMenuInfo(controller.getShopInfoController());
        this.shopList = new JList<String>(itemController.getItemList());
        this.shopList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.shopList.setLayoutOrientation(JList.VERTICAL);
        this.shopList.setVisibleRowCount(-1);
        this.shopList.setFixedCellHeight(25);
        this.shopList.setSize(new Dimension(300, 220));
        this.shopList.setLocation(10, 12);
        this.shopList.addListSelectionListener(new ShopListHandler(controller.getShopInfoController().getShopItemInfoData()));

        scrollPane = new JScrollPane(this.shopList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollPane.setLocation(10,12);
        this.scrollPane.setSize(300,220);
        this.add(this.scrollPane);

        this.buyButton.setSize(100,30);
        this.buyButton.setLocation(200,238);
        this.add(this.buyButton);
        this.buyButton.addActionListener(new BuyButtonHandler(this.shopList, controller));
        this.setBackground(Color.green);
        this.shopInfoMenu.setLocation(0, 275);
        this.add(this.shopInfoMenu);
    }

    @Override
    public void actualiser() {
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(0,0,320,325);
    }
}