package ViewPkg.Menus;

import ControllerPkg.MasterController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Xav on 03/03/14.
 */
public class MainMenu extends ContextualMenu{

    private GotoMenuButton shopIcon;
    private GotoMenuButton inventoryIcon;
    private GotoMenuButton creationButton;
    JLabel labelPopulation = new JLabel("Population: 999");
    JLabel labelFood = new JLabel("Nourriture: 9999");
    JLabel labelDeaths = new JLabel("Victimes: 999");
    JLabel labelLevel = new JLabel("Niveau: 99");
    JLabel labelScore = new JLabel("Score: 99999");
    JLabel background = new JLabel();

    public MainMenu(final MasterController controller){
        super(controller, "main_menu");
        shopIcon = new GotoMenuButton(controller, "shop_button", new Dimension(250,100), Color.BLACK);
        inventoryIcon = new GotoMenuButton(controller, "inventory_button", new Dimension(250,100),Color.YELLOW);
        creationButton = new GotoMenuButton((controller),"creation_button", new Dimension(250,100), Color.CYAN);
        this.add(inventoryIcon);
        this.add(shopIcon);
        this.add(creationButton);
        shopIcon.setLocation(0, 25);
        inventoryIcon.setLocation(0, 160);
        creationButton.setLocation(0,400);



        //TODO lier les labels à leur valeur.
        //labelFood.setText(labelFood.split(":")[0]+" "+nourriture);
        labelFood.setSize(92,9);
        labelFood.setForeground(Color.white);
        labelFood.setLocation(10,525);
        this.add(labelFood);
        //population
        labelPopulation.setSize(87,16);
        labelPopulation.setForeground(Color.white);
        labelPopulation.setLocation(10,535);
        this.add(labelPopulation);
        //Victimes
        labelDeaths.setSize(77,9);
        labelDeaths.setForeground(Color.white);
        labelDeaths.setLocation(10,553);
        this.add(labelDeaths);
       //Level
        labelLevel.setSize(58,9);
        labelLevel.setForeground(Color.white);
        labelLevel.setLocation(10,567);
        this.add(labelLevel);
        //Score
        labelScore.setSize(87,9);
        labelScore.setForeground(Color.white);
        labelScore.setLocation(10,580);
        this.add(labelScore);

        //this.setBackground(Color.BLACK);

        background.setOpaque(true);
        background.setBackground(Color.DARK_GRAY);
        background.setSize(100,85);
        background.setLocation(7,515);
        this.add(background);
    }

    public void actualiser(){
        shopIcon.actualiser();
        this.invalidate();
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics graphics){

        super.paintComponent(graphics);
        graphics.setColor(Color.BLUE);
        graphics.fillRect(0,0,300,300);
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,350,300,350);

    }
}
