package ViewPkg;

import ControllerPkg.MasterController;
import ModelPkg.QuestionChaman;
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
    private ContextualMenu creationMenu;

    private int numberQuestion =1;
    private JLabel questionLabel = new JLabel();
    private QuestionChaman actualQuestion;

    //TODO déplacer dans le modèle
    private int food=300;

    private int getGridEndPointX;

    public MasterUI(final MasterController controller){
        this.controller=controller;

        mainMenu= new MainMenu(controller);
        shopMenu=new ShopMenu(controller);
        creationMenu = new CreationMenu(controller);


        inventoryMenu= new InventoryMenu(controller);
        this.setSize(MasterFrame.GAME_FRAME_SIZE);
        this.setBackground(new Color(Integer.parseInt("314159", 16)));

        this.setLocation(0,0);
        this.setLayout(null);

        // Pour enlever les questions du chaman, mettre en commentaire ci-dessous
        creationQuestion();

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
        quitIcon = new GotoMenuButton(controller, "quit_button", new Dimension(25,25), new Color(Integer.parseInt("314159",16)));
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
        else if(menuName.equals("creation_menu")){
            selectedMenu.setVisible(false);
            selectedMenu=creationMenu;
            this.remove(selectedMenu);
            this.add(selectedMenu);
            selectedMenu.setLocation(gridEndPointX + 25, 25);
            selectedMenu.setVisible(true);
            System.out.println("Creation!");
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
            ((InventoryMenu)selectedMenu).setI(0);
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

    public void creationQuestion(){
        System.out.println("New question");
        actualQuestion = controller.getChamanController().getQuestion();
        System.out.println(actualQuestion.getFinish());
        System.out.println(actualQuestion.getQuestion());
        questionLabel = new Chaman(controller, actualQuestion);
        this.add(questionLabel);
        questionLabel.setVisible(true);
        questionLabel.setLocation(75, 163);
    }

    public void actualiser(){
        quitIcon.actualiser();
        if (selectedMenu!=null){      //Boucle infinie  !?
            selectedMenu.actualiser();
        }
        if(actualQuestion.getGoodAnswer()){
            System.out.println("Trouvé!");
            questionLabel.setVisible(false);
            this.remove(questionLabel);
            creationQuestion();
            questionLabel.invalidate();
            questionLabel.repaint();
        }
    }
}