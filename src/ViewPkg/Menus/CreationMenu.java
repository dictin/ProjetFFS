package ViewPkg.Menus;

import ControllerPkg.CreationController;
import ControllerPkg.HashtableController;
import ControllerPkg.MasterController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Chloé on 14-03-31.
 */
public class CreationMenu extends ContextualMenu {

    private String[] strings = {"Myrmidon", "TamanduaMexique","TamanduaNord", "Tamanoir"};
    private JButton[] jButtons = new JButton[this.strings.length];
    private CreationController creationController;

    public CreationMenu(MasterController controller){
        super(controller, "creation_menu");
        this.creationController = new CreationController(controller);
        JLabel background = new JLabel();

        JButton fourmilier1 = new JButton();
        fourmilier1.setIcon(new ImageIcon("IMG/MyrmidonIcon.png") );
        JLabel textfou1 = new JLabel(this.strings[0]);




        JButton fourmilier2 = new JButton();
        fourmilier2.setIcon(new ImageIcon("IMG/TamanduaMexiqueIcon.png") );
        JLabel textfou2 = new JLabel(this.strings[1]);


        JButton fourmilier3 = new JButton();
        fourmilier3.setIcon(new ImageIcon("IMG/TamanduaNordIcon.png"));
        JLabel textfou3 = new JLabel(this.strings[2]);

        JButton fourmilier4 = new JButton();
        fourmilier4.setIcon(new ImageIcon("IMG/TamanoirIcon.png"));
        JLabel textfou4 = new JLabel (this.strings[3]);

        fourmilier1.setSize(100,100);
        textfou1.setSize(200,50);
        fourmilier1.setLocation(15, 115);
        textfou1.setLocation(120,145);
        this.add(textfou1);
        this.add(fourmilier1);
        fourmilier1.addActionListener(this.creationController);


        fourmilier2.setSize(100,100);
        textfou2.setSize(200,50);
        fourmilier2.setLocation(15, 220);
        textfou2.setLocation(120,250);
        this.add(textfou2);
        this.add(fourmilier2);
        fourmilier2.addActionListener(this.creationController);

        fourmilier3.setSize(100,100);
        textfou3.setSize(200,50);
        fourmilier3.setLocation(15, 325);
        textfou3.setLocation(120,355);
        this.add(textfou3);
        this.add(fourmilier3);
        fourmilier3.addActionListener(this.creationController);

        fourmilier4.setSize(100,100);
        textfou4.setSize(200,50);
        fourmilier4.setLocation(15, 430);
        textfou4.setLocation(120,460);
        this.add(textfou4);
        this.add(fourmilier4);
        fourmilier4.addActionListener(this.creationController);

        background.setVisible(true);
        background.setOpaque(true);
        background.setSize(300,425);
        background.setBackground(Color.ORANGE);
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

    }
}
