package ViewPkg.Menus;

import ControllerPkg.CreationController;
import ControllerPkg.HashtableController;
import ControllerPkg.MasterController;
import ControllerPkg.PlayerDataController;
import ModelPkg.MapData;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Chloé on 14-03-31.
 */
public class CreationMenu extends ContextualMenu {

    private String[] strings = {"Myrmidon", "TamanduaMexique","TamanduaNord", "Tamanoir"};
    private JButton[] jButtons = new JButton[this.strings.length];
    private CreationController creationController;
    private MasterController controller;
    private JButton fourmilier1 = new JButton();
    private JButton fourmilier2 = new JButton();
    private JButton fourmilier3 = new JButton();
    private JButton fourmilier4 = new JButton();
    private JLabel textfou1 = new JLabel(this.strings[0] + "   Coût: "+ MapData.getCostFourmilier(1));
    private JLabel textfou2 = new JLabel(this.strings[1]+ "   Coût: "+ MapData.getCostFourmilier(1));
    private JLabel textfou3 = new JLabel(this.strings[2]+ "   Coût: "+ MapData.getCostFourmilier(2));
    private JLabel textfou4 = new JLabel (this.strings[3]+ "   Coût: "+ MapData.getCostFourmilier(3));




    public CreationMenu(MasterController controller){
        super(controller, "creation_menu");
        this.creationController = new CreationController(controller);
        this.controller = controller;
        JLabel background = new JLabel();

        fourmilier1.setIcon(new ImageIcon("IMG/MyrmidonIcon.png") );

        fourmilier2.setVisible(false);
        fourmilier2.setIcon(new ImageIcon("IMG/TamanduaMexiqueIcon.png") );


        fourmilier3.setVisible(false);
        fourmilier3.setIcon(new ImageIcon("IMG/TamanduaNordIcon.png"));

        fourmilier4.setVisible(false);
        fourmilier4.setIcon(new ImageIcon("IMG/TamanoirIcon.png"));


        fourmilier1.setSize(100,100);
        textfou1.setSize(200,50);
        fourmilier1.setLocation(15, 115);
        textfou1.setLocation(120,145);
        this.add(textfou1);
        this.add(fourmilier1);
        fourmilier1.addActionListener(this.creationController);


        fourmilier2.setSize(100,100);
        textfou2.setSize(200,50);
        textfou2.setVisible(false);
        fourmilier2.setLocation(15, 220);
        textfou2.setLocation(120,250);
        this.add(textfou2);
        this.add(fourmilier2);
        fourmilier2.addActionListener(this.creationController);

        fourmilier3.setSize(100,100);
        textfou3.setSize(200,50);
        textfou3.setVisible(false);
        fourmilier3.setLocation(15, 325);
        textfou3.setLocation(120,355);
        this.add(textfou3);
        this.add(fourmilier3);
        fourmilier3.addActionListener(this.creationController);

        fourmilier4.setSize(100,100);
        textfou4.setSize(200,50);
        textfou4.setVisible(false);
        fourmilier4.setLocation(15, 430);
        textfou4.setLocation(120,460);
        this.add(textfou4);
        this.add(fourmilier4);
        fourmilier4.addActionListener(this.creationController);

        background.setVisible(true);
        background.setOpaque(true);
        background.setSize(300, 425);
        background.setIcon(new ImageIcon("IMG/CREATION_BACKGROUND.jpg"));
        background.setLocation(10,110);
        this.add(background);

        this.jButtons[0] = fourmilier1;
        this.jButtons[1] = fourmilier2;
        this.jButtons[2] = fourmilier3;
        this.jButtons[3] = fourmilier4;

        HashtableController.InitializeHashtable(this.jButtons, this.strings);
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
    }

    @Override
    public void actualiser() {
    changeFourmilierVisibility();
    }
    //TODO Faire le lien avec le changement de level et la visibilité des fourmiliers.
    public void changeFourmilierVisibility(){
        switch (controller.getPlayerDataController().getLevel()){
            case 1: break;
            case 2: fourmilier2.setVisible(true);
                textfou2.setText(this.strings[1] + "   Coût: "+ MapData.getCostFourmilier(1));
                textfou2.setVisible(true);
                break;
            case 3: fourmilier2.setVisible(true);
                textfou2.setText(this.strings[1] + "   Coût: "+ MapData.getCostFourmilier(1));
                textfou2.setVisible(true);
                fourmilier3.setVisible(true);
                textfou3.setText(this.strings[2] + "   Coût: "+ MapData.getCostFourmilier(2));
                textfou3.setVisible(true);
            case 4:
                fourmilier2.setVisible(true);
                textfou2.setText(this.strings[1] + "   Coût: "+ MapData.getCostFourmilier(1));
                textfou2.setVisible(true);
                fourmilier3.setVisible(true);
                textfou2.setText(this.strings[2] + "   Coût: "+ MapData.getCostFourmilier(2));
                textfou3.setVisible(true);
                fourmilier4.setVisible(true);
                textfou2.setText(this.strings[3] + "   Coût: "+ MapData.getCostFourmilier(3));
                textfou4.setVisible(true);
        }
    }
}
