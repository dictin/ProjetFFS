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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
    private JLabel cost = new JLabel("Coût futur de cette nouvelle race: "+ Laboratory.getCost());
    private int modificationCost = 0;
    private JLabel blocSlider = new JLabel();

    private JButton speed = new JButton("Vitesse");
    private JButton endurance = new JButton("Endurance");
    private JButton attack = new JButton("Attaque");
    private JButton defence = new JButton("Défence");
    private JButton sensitivity = new JButton("Odorat");
    private JButton smellStrength = new JButton("Odeur");

    public GeneticModifications(final MasterController controller){
        this.setOpaque(true);
        this.setBackground(color);
        this.setSize(500,375);
        this.controller=controller;

        blocSlider.setSize(270, 230);
        blocSlider.setLocation(112, 100);
        this.add(blocSlider);
        blocSlider.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

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

        speed.setSize(90, 30);
        speed.setLocation(20, 95);
        this.add(speed);
        speed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSlider("statsSpeed");
            }
        });
        endurance.setSize(100, 30);
        endurance.setLocation(387, 95);
        this.add(endurance);
        endurance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSlider("statsEndurance");
            }
        });
        speedEndurance = new JSlider(JSlider.HORIZONTAL,1, 24, MapData.getFourmilierActualRaceStats(0));
        speedEndurance.setMajorTickSpacing(2);
        speedEndurance.setMinorTickSpacing(1);
        speedEndurance.setPaintTicks(true);
        speedEndurance.setPaintLabels(true);
        speedEndurance.setSize(280, 50);
        speedEndurance.setLocation(107, 100);
        speedEndurance.setFont(font);
        speedEndurance.setBackground(color);
        this.add(speedEndurance);

        attack.setSize(90, 30);
        attack.setLocation(20, 185);
        this.add(attack);
        attack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSlider("statsAttack");
            }
        });
        defence.setSize(90, 30);
        defence.setLocation(387, 185);
        this.add(defence);
        defence.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSlider("statsDefence");
            }
        });
        attackDefence = new JSlider(JSlider.HORIZONTAL,1, 24, MapData.getFourmilierActualRaceStats(1));
        attackDefence.setMajorTickSpacing(2);
        attackDefence.setMinorTickSpacing(1);
        attackDefence.setPaintTicks(true);
        attackDefence.setPaintLabels(true);
        attackDefence.setSize(280, 50);
        attackDefence.setLocation(107, 190);
        attackDefence.setFont(font);
        attackDefence.setBackground(color);
        this.add(attackDefence);

        sensitivity.setSize(90, 30);
        sensitivity.setLocation(20, 275);
        this.add(sensitivity);
        sensitivity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSlider("statsSensitivity");
            }
        });
        smellStrength.setSize(90, 30);
        smellStrength.setLocation(387, 275);
        this.add(smellStrength);
        smellStrength.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSlider("statsSmell");
            }
        });
        sensitivitySmellStrength = new JSlider(JSlider.HORIZONTAL,1, 24, MapData.getFourmilierActualRaceStats(2));
        sensitivitySmellStrength.setMajorTickSpacing(2);
        sensitivitySmellStrength.setMinorTickSpacing(1);
        sensitivitySmellStrength.setPaintTicks(true);
        sensitivitySmellStrength.setPaintLabels(true);
        sensitivitySmellStrength.setSize(280, 50);
        sensitivitySmellStrength.setLocation(107, 280);
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

    reset.setSize(120,23);
    reset.setLocation(5, 345);
    reset.setForeground(Color.white);
    reset.setBackground(new Color(Integer.parseInt("1994", 20)));
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("time to reset!");
                reset();
            }
        });
    this.add(reset);

        cost.setSize(230,23);
        cost.setLocation(150,345);
        this.add(cost);
        if(Laboratory.isMoving()){
            System.out.println("Actual: " + speedEndurance.getValue());
            Laboratory.setMoving(false);
        }
    }

    public void finish(){
        System.out.println("Cout: "+modificationCost);
        Laboratory.setFinish(true);
        Laboratory.setCost(modificationCost);
        if(controller.getPlayerDataController().getLevel() ==2){
            Laboratory.setSpeedEnduranceBefore(speedEndurance.getValue());
            Laboratory.setAttackDefenceBefore(attackDefence.getValue());
            Laboratory.setSensitivitySmellStrengthBefore(sensitivitySmellStrength.getValue());
            int[] newStats= new int[]{speedEndurance.getValue(),attackDefence.getValue(),sensitivitySmellStrength.getValue()};
            MapData.setFourmilierFixRaceStats1(newStats);
        }

    }
    public void reset(){
        Laboratory.setReset(true);
        Laboratory.setCost(0);
        cost.setText("Coût futur de cette nouvelle race: " + Laboratory.getCost());
        speedEndurance.setValue(Laboratory.getSpeedEnduranceBefore());
        attackDefence.setValue(Laboratory.getAttackDefenceBefore());
        sensitivitySmellStrength.setValue(Laboratory.getSensitivitySmellStrengthBefore());
    }
    public void updateSlider(String statsName){
        switch (statsName){
            case "statsSpeed":
                speedEndurance.setValue(speedEndurance.getValue()-1);
                break;
            case "statsEndurance":
                speedEndurance.setValue(speedEndurance.getValue()+1);
                break;
            case "statsAttack":
                attackDefence.setValue(attackDefence.getValue()-1);
                break;
            case "statsDefence":
                attackDefence.setValue(attackDefence.getValue()+1);
                break;
            case "statsSensitivity":
                sensitivitySmellStrength.setValue(sensitivitySmellStrength.getValue()-1);
                break;
            case "statsSmell":
                sensitivitySmellStrength.setValue(sensitivitySmellStrength.getValue()+1);
                break;
        }
        modificationCost = modificationCost+Math.abs(Laboratory.getSpeedEnduranceBefore()-speedEndurance.getValue())*10;
        modificationCost = modificationCost+Math.abs(Laboratory.getAttackDefenceBefore()-attackDefence.getValue())*10;
        modificationCost = modificationCost+Math.abs(Laboratory.getSensitivitySmellStrengthBefore()-sensitivitySmellStrength.getValue())*10;
        cost.setText("Coût futur de cette nouvelle race: " + modificationCost);

    }
}

