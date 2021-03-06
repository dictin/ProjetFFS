package ViewPkg.Menus;

import ControllerPkg.*;
import ModelPkg.PlayerData;
import ObserverPkg.Observer;

import javax.swing.*;
import java.awt.*;

public class InventoryMenu extends ContextualMenu implements Observer{

    /**
     * JComponent contenant le menu d'objet consommables.
     */
    private JComponent consumables;
    /**
     * JComponent contenant le menu d'objet consommables.
     */
    private JComponent reusables;
    /**
     * JLabel informant le joueur que la liste est la liste d'objets temporaires dans son inventaire.
     */
    private JLabel consumablesLabel = new JLabel("Bonus temporaires!");
    /**
     * JLabel informant le joueur que la liste est la liste d'objets permanents dans son inventaire.
     */
    private JLabel reusablesLabel = new JLabel ("Bonus permanents!");
    /**
     * Bouton activant le bonus d'un objet temporaire.
     */
    private JButton consumablesBuyButton = new JButton("Utiliser");
    /**
     * Bouton activant le bonus d'un objet permanent.
     */
    private JButton reusablesBuyButton = new JButton("Activer");
    /**
     * Contrôleur gérant les objets.
     */
    private ItemController itemController;
    /**
     * Contrôleur gérant des informations sur la partie en cours.
     */
    private PlayerDataController playerDataController;
    /**
     * Contrôleur principal.
     */
    private MasterController masterController;
    /**
     * Liste des noms des objets consommables.
     */
    private JList<String> consumableShopList;
    /**
     * Panneau défilant contenant les objets temporaires achetés par le joueur non activés.
     */
    JScrollPane consumableScrollPane;
    /**
     * Panneau défilant contenant les objets permanents achetés par le joueur non activés.
     */
    private JList<String> reusableShopList;
    /**
     * Panneau défilant contenant les objets permanents achetés par le joueur non activés.
     */
    private JScrollPane reusableScrollPane;
    /**
     * Contrôleur gérant l'activation d'objets temporaires.
     */
    private InventoryActivateButtonHandler InventoryActivateTempButtonHandler;
    /**
     * Contrôleur gérant l'activation d'objets permanents.
     */
    private InventoryActivateButtonHandler InventoryActivatePermButtonHandler;

    /**
     * Constructeur d'InventoryMenu
     * @param controller le contrôleur principal
     */
    public InventoryMenu(MasterController controller) {
        super(controller, "inventory_menu");
        this.masterController = controller;
        this.playerDataController = controller.getPlayerDataController();
        this.itemController = controller.getItemController();
        this.playerDataController.addObserver(this);
        consumablesLabel.setSize(400,35);
        consumablesLabel.setLocation(5, 0);
        consumablesLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        consumables = new GotoMenuButton(controller, "consumables_button", new Dimension(this.getWidth()-10, this.getHeight()/2-10),1);
        consumables.setLocation(5, 5);
        this.add(consumables);
        this.consumableShopList = new JList<String>();
        this.consumableShopList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.consumableShopList.setLayoutOrientation(JList.VERTICAL);
        this.consumableShopList.setVisibleRowCount(-1);
        this.consumableShopList.setFixedCellHeight(25);
        this.consumableShopList.setSize(new Dimension(300, 220));
        this.consumableShopList.setLocation(10, 12);
        this.consumableShopList.setBackground(new Color(32,172,32));
        // this.consumableShopList.addListSelectionListener(new ShopListHandler(controller.getShopInfoController().getShopItemInfoData()));
        // this.consumableShopList.addListSelectionListener(new ShopListHandler(controller.getShopInfoController().getShopItemInfoData()));
        this.consumableShopList.addListSelectionListener(new InventoryListHandler(masterController));


        consumableScrollPane = new JScrollPane(this.consumableShopList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.consumableScrollPane.setLocation(10, 40);
        this.consumableScrollPane.setSize(300, 220);
        this.consumables.add(this.consumableScrollPane);
        this.consumables.add(consumablesLabel);

        this.consumablesBuyButton.setSize(100,30);
        this.consumablesBuyButton.setIcon(new ImageIcon("IMG/GO_BUTTON.jpg"));
        this.consumablesBuyButton.setLocation(200, 270);
        this.consumables.add(this.consumablesBuyButton);
        InventoryActivateTempButtonHandler = new InventoryActivateButtonHandler(this.masterController,this.consumableShopList, PlayerData.TEMP_ITEM);
        this.consumablesBuyButton.addActionListener(InventoryActivateTempButtonHandler);


        //************************************************************************************


        reusablesLabel.setSize(400, 35);
        reusablesLabel.setLocation(25, 0);
        reusablesLabel.setFont(new Font("Arial", Font.PLAIN, 30));


        reusables = new GotoMenuButton(controller, "reusables_button", consumables.getSize(),2);
        reusables.setLocation(5, 5 + 10 + reusables.getHeight());
        this.add(reusables);

        this.reusableShopList = new JList<String>();

        this.reusableShopList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.reusableShopList.setLayoutOrientation(JList.VERTICAL);
        this.reusableShopList.setVisibleRowCount(-1);
        this.reusableShopList.setFixedCellHeight(25);
        this.reusableShopList.setSize(new Dimension(300, 220));
        this.reusableShopList.setBackground(new Color(32, 172, 32));
        this.reusableShopList.setLocation(10, 12);
        //this.reusableShopList.addListSelectionListener(new ShopListHandler(controller.getShopInfoController().getShopItemInfoData()));
        this.reusableShopList.addListSelectionListener(new InventoryListHandler(masterController));


        this.reusableScrollPane = new JScrollPane(this.reusableShopList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.reusableScrollPane.setLocation(10,40);
        this.reusableScrollPane.setSize(300, 220);
        this.reusables.add(this.reusableScrollPane);
        this.reusables.add(reusablesLabel);

        this.reusablesBuyButton.setIcon(new ImageIcon("IMG/GO_BUTTON.jpg"));
        this.reusablesBuyButton.setSize(100, 30);
        this.reusablesBuyButton.setLocation(200, 270);
        this.reusables.add(this.reusablesBuyButton);
        InventoryActivatePermButtonHandler = new InventoryActivateButtonHandler(this.masterController,this.reusableShopList, PlayerData.PERM_ITEM);
        this.reusablesBuyButton.addActionListener(InventoryActivatePermButtonHandler);

    }


    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.drawImage(Toolkit.getDefaultToolkit().getImage("IMG/INVENTORY_BACKGROUND.jpg"), 0, 0, this);
    }


    @Override
    public void actualiser() {
        this.invalidate();
        this.repaint();


    }

    /**
     * Méthode qui actualise les listes des objets consumables et réutilisables
     */
    @Override
    public void update() {
        this.consumableShopList.setModel(this.playerDataController.getConsumableInventoryDataModel());
        this.reusableShopList.setModel(this.playerDataController.getPermanentInventoryDataModel());

    }
}
