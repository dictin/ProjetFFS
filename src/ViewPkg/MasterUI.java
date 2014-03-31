package ViewPkg;

import ControllerPkg.ItemController;
import ControllerPkg.MasterController;
import ViewPkg.Menus.*;

import javax.swing.*;
import java.awt.*;

public class MasterUI extends JPanel{


    private int gridEndPointX;
    private MasterController controller;
    private GotoMenuButton quitIcon;

    private MenuTriggerZone menuTriggerZone;
    private ContextualMenu selectedMenu;
    private ContextualMenu mainMenu;
    private ContextualMenu shopMenu;
    private ContextualMenu inventoryMenu;

    //TODO déplacer dans le modèle
    private int food=300;

    private int getGridEndPointX;

    public MasterUI(final MasterController controller){
        this.controller=controller;

        mainMenu= new MainMenu(controller);
        shopMenu=new ShopMenu(controller);

        //TODO lier les labels à leur valeur.
        //labelFood.setText(labelFood.split(":")[0]+" "+nourriture);
        JLabel labelFood = new JLabel("Nourriture: 9999");
        labelFood.setSize(92,9);
        labelFood.setForeground(Color.white);
        labelFood.setLocation(25,35);
        this.add(labelFood);

        JLabel labelPopulation = new JLabel("Population: 999");
        labelPopulation.setSize(87,16);
        labelPopulation.setForeground(Color.white);
        labelPopulation.setLocation(25+labelFood.getWidth()+5,15);
        this.add(labelPopulation);

        JLabel labelDeaths = new JLabel("Victimes: 999");
        labelDeaths.setSize(77,9);
        labelDeaths.setForeground(Color.white);
        labelDeaths.setLocation(labelFood.getX()+labelFood.getWidth()+15,35);
        this.add(labelDeaths);

        JLabel labelLevel = new JLabel("Niveau: 99");
        labelLevel.setSize(58,9);
        labelLevel.setForeground(Color.white);
        labelLevel.setLocation(labelDeaths.getX()+labelDeaths.getWidth()+15,35);
        this.add(labelLevel);

        JLabel labelScore = new JLabel("Score: 99999");
        labelScore.setSize(87,9);
        labelScore.setForeground(Color.white);
        labelScore.setLocation(labelLevel.getX()+labelLevel.getWidth()+15,35);
        this.add(labelScore);

        inventoryMenu= new InventoryMenu(controller);
        this.setSize(MasterFrame.GAME_FRAME_SIZE);
        this.setBackground(new Color(Integer.parseInt("314159", 16)));
        this.setLocation(0,0);
        this.setLayout(null);

        int xGridSize=30;
        int tailleYGrille=30;
        VisualCase[][] visualCasesGrid=new VisualCase[xGridSize][tailleYGrille];
        Point visualCasesGridOrigin= new Point(25, (MasterFrame.GAME_FRAME_SIZE.height-tailleYGrille*VisualCase.CASE_SIDE_PIXEL_SIZE)/2);

        for (int i=0; i<xGridSize; i++){
            for (int j=0; j<tailleYGrille; j++){
                visualCasesGrid[i][j]=new VisualCase(i, j, visualCasesGridOrigin, controller);
                this.add(visualCasesGrid[i][j]);
            }
        }
        quitIcon = new GotoMenuButton(controller, "quit_button", new Dimension(25,25), Color.black);
        this.add(quitIcon);
        quitIcon.setLocation(this.getWidth()-quitIcon.getWidth(), 0);

        gridEndPointX=25+xGridSize*VisualCase.CASE_SIDE_PIXEL_SIZE;

        selectedMenu=mainMenu;
        this.add(selectedMenu);
        selectedMenu.setLocation(gridEndPointX+25, 25);
        selectedMenu.setVisible(true);

        menuTriggerZone =new MenuTriggerZone(controller);
//        gridTriggerZone= new GridTriggerZone(controller, xGridSize);

        this.add(menuTriggerZone);
        menuTriggerZone.setLocation(gridEndPointX, 25);
//TODO kill gridTriggerZone from all of existence.
//        this.add(gridTriggerZone);
//        gridTriggerZone.setLocation(25, 25);

        //TODO Ajouter éléments visuels d'un niveau de jeu.
    }

    public void setFood(int newFoodValue){
        this.food=newFoodValue;
    }

    public void popMenu(String menuName){
        if (menuName.equals("main_menu")){
            selectedMenu=mainMenu;
            selectedMenu.setVisible(true);
        }
        else if (menuName.equals("shop_menu")){
            selectedMenu.setVisible(false);
            selectedMenu=shopMenu;
            this.remove(selectedMenu);
            this.add(selectedMenu);
            selectedMenu.setLocation(gridEndPointX + 25, 25);
            selectedMenu.setVisible(true);
            System.out.println("potatost");
            System.out.println(selectedMenu.getBackground());
        }
        else if (menuName.equals("inventory_menu")){
            selectedMenu.setVisible(false);
            selectedMenu=inventoryMenu;
            this.remove(selectedMenu);
            this.add(selectedMenu);
            selectedMenu.setLocation(gridEndPointX+25, 25);
            selectedMenu.setVisible(true);
        }
        selectedMenu.invalidate();
        selectedMenu.repaint();
        menuTriggerZone.setVisible(false);
    }

    public void setGridToActive(){
        closeMenus();
        menuTriggerZone.setVisible(true);
    }

    public void closeMenus(){
        if (selectedMenu!=null){
        selectedMenu.setVisible(false);
        selectedMenu=null;
        }
    }

    public void showCaseContents(){
        //TODO link visualCases to contained items and display informations on them.
    }

    public void actualiser(){
        quitIcon.actualiser();
        if (selectedMenu!=null){
            selectedMenu.actualiser();
        }
        this.invalidate();
        this.repaint();
    }
}