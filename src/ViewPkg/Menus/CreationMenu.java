package ViewPkg.Menus;

import ControllerPkg.MasterController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Chloé on 14-03-31.
 */
public class CreationMenu extends ContextualMenu {

    public CreationMenu(MasterController controller){
        super(controller, "creation_menu");
        JLabel background = new JLabel();
        JButton fourmilier1 = new JButton(":D");
        JLabel textfou1 = new JLabel("OHHH le petit cute fourmilier!");
        JButton fourmilier2 = new JButton(":)");
        JLabel textfou2 = new JLabel("Wow! Nice Chest/Bras!");
        JButton fourmilier3 = new JButton("^^");
        JLabel textfou3 = new JLabel("Quéssé ça???? O.o?");
        JButton fourmilier4 = new JButton(";)");
        JLabel textfou4 = new JLabel ("Very much wow fourmilier");

        fourmilier1.setSize(100,100);
        textfou1.setSize(200,50);
        fourmilier1.setLocation(15, 115);
        textfou1.setLocation(120,145);
        this.add(textfou1);
        this.add(fourmilier1);
        fourmilier1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("clicklick!");
            }
        });


        fourmilier2.setSize(100,100);
        textfou2.setSize(200,50);
        fourmilier2.setLocation(15, 220);
        textfou2.setLocation(120,250);
        this.add(textfou2);
        this.add(fourmilier2);
        fourmilier2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hihihi ça chatouille!!");
            }
        });

        fourmilier3.setSize(100,100);
        textfou3.setSize(200,50);
        fourmilier3.setLocation(15, 325);
        textfou3.setLocation(120,355);
        this.add(textfou3);
        this.add(fourmilier3);
        fourmilier3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Help! On me touche!!");
            }
        });

        fourmilier4.setSize(100,100);
        textfou4.setSize(200,50);
        fourmilier4.setLocation(15, 430);
        textfou4.setLocation(120,460);
        this.add(textfou4);
        this.add(fourmilier4);
        fourmilier4.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Much clicking such wow!");
            }
        });

        background.setVisible(true);
        background.setOpaque(true);
        background.setSize(300,425);
        background.setBackground(Color.ORANGE);
        background.setLocation(10,110);
        this.add(background);
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
    }

    @Override
    public void actualiser() {

    }
}
