package ViewPkg;

import ControllerPkg.MasterController;
import ModelPkg.MapData;
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
    private JLabel tvaNews = new JLabel();
    private int positionMinTvaNews = 660;
    private int positionMaxTvaNews = 701;
    private int positionActualTvaNews = 701;
    private int numberOfNews = 0;
    private int mouvement = 0;
    private int duration = 0;

    JLabel labelPopulation = new JLabel("Population: 999");
    JLabel labelFood = new JLabel("Nourriture: 9999");
    JLabel labelDeaths = new JLabel("Victimes: 999");
    JLabel labelLevel = new JLabel("Niveau: 99");
    JLabel labelScore = new JLabel("Score: 99999");
    JLabel background = new JLabel();

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
        System.out.println("Checking if empty");

        //TODO lier les labels à leur valeur.
        //labelFood.setText(labelFood.split(":")[0]+" "+nourriture);

        labelFood.setSize(92,9);
        labelFood.setForeground(Color.white);
        labelFood.setLocation(660,590); // 10,525
        this.add(labelFood);
        //population
        labelPopulation.setSize(87,16);
        labelPopulation.setForeground(Color.white);
        labelPopulation.setLocation(660,600);
        this.add(labelPopulation);
        //Victimes
        labelDeaths.setSize(77,9);
        labelDeaths.setForeground(Color.white);
        labelDeaths.setLocation(660,617);
        this.add(labelDeaths);
        //Level
        labelLevel.setSize(58,9);
        labelLevel.setForeground(Color.white);
        labelLevel.setLocation(660,630);
        this.add(labelLevel);
        //Score
        labelScore.setSize(87,9);
        labelScore.setForeground(Color.white);
        labelScore.setLocation(660,645);
        this.add(labelScore);
        //Fond derrière les label
        background.setOpaque(true);
        background.setBackground(new Color(Integer.parseInt("324159", 15)));
        background.setSize(110,85);
        background.setLocation(655,580);
        this.add(background);


        tvaNews.setText(MapData.getNewsList().remove(0));
        tvaNews.setSize(495, 20);
        tvaNews.setLocation(25, positionActualTvaNews);
        tvaNews.setOpaque(true);
        tvaNews.setBackground(Color.BLACK);
        tvaNews.setFont(new Font("Courier New", Font.PLAIN, 20));
        tvaNews.setForeground(Color.white);
        this.add(tvaNews);


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
        questionLabel = new Chaman(controller, actualQuestion);
        questionLabel.setVisible(true);
        questionLabel.setLocation(75, 163);
        this.add(questionLabel);
    }

    public void actualiser(){
        quitIcon.actualiser();
        if (selectedMenu!=null){
            selectedMenu.actualiser();
        }

        if(MapData.getNewsList().size()>numberOfNews){
            mouvement = 1;
        }
        if(mouvement ==1){
            tvaNews.setLocation(25,positionActualTvaNews--);
            if(positionActualTvaNews == positionMinTvaNews){
                mouvement = 0;
                duration = MasterController.getTime();
            }
        }
        if(MasterController.getTime() -duration == 150){
        mouvement = -1;
        }
        if(mouvement ==-1){
            tvaNews.setLocation(25,positionActualTvaNews++);

            if(positionActualTvaNews == positionMaxTvaNews){
                System.out.println("Time to stop min atteint");
                mouvement = 0;

                System.out.println("longeur : "+MapData.getNewsList().size());
                if(MapData.getNewsList().size()>=1){
                    tvaNews.setText(MapData.getNewsList().get(0));
                    System.out.println("moving up");
                    mouvement =1;
                    MapData.getNewsList().remove(0);

                }
            }
        }
        /*if(MasterController.getTime() % 120 == 0 && !MapData.getNewsList().isEmpty()){
            System.out.println("Temps de changer les news");
            tvaNews.setText(MapData.getNewsList().remove(0));
        }*/
        if(actualQuestion.getQuestionTaTuBienRepondu()==1){
            System.out.println("Trouvé!");
            actualQuestion = controller.getChamanController().getQuestion();
            questionLabel.setVisible(false);
            questionLabel.setText("Stéphane est un dieu");
            creationQuestion();
            questionLabel.invalidate();
            questionLabel.repaint();
            }
        numberOfNews = MapData.getNewsList().size();
    }
}