package ViewPkg;


import ControllerPkg.MasterController;
import ModelPkg.Laboratory;
import ModelPkg.MapData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GeneticModifications extends JLabel {
    /**
     * Contrôleur principal.
     */
    private MasterController controller;
    /**
     * JSlider permettant de régler la vitesse et l'endurance des fourmiliers.
     */
    private JSlider speedEndurance;
    /**
     * JSlider permettant de régler la défense et l'attaque.
     */
    private JSlider attackDefence;
    /**
     * JSlider permettant de régler l'odorat et l'odeur.
     */
    private JSlider sensitivitySmellStrength;
    /**
     * Label contenant le texte de bienvenue.
     */
    private JLabel laboratoryTitle = new JLabel("Bienvenue au laboratoire de modification génétique!");
    /**
     * Explication du fonctionnement du laboratoire, partie 1.
     */
    private JLabel laboratoryExplanation1 = new JLabel("Chaque déplacement du curseur coûte 10 nourritures.");
    /**
     * Explication du fonctionnement du laboratoire, partie 2.
     */
    private JLabel laboratoryExplanation2 = new JLabel("Plus le curseur se rapporche d'une compétence, plus cette compétence sera forte.");
    /**
     * Première police utilisée
     */
    private Font font = new Font("Serif", Font.ITALIC, 12);
    /**
     * Seconde police utilisée
     */
    private Font font2 = new Font("Couriel", Font.BOLD, 18);
    /**
     * Couleur de fond du laboratoire.
     */
    private Color color = new Color(Integer.parseInt("510968", 19));
    /**
     * Bouton confirmant les choix du joueur.
     */
    private JButton finish = new JButton("Fini!");
    /**
     * Bouton réinitialisant les JSliders.
     */
    private JButton reset = new JButton("Réinisialiser");
    /**
     * Cout du fourmilier
     */
    private int modificationCost = 100;
    /**
     * JLabel affichant le coût de l'espèce générée.
     */
    private JLabel cost = new JLabel("Coût futur de cette nouvelle race: "+ modificationCost);
    /**
     *
     */
    private JLabel blocSlider = new JLabel();
    /**
     * Bouton augmentant la vitesse au détriment de l'endurance.
     */
    private JButton speed = new JButton("Vitesse");
    /**
     * Bouton augmentant l'endurance au détriment de la vitesse.
     */
    private JButton endurance = new JButton("Endurance");
    /**
     * Bouton augmentant l'attaque au détriment de la défense.
     */
    private JButton attack = new JButton("Attaque");
    /**
     * Bouton augmentant l'attaque au détriment de la défense.
     */
    private JButton defence = new JButton("Défense");
    /**
     * Bouton augmentant l'odorat au détriment de l'intensité de l'odeur du fourmilier.
     */
    private JButton sensitivity = new JButton("Odorat");
    /**
     * Bouton augmentant l'intensité de l'odeur du fourmilier au détriment de l'odorat.
     */
    private JButton smellStrength = new JButton("Odeur");
    /**
     * JLabel priant le joueur de patienter pendant la mise à jour du nouveau terrain.
     */
    private JLabel pleaseWait = new JLabel("Veuillez patienter pendant la création d'un nouveau terrain.");

    /**
     * Constructeur des modifications génétiques
     * @param controller le contrôleur principal
     */
    public GeneticModifications(final MasterController controller){
        this.setOpaque(true);
        this.setBackground(color);
        this.setSize(500,375);
        this.controller=controller;

        pleaseWait.setSize(350,100);
        pleaseWait.setLocation(70, 150);
        pleaseWait.setOpaque(true);
        pleaseWait.setBackground(color.CYAN);
        pleaseWait.setVisible(false);
        this.add(pleaseWait); 
        blocSlider.setSize(270, 230);
        blocSlider.setLocation(112, 100);
        this.add(blocSlider);
        blocSlider.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("this is bloc slider");
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
        laboratoryExplanation1.setSize(400, 20);
        laboratoryExplanation2.setSize(475, 20);
        laboratoryTitle.setLocation(5, 5);
        laboratoryExplanation1.setLocation(5, 30);
        laboratoryExplanation2.setLocation(5, 50);
        laboratoryTitle.setFont(font2);
        this.add(laboratoryTitle);
        this.add(laboratoryExplanation1);
        this.add(laboratoryExplanation2);

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
                reset();
            }
        });
    this.add(reset);

        cost.setSize(230,23);
        cost.setLocation(150,345);
        this.add(cost);

    }

    /**
     * Méthode qui affecte les valeurs du slider aux statistiques de la nouvelle race créé
     */
    public void finish(){
        Laboratory.setFinish(true);
        Laboratory.setCost(modificationCost);
        Laboratory.setSpeedEnduranceBefore(speedEndurance.getValue());
        Laboratory.setAttackDefenceBefore(attackDefence.getValue());
        Laboratory.setSensitivitySmellStrengthBefore(sensitivitySmellStrength.getValue());
        int[] newStats= new int[]{speedEndurance.getValue(),attackDefence.getValue(),sensitivitySmellStrength.getValue()};
        pleaseWait.setVisible(true);
        switch(controller.getPlayerDataController().getLevel()){
            case 2:
                MapData.setFourmilierFixRaceStats1(newStats);
                MapData.setCostFourmilier(1,modificationCost);
                break;
            case 3:
                MapData.setFourmilierFixRaceStats2(newStats);
                MapData.setCostFourmilier(2,modificationCost);
                break;
            case 4:
                MapData.setFourmilierFixRaxceStats3(newStats);
                MapData.setCostFourmilier(3,modificationCost);
                break;
        }
    }

    /**
     * Méthode qui replace les slider à leurs positions initiales
     */
    public void reset(){
        Laboratory.setCost(0);
        cost.setText("Coût futur de cette nouvelle race: " +  100);
        speedEndurance.setValue(Laboratory.getSpeedEnduranceBefore());
        attackDefence.setValue(Laboratory.getAttackDefenceBefore());
        sensitivitySmellStrength.setValue(Laboratory.getSensitivitySmellStrengthBefore());
        modificationCost = 100;
    }

    /**
     * Méthode qui déplace un slider tout dépendamment de quel bouton a été clické
     * @param statsName nom du bouton clické
     */
    public void updateSlider(String statsName){
        modificationCost = 100;
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

