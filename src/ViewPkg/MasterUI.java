package ViewPkg;

import ControllerPkg.MasterController;
import ControllerPkg.PlayerDataController;
import ModelPkg.Laboratory;
import ModelPkg.MapData;
import ModelPkg.QuestionChaman;
import ObserverPkg.Observer;
import ViewPkg.Menus.*;

import javax.swing.*;
import java.awt.*;

public class MasterUI extends JLayeredPane implements Observer{


    private int gridEndPointX;
    private MasterController masterController;
    private PlayerDataController playerDataController;
    private GotoMenuButton quitIcon;

    private VisualCase[][] visualCasesGrid;

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
    private boolean alreadyInMovement = false;
    private JLabel laboratoryLabel = new JLabel();
    private JLabel hideMenus = new JLabel();

    JLabel labelPopulation = new JLabel("Population: 0");
    JLabel labelFood = new JLabel("Nourriture: 300");
    JLabel labelDeaths = new JLabel("Victimes: 0");
    JLabel labelLevel = new JLabel("Niveau: 1");
    JLabel labelScore = new JLabel("Score: 0");
    JLabel labelPickUpFood = new JLabel("Food to pick up: 0");
    JLabel background = new JLabel();


    public MasterUI(final MasterController masterController){
        this.masterController = masterController;
        this.playerDataController = this.masterController.getPlayerDataController();

        this.playerDataController.addObserver(this);

        mainMenu= new MainMenu(masterController);
        shopMenu=new ShopMenu(masterController);
        creationMenu = new CreationMenu(masterController);
        inventoryMenu= new InventoryMenu(masterController);
        this.setSize(MasterFrame.GAME_FRAME_SIZE);
        this.setBackground(new Color(Integer.parseInt("314159", 16)));

        hideMenus.setSize(320, 557);
        hideMenus.setOpaque(true);
        hideMenus.setBackground(new Color(Integer.parseInt("314159", 16)));
        hideMenus.setLocation(650,20);
        hideMenus.setVisible(false);
        this.add(hideMenus, UILayers.MENUS);
        this.setLocation(0,0);
        this.setLayout(null);

        // Pour enlever les questions du chaman et/ou GeneticModification, mettre en commentaire ci-dessous
      //  this.creationQuestion();
       // this.creationGeneticModifications();

        int xGridSize=30;
        int tailleYGrille=30;
        this.visualCasesGrid=new VisualCase[xGridSize][tailleYGrille];
        Point visualCasesGridOrigin= new Point(25, (MasterFrame.GAME_FRAME_SIZE.height-tailleYGrille*VisualCase.CASE_SIDE_PIXEL_SIZE)/2);

        for (int i=0; i<xGridSize; i++){
            for (int j=0; j<tailleYGrille; j++){
                visualCasesGrid[i][j]=new VisualCase(i, j, visualCasesGridOrigin, masterController);
                this.add(visualCasesGrid[i][j], UILayers.MAP);
            }
        }
        quitIcon = new GotoMenuButton(masterController, "quit_button", new Dimension(25,25), new Color(Integer.parseInt("314159",16)));
        this.add(quitIcon, UILayers.MENUS);
        quitIcon.setLocation(this.getWidth()-quitIcon.getWidth(), 0);

        gridEndPointX=25+xGridSize*VisualCase.CASE_SIDE_PIXEL_SIZE;

        selectedMenu=mainMenu;
        this.add(selectedMenu, UILayers.MENUS);
        selectedMenu.setLocation(gridEndPointX+25, 25);
        selectedMenu.setVisible(true);

        menuTriggerZone =new MenuTriggerZone(masterController);
//        gridTriggerZone= new GridTriggerZone(masterController, xGridSize);


        this.add(menuTriggerZone, UILayers.MENUS);
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
        this.add(labelFood, UILayers.BACKGROUND);
        //population
        labelPopulation.setSize(87,16);
        labelPopulation.setForeground(Color.white);
        labelPopulation.setLocation(660,600);
        this.add(labelPopulation, UILayers.BACKGROUND);
        //Victimes
        labelDeaths.setSize(77,9);
        labelDeaths.setForeground(Color.white);
        labelDeaths.setLocation(660,617);
        this.add(labelDeaths, UILayers.BACKGROUND);
        //Level
        labelLevel.setSize(58,9);
        labelLevel.setForeground(Color.white);
        labelLevel.setLocation(660,630);
        this.add(labelLevel, UILayers.BACKGROUND);
        //Nourriture qu'il reste à ramasser avant le prochain niveau
        labelPickUpFood.setText("Food to pick up: "+masterController.getPlayerDataController().getNumberFoodToGo());
        labelPickUpFood.setSize(120,14);
        labelPickUpFood.setForeground(Color.white);
        labelPickUpFood.setLocation(660,640);
        this.add(labelPickUpFood, UILayers.BACKGROUND);
        //Score
        labelScore.setSize(87,9);
        labelScore.setForeground(Color.white);
        labelScore.setLocation(660,655);
        this.add(labelScore, UILayers.BACKGROUND);
        //Fond derrière les label
        background.setOpaque(true);
        background.setBackground(new Color(Integer.parseInt("324159", 15)));
        background.setSize(127,95);
        background.setLocation(655,580);
        this.add(background, UILayers.BACKGROUND);

        tvaNews.setText(MapData.getNewsList().remove(0));
        tvaNews.setSize(600, 20);
        tvaNews.setLocation(25, positionActualTvaNews);
        tvaNews.setOpaque(true);
        tvaNews.setBackground(Color.BLACK);
        tvaNews.setFont(new Font("Courier New", Font.PLAIN, 20));
        tvaNews.setForeground(Color.white);
        this.add(tvaNews, UILayers.MENUS);


    }

    public void popMenu(String menuName){
        setInformationVisible(true);
        if (menuName.equals("main_menu")){
            selectedMenu=mainMenu;
            selectedMenu.setVisible(true);
        }
        else if(menuName.equals("creation_menu")){
            selectedMenu.setVisible(false);
            selectedMenu=creationMenu;
            this.remove(selectedMenu);
            this.add(selectedMenu, UILayers.MENUS);
            selectedMenu.setLocation(gridEndPointX + 25, 25);
            selectedMenu.setVisible(true);
            System.out.println("Creation!");
        }
        else if (menuName.equals("shop_menu")){
            selectedMenu.setVisible(false);
            selectedMenu=shopMenu;
            this.remove(selectedMenu);
            this.add(selectedMenu, UILayers.MENUS);
            selectedMenu.setLocation(gridEndPointX + 25, 25);
            selectedMenu.setVisible(true);
            System.out.println("potatost");
            System.out.println(selectedMenu.getBackground());
        }
        else if (menuName.equals("inventory_menu")){
            selectedMenu.setVisible(false);
            selectedMenu=inventoryMenu;
            this.remove(selectedMenu);
            this.add(selectedMenu, UILayers.MENUS);
            selectedMenu.setLocation(gridEndPointX + 25, 25);
            selectedMenu.setVisible(true);
            setInformationVisible(false);

        }
        selectedMenu.invalidate();
        selectedMenu.repaint();
        menuTriggerZone.setVisible(false);
    }
    public void setInformationVisible(boolean visible){
        labelFood.setVisible(visible);
        labelPopulation.setVisible(visible);
        labelDeaths.setVisible(visible);
        labelLevel.setVisible(visible);
        labelPickUpFood.setVisible(visible);
        labelScore.setVisible(visible);
        background.setVisible(visible);
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
        masterController.getPlayerDataController().setItTimeForChaman(true);
        actualQuestion = masterController.getChamanController().getQuestion();
        questionLabel = new Chaman(masterController, actualQuestion);
        questionLabel.setVisible(true);
        questionLabel.setLocation(75, 163);
        resetLayers();
        this.add(questionLabel, UILayers.QUESTIONS);

    }
    
    public void creationGeneticModifications(){
        System.out.println("Time to me creepy");
        laboratoryLabel = new GeneticModifications(masterController);
        laboratoryLabel.setVisible(true);
        laboratoryLabel.setLocation(75,163);
        resetLayers();
        this.add(laboratoryLabel, UILayers.QUESTIONS);


    }

    public void actualizeIcons(){
        quitIcon.actualiser();
        if (selectedMenu!=null){
            selectedMenu.actualiser();
        }
    }

    public void actualiser(){

        if(MapData.getNewsList().size()>numberOfNews && !alreadyInMovement){
            mouvement = 1;
            alreadyInMovement = true;
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
                mouvement = 0;
                if(MapData.getNewsList().size()>=1){
                    mouvement =1;
                    tvaNews.setText(MapData.getNewsList().remove(0));

                }
                else if(MapData.getNewsList().size() ==0){
                    alreadyInMovement = false;
                    tvaNews.setText("Nouvelle de dernière heure!");
                }

            }
        }
        numberOfNews = MapData.getNewsList().size();
        if(actualQuestion != null && actualQuestion.getQuestionTaTuBienRepondu()==1 && Laboratory.isFinish() && masterController.getPlayerDataController().isItTimeForChaman()) {
            masterController.getPlayerDataController().setQuestionNumber(masterController.getPlayerDataController().getQuestionNumber()+1);
            if(masterController.getPlayerDataController().getQuestionNumber()%4 ==0){
                masterController.getPlayerDataController().setItTimeForChaman(false);
                System.out.println("Question over!");
                questionLabel.setVisible(false);
                this.remove(questionLabel);
                Laboratory.setFinish(false);
                creationGeneticModifications();
            }
            else{
            actualQuestion = masterController.getChamanController().getQuestion();
            questionLabel.setVisible(false);
            this.remove(questionLabel);
            creationQuestion();
            this.invalidate();
            this.repaint();
            this.resetLayers();
            }
        }

        if(Laboratory.isFinish() &&masterController.getPlayerDataController().getQuestionNumber()%4 == 0 ){
         laboratoryLabel.setVisible(false);
            if(masterController.getPlayerDataController().isTheLevelFinish()){
                creationMenu.actualiser();
            }

        }
    }

    private void resetLayers(){
        for (int i=0; i<this.visualCasesGrid.length; i++){
            for (int j=0; j<visualCasesGrid[i].length; j++){
               this.setLayer(this.visualCasesGrid[i][j], UILayers.MAP.getLayerIndex());
            }
        }
        if(Laboratory.isFinish()){
            this.setLayer(this.questionLabel, UILayers.QUESTIONS.getLayerIndex());
        }
        else{
        this.setLayer(this.laboratoryLabel,  UILayers.QUESTIONS.getLayerIndex());
        }
    }

    private void updateNumericInfos(){
        this.labelScore.setText("Score: "+this.playerDataController.getScore());
        this.labelFood.setText("Nourriture: "+this.playerDataController.getFood());
        this.labelLevel.setText("Niveau: "+this.playerDataController.getLevel());
        this.labelPopulation.setText("Population: "+this.playerDataController.getPopulation());
        this.labelDeaths.setText("Victimes: "+this.playerDataController.getDead());
        this.labelPickUpFood.setText("Food to pick up: "+masterController.getPlayerDataController().getNumberFoodToGo());
    }

    @Override
    public void update() {
        this.updateNumericInfos();
    }

    public void disableMenus(boolean invisible){
        hideMenus.setVisible(invisible);
    }
}