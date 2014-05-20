package ViewPkg.Menus;

import ControllerPkg.BuyButtonHandler;
import ControllerPkg.ItemController;
import ControllerPkg.MasterController;
import ControllerPkg.ShopListHandler;

import javax.swing.*;
import java.awt.*;

public class ShopMenu extends ContextualMenu{

    /**
     * Contrôleur gérant les objets
     */
    ItemController itemController;
    /**
     * Composant contenant les informations sur l'objet sélectionné dans le magasin.
     */
    JComponent shopInfoMenu;
    /**
     * Liste des objets dans le magasin.
     */
    JList<String> shopList;
    /**
     * Panneau défilant affichant les objets de la liste.
     */
    JScrollPane scrollPane;
    /**
     * Bouton confirmant l'achat d'un objet.
     */
    JButton buyButton=new JButton();

    /**
     * Constructeur du menu du magasin
     * @param controller le contrôleur principal
     */
    public ShopMenu(final MasterController controller){
        super(controller, "shop_menu");

        buyButton.setIcon(new ImageIcon("IMG/BUY_BUTTON.jpg"));

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

        shopList.setBackground(new Color(186,139,106));

        scrollPane = new JScrollPane(this.shopList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollPane.setLocation(10,12);
        this.scrollPane.setSize(300,220);
        this.add(this.scrollPane);

        this.buyButton.setSize(100,30);
        this.buyButton.setLocation(200, 238);
        this.add(this.buyButton);
        this.buyButton.addActionListener(new BuyButtonHandler(this.shopList, controller));
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
        g.drawImage(Toolkit.getDefaultToolkit().getImage("IMG/SHOP_BACKGROUND.jpg"), 0, 0, this);
    }
}