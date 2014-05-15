package ViewPkg;


import ControllerPkg.MasterController;
import ControllerPkg.SliderListener;
import ModelPkg.Laboratory;
import ModelPkg.MapData;
import ModelPkg.PlayerData;

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
    private JButton reset = new JButton("Réinisialiser");
    private JLabel cost = new JLabel("Coût: "+ Laboratory.getCost());
    private int modificationCost = 0;

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
        speedEndurance.addChangeListener(new SliderListener());




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
        attackDefence.setLocation(107, 190);
        attackDefence.setFont(font);
        attackDefence.setBackground(color);
        attackDefence.addChangeListener(new SliderListener());
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
        sensitivitySmellStrength.setLocation(107, 280);
        sensitivitySmellStrength.setFont(font);
        sensitivitySmellStrength.setBackground(color);
        sensitivitySmellStrength.addChangeListener(new SliderListener());
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

    reset.setSize(120,23);
    reset.setLocation(5, 345);
    reset.setForeground(Color.white);
    reset.setBackground(new Color(Integer.parseInt("1994", 20)));
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("time to reset!");
            }
        });
    this.add(reset);
        cost.setSize(100,23);
        cost.setLocation(230,345);
        this.add(cost);
        if(Laboratory.isMoving()){
            System.out.println("Actual: " + speedEndurance.getValue());
            Laboratory.setMoving(false);
        }
    }

    public void finish(){
        System.out.println(Laboratory.isFinish());
        System.out.println(speedEndurance.getValue());
        System.out.println(attackDefence.getValue());
        System.out.println(sensitivitySmellStrength.getValue());
        modificationCost = modificationCost+Math.abs(Laboratory.getSpeedEnduranceBefore()-speedEndurance.getValue())*10;
        modificationCost = modificationCost+Math.abs(Laboratory.getAttackDefenceBefore()-attackDefence.getValue())*10;
        modificationCost = modificationCost+Math.abs(Laboratory.getSensitivitySmellStrengthBefore()-sensitivitySmellStrength.getValue())*10;
        System.out.println("Cout: "+modificationCost);
        if(controller.getPlayerDataController().getFood() -modificationCost >= 0 ){
            Laboratory.setFinish(true);
            Laboratory.setCost(modificationCost);
            controller.getPlayerDataController().spendFood(modificationCost);
            Laboratory.setSpeedEnduranceBefore(speedEndurance.getValue());
            Laboratory.setAttackDefenceBefore(attackDefence.getValue());
            Laboratory.setSensitivitySmellStrengthBefore(sensitivitySmellStrength.getValue());
        }
        else{
            JOptionPane.showMessageDialog(null, "Nourriture insuffisante");
            reset();
        }
    }
    public void reset(){
        Laboratory.setReset(true);
        Laboratory.setCost(0);
        cost.setText("Coût: " + Laboratory.getCost());
        speedEndurance.setValue(Laboratory.getSpeedEnduranceBefore());
        attackDefence.setValue(Laboratory.getAttackDefenceBefore());
        sensitivitySmellStrength.setValue(Laboratory.getSensitivitySmellStrengthBefore());
    }
}

