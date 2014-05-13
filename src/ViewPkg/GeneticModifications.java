package ViewPkg;


import ControllerPkg.MasterController;
import ControllerPkg.SliderListener;
import ModelPkg.MapData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeneticModifications extends JLabel {
    private JLabel laboratory = new JLabel();
    private MasterController controller;
    private JSlider speedEndurance;
    private JSlider attackDefence;
    private JSlider sensitivitySmellStrength;
    private JLabel laboratoryTitle = new JLabel("Bienvenu au laboratoire de modification génétique!");
    private JLabel laboratoryExplination1 = new JLabel("Chaque déplacement du curseur coûte 10 nourritures.");
    private JLabel laboratoryExplination2 = new JLabel("Plus le curseur se rapporche d'une compétence, plus cette compétence sera forte.");
    private Font font = new Font("Serif", Font.ITALIC, 12);
    private Font font2 = new Font("Couriel", Font.BOLD, 18);
    private Color color = new Color(Integer.parseInt("510968", 19));
    private JButton finish = new JButton("Finish");

    private int speedEnduranceBefore = MapData.getFourmilierStats(0);
    private int attackDefenceBefore = MapData.getFourmilierStats(1);
    private int sensitivitySmellStrengthBefore = MapData.getFourmilierStats(2);
    private int cost = 0;

    private JLabel speed = new JLabel("Vitesse");
    private JLabel endurance = new JLabel("Endurance");
    private JLabel attack = new JLabel("Attaque");
    private JLabel defence = new JLabel("Défence");
    private JLabel sensitivity = new JLabel("Odorat");
    private JLabel sensitivity2 = new JLabel("Sensible");
    private JLabel smellStrength = new JLabel("Odeur");

    public GeneticModifications(final MasterController controller){
        this.setOpaque(true);
        this.setBackground(color);
        this.setSize(500,375);
        this.controller=controller;

        laboratoryTitle.setSize(450,23);
        laboratoryExplination1.setSize(400,20);
        laboratoryExplination2.setSize(475,20);
        laboratoryTitle.setLocation(5, 5);
        laboratoryExplination1.setLocation(5,30);
        laboratoryExplination2.setLocation(5,50);
        laboratoryTitle.setFont(font2);
        this.add(laboratoryTitle);
        this.add(laboratoryExplination1);
        this.add(laboratoryExplination2);

        speed.setSize(50, 20);
        speed.setLocation(55, 95);
        this.add(speed);
        endurance.setSize(70, 20);
        endurance.setLocation(387, 95);
        this.add(endurance);
        speedEndurance = new JSlider(JSlider.HORIZONTAL,1, 24, MapData.getFourmilierStats(0));
        speedEndurance.setMajorTickSpacing(2);
        speedEndurance.setMinorTickSpacing(1);
        speedEndurance.setPaintTicks(true);
        speedEndurance.setPaintLabels(true);
        speedEndurance.setSize(280, 50);
        speedEndurance.setLocation(107, 100);
        speedEndurance.setFont(font);
        speedEndurance.setBackground(color);
        this.add(speedEndurance);
       // speedEndurance.addChangeListener(new SliderListener());
        speedEndurance.addPropertyChangeListener(new SliderListener());



        attack.setSize(50, 20);
        attack.setLocation(55, 195);
        this.add(attack);
        defence.setSize(70, 20);
        defence.setLocation(387, 195);
        this.add(defence);
        attackDefence = new JSlider(JSlider.HORIZONTAL,1, 24, MapData.getFourmilierStats(1));
        attackDefence.setMajorTickSpacing(2);
        attackDefence.setMinorTickSpacing(1);
        attackDefence.setPaintTicks(true);
        attackDefence.setPaintLabels(true);
        attackDefence.setSize(280, 50);
        attackDefence.setLocation(107, 200);
        attackDefence.setFont(font);
        attackDefence.setBackground(color);
        this.add(attackDefence);

        sensitivity.setSize(50, 20);
        sensitivity.setLocation(55, 290);
        this.add(sensitivity);
        sensitivity2.setSize(50, 20);
        sensitivity2.setLocation(55, 305);
        this.add(sensitivity2);
        smellStrength.setSize(70, 20);
        smellStrength.setLocation(387, 295);
        this.add(smellStrength);
        sensitivitySmellStrength = new JSlider(JSlider.HORIZONTAL,1, 24, MapData.getFourmilierStats(2));
        sensitivitySmellStrength.setMajorTickSpacing(2);
        sensitivitySmellStrength.setMinorTickSpacing(1);
        sensitivitySmellStrength.setPaintTicks(true);
        sensitivitySmellStrength.setPaintLabels(true);
        sensitivitySmellStrength.setSize(280, 50);
        sensitivitySmellStrength.setLocation(107, 300);
        sensitivitySmellStrength.setFont(font);
        sensitivitySmellStrength.setBackground(color);
        this.add(sensitivitySmellStrength);

        finish.setSize(100,23);
        finish.setLocation(390, 345);
        finish.setForeground(Color.white);
        finish.setBackground(new Color(Integer.parseInt("1994", 19)));
        finish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finish();
            }
        });
        this.add(finish);
    }

    public void finish(){
        System.out.println(speedEndurance.getValue());
        System.out.println(attackDefence.getValue());
        System.out.println(sensitivitySmellStrength.getValue());
        cost = cost+Math.abs(speedEnduranceBefore-speedEndurance.getValue())*10;
        cost = cost+Math.abs(attackDefenceBefore-attackDefence.getValue())*10;
        cost = cost+Math.abs(sensitivitySmellStrengthBefore-sensitivitySmellStrength.getValue())*10;
        System.out.println("cout: "+ cost);
    }
}

