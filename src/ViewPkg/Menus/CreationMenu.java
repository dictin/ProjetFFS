package ViewPkg.Menus;

import ControllerPkg.CreationController;
import ControllerPkg.HashtableController;
import ControllerPkg.MasterController;
import ModelPkg.MapData;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Chloé on 14-03-31.
 */
public class CreationMenu extends ContextualMenu {

    /**
     * Tableau contenant les noms d'espèces des fourmiliers.
     */
    private String[] strings = {"Myrmidon", "TamanduaMexique","TamanduaNord", "Tamanoir"};
    /**
     * Tableau contenant les boutons faisant apparaître les fourmiliers.
     */
    private JButton[] jButtons = new JButton[this.strings.length];
    /**
     * Contrôleur du menu de création gérant les évènements.
     */
    private CreationController creationController;
    /**
     * Contrôleur principal.
     */
    private MasterController controller;
    /**
     * Bouton faisant apparaître un myrmidon.
     */
    private JButton boutonMyrmidon = new JButton();
    /**
     * Bouton faisant apparaître un tamandua du Mexique.
     */
    private JButton boutonMexique = new JButton();
    /**
     * Bouton faisant apparaître un tamandua du nord.
     */
    private JButton boutonNord = new JButton();
    /**
     * Bouton faisant apparaître un tamanoir.
     */
    private JButton boutonTamanoir = new JButton();
    /**
     * Texte affiché à côté du bouton du myrmidon.
     */
    private JLabel textMyrmidon = new JLabel(this.strings[0] + "   Coût: "+ MapData.getCostFourmilier(1));
    /**
     * Texte affiché à côté du bouton du tamandua du Mexique.
     */
    private JLabel textMexique = new JLabel(this.strings[1]+ "   Coût: "+ MapData.getCostFourmilier(1));
    /**
     * Texte affiché à côté du bouton du tamndua du nord.
     */
    private JLabel textNord = new JLabel(this.strings[2]+ "   Coût: "+ MapData.getCostFourmilier(2));
    /**
     * Texte affiché à côté du bouton du tamanoir.
     */
    private JLabel textTamanoir = new JLabel (this.strings[3]+ "   Coût: "+ MapData.getCostFourmilier(3));


    /**
     * Constructeur de CreationMenu
     * @param controller le contrôleur principal
     */
    public CreationMenu(MasterController controller){
        super(controller, "creation_menu");
        this.creationController = new CreationController(controller);
        this.controller = controller;
        JLabel background = new JLabel();

        boutonMyrmidon.setIcon(new ImageIcon("IMG/MyrmidonIcon.png"));

        boutonMexique.setVisible(false);
        boutonMexique.setIcon(new ImageIcon("IMG/TamanduaMexiqueIcon.png"));


        boutonNord.setVisible(false);
        boutonNord.setIcon(new ImageIcon("IMG/TamanduaNordIcon.png"));

        boutonTamanoir.setVisible(false);
        boutonTamanoir.setIcon(new ImageIcon("IMG/TamanoirIcon.png"));


        boutonMyrmidon.setSize(100, 100);
        textMyrmidon.setSize(200, 50);
        boutonMyrmidon.setLocation(15, 115);
        textMyrmidon.setLocation(120, 145);
        this.add(textMyrmidon);
        this.add(boutonMyrmidon);
        boutonMyrmidon.addActionListener(this.creationController);


        boutonMexique.setSize(100, 100);
        textMexique.setSize(200, 50);
        textMexique.setVisible(false);
        boutonMexique.setLocation(15, 220);
        textMexique.setLocation(120, 250);
        this.add(textMexique);
        this.add(boutonMexique);
        boutonMexique.addActionListener(this.creationController);

        boutonNord.setSize(100, 100);
        textNord.setSize(200, 50);
        textNord.setVisible(false);
        boutonNord.setLocation(15, 325);
        textNord.setLocation(120, 355);
        this.add(textNord);
        this.add(boutonNord);
        boutonNord.addActionListener(this.creationController);

        boutonTamanoir.setSize(100, 100);
        textTamanoir.setSize(200, 50);
        textTamanoir.setVisible(false);
        boutonTamanoir.setLocation(15, 430);
        textTamanoir.setLocation(120, 460);
        this.add(textTamanoir);
        this.add(boutonTamanoir);
        boutonTamanoir.addActionListener(this.creationController);

        background.setVisible(true);
        background.setOpaque(true);
        background.setSize(300, 425);
        background.setIcon(new ImageIcon("IMG/CREATION_BACKGROUND.jpg"));
        background.setLocation(10, 110);
        this.add(background);

        this.jButtons[0] = boutonMyrmidon;
        this.jButtons[1] = boutonMexique;
        this.jButtons[2] = boutonNord;
        this.jButtons[3] = boutonTamanoir;

        HashtableController.InitializeHashtable(this.jButtons, this.strings);
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
    }

    /**
     * Méthode qui actualise les fourmiliers visible dans le menu de création
     */
    @Override
    public void actualiser() {
    changeFourmilierVisibility();
    }

    /**
     * Méthode qui vérifier quel est le level du joueur et qui modifie les fourmiliers disponible dans le menu creation
     */
    public void changeFourmilierVisibility(){
        switch (controller.getPlayerDataController().getLevel()){
            case 1: break;
            case 2: boutonMexique.setVisible(true);
                textMexique.setText(this.strings[1] + "   Coût: " + MapData.getCostFourmilier(1));
                textMexique.setVisible(true);
                break;
            case 3: boutonMexique.setVisible(true);
                textMexique.setText(this.strings[1] + "   Coût: " + MapData.getCostFourmilier(1));
                textMexique.setVisible(true);
                boutonNord.setVisible(true);
                textNord.setText(this.strings[2] + "   Coût: " + MapData.getCostFourmilier(2));
                textNord.setVisible(true);
            case 4:
                boutonMexique.setVisible(true);
                textMexique.setText(this.strings[1] + "   Coût: " + MapData.getCostFourmilier(1));
                textMexique.setVisible(true);
                boutonNord.setVisible(true);
                textMexique.setText(this.strings[2] + "   Coût: " + MapData.getCostFourmilier(2));
                textNord.setVisible(true);
                boutonTamanoir.setVisible(true);
                textMexique.setText(this.strings[3] + "   Coût: " + MapData.getCostFourmilier(3));
                textTamanoir.setVisible(true);
        }
    }
}
