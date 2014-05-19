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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MasterUI extends JLayeredPane implements Observer{


    private int gridEndPointX;
    private MasterController masterController;
    private PlayerDataController playerDataController;
    private GotoMenuButton quitIcon;
    private JLabel helpLabel = new JLabel("?");

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

    private JLabel backgroundImage= new JLabel();

    private JLabel labelPopulation = new JLabel("Population: 0");
    private JLabel labelFood = new JLabel("Nourriture: 300");
    private JLabel labelDeaths = new JLabel("Victimes: 0");
    private JLabel labelLevel = new JLabel("Niveau: 1");
    private JLabel labelScore = new JLabel("Score: 0");
    private JLabel labelPickUpFood = new JLabel("Food to pick up: 0");
    private JLabel playerStatsBackground = new JLabel();
    private JLabel hackEvent = new JLabel();
    private ImageIcon myIcon = new ImageIcon("IMG/hackEvent.gif");
    /**
     * Constructeur de l'interface d'utilisateur principale
     * @param masterController le contrôleur principal
     */
    public MasterUI(final MasterController masterController){
        this.masterController = masterController;
        this.playerDataController = this.masterController.getPlayerDataController();

        this.playerDataController.addObserver(this);



        mainMenu= new MainMenu(masterController);
        shopMenu=new ShopMenu(masterController);
        creationMenu = new CreationMenu(masterController);
        inventoryMenu= new InventoryMenu(masterController);
        this.setSize(MasterFrame.GAME_FRAME_SIZE);

        hackEvent.setSize(350,500);
        hackEvent.setLocation(620,10);
        hackEvent.setIcon(myIcon);
        this.add(hackEvent);
        hackEvent.setVisible(false);

        hideMenus.setIcon(new ImageIcon("IMG/shaman.jpg"));
        hideMenus.setSize(320, 557);
        hideMenus.setOpaque(true);
        hideMenus.setLocation(650,20);
        hideMenus.setVisible(false);
        this.add(hideMenus, UILayers.MENUS.getLayerIndex());
        this.setLocation(0, 0);
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
                this.add(visualCasesGrid[i][j], UILayers.MAP.getLayerIndex());
            }
        }
        quitIcon = new GotoMenuButton(masterController, "quit_button", new Dimension(25,25),2);
        this.add(quitIcon, UILayers.MENUS.getLayerIndex());
        quitIcon.setLocation(this.getWidth()-quitIcon.getWidth(), 0);

        helpLabel.setSize(20,20);
        helpLabel.setLocation(this.getWidth()-quitIcon.getWidth()-20, 0);
        helpLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Bienvenue dans la section aide. \n\n" +
                        "Le but de Fourmilier Fourmilière Simulator est de créer sa colonie de fourmilier et de survivre à travers les niveaux \n \n" +
                        "Pour ce faire, vous devez jouer stratégiquement et utiliser judicieusement les objets du magasins.\n" +
                        "Les fourmiliers vous aideront à récolter de la nourriture que vous pourrez utiliser pour créer d'autres fourmiliers\n" +
                        "ou pour acheter des améliorations et objets au magasin.\n\n" +
                        "Lorsque la quantité de nourriture nécessaire pour passer au prochain niveau est atteinte, le Chaman des fourmiliers \n" +
                        "va vous posez 3questions pour déterminer si vous méritez sa bénédiction pour le prochain niveau. Une bonne réponse \n" +
                        "augmentera votre chance d'avoir des bons événements et vis-versa pour une mauvaise réponse.\n\n" +
                        "Après les 3 questions, Vous aurez la possibilité de créer votre nouvelle race de fourmiliers.\n\n" +
                        "Bonne chance!");
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
        this.add(helpLabel);

        gridEndPointX=25+xGridSize*VisualCase.CASE_SIDE_PIXEL_SIZE;

        selectedMenu=mainMenu;
        this.add(selectedMenu, UILayers.MENUS.getLayerIndex());
        selectedMenu.setLocation(gridEndPointX + 25, 25);
        selectedMenu.setVisible(true);

        menuTriggerZone =new MenuTriggerZone(masterController);


        this.add(menuTriggerZone, UILayers.MENUS.getLayerIndex());
        menuTriggerZone.setLocation(gridEndPointX, 25);



        labelFood.setSize(92,9);
        labelFood.setForeground(Color.white);
        labelFood.setLocation(660, 590); // 10,525
        this.add(labelFood, UILayers.INFOLABELS.getLayerIndex());
        //population
        labelPopulation.setSize(87,16);
        labelPopulation.setForeground(Color.white);
        labelPopulation.setLocation(660, 600);
        this.add(labelPopulation, UILayers.INFOLABELS.getLayerIndex());
        //Victimes
        labelDeaths.setSize(77,9);
        labelDeaths.setForeground(Color.white);
        labelDeaths.setLocation(660, 617);
        this.add(labelDeaths, UILayers.INFOLABELS.getLayerIndex());
        //Level
        labelLevel.setSize(58,9);
        labelLevel.setForeground(Color.white);
        labelLevel.setLocation(660, 630);
        this.add(labelLevel, UILayers.INFOLABELS.getLayerIndex());
        //Nourriture qu'il reste à ramasser avant le prochain niveau
        labelPickUpFood.setText("Food to pick up: " + masterController.getPlayerDataController().getNumberFoodToGo());
        labelPickUpFood.setSize(120, 14);
        labelPickUpFood.setForeground(Color.white);
        labelPickUpFood.setLocation(660, 640);
        this.add(labelPickUpFood, UILayers.INFOLABELS.getLayerIndex());
        //Score
        labelScore.setSize(87,9);
        labelScore.setForeground(Color.white);
        labelScore.setLocation(660, 655);
        this.add(labelScore, UILayers.INFOLABELS.getLayerIndex());
        //Fond derrière les label
        playerStatsBackground.setOpaque(false);
        playerStatsBackground.setIcon(new ImageIcon("IMG/playerStatsBackgroundImg.jpg"));
        playerStatsBackground.setSize(127, 95);
        playerStatsBackground.setLocation(655, 580);
        this.add(playerStatsBackground);

        tvaNews.setText(MapData.getNewsList().remove(0));
        tvaNews.setSize(600, 20);
        tvaNews.setLocation(25, positionActualTvaNews);
        tvaNews.setOpaque(true);
        tvaNews.setBackground(Color.BLACK);
        tvaNews.setFont(new Font("Courier New", Font.PLAIN, 20));
        tvaNews.setForeground(Color.white);
        this.add(tvaNews, UILayers.MENUS.getLayerIndex());

        backgroundImage.setIcon(new ImageIcon("IMG/Background.jpg"));
        backgroundImage.setSize(MasterFrame.GAME_FRAME_SIZE);
        backgroundImage.setLocation(0,0);
        this.add(backgroundImage);
    }

    /**
     * Méthode pour déterminer le menu sélectionné
     * @param menuName nom du menu
     */
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
            this.add(selectedMenu, UILayers.MENUS.getLayerIndex());
            selectedMenu.setLocation(gridEndPointX + 25, 25);
            selectedMenu.setVisible(true);
            System.out.println("Creation!");
        }
        else if (menuName.equals("shop_menu")){
            selectedMenu.setVisible(false);
            selectedMenu=shopMenu;
            this.remove(selectedMenu);
            this.add(selectedMenu, UILayers.MENUS.getLayerIndex());
            selectedMenu.setLocation(gridEndPointX + 25, 25);
            selectedMenu.setVisible(true);
            System.out.println("potatost");
            System.out.println(selectedMenu.getBackground());
        }
        else if (menuName.equals("inventory_menu")){
            selectedMenu.setVisible(false);
            selectedMenu=inventoryMenu;
            this.remove(selectedMenu);
            this.add(selectedMenu, UILayers.MENUS.getLayerIndex());
            selectedMenu.setLocation(gridEndPointX + 25, 25);
            selectedMenu.setVisible(true);
            setInformationVisible(false);

        }
        selectedMenu.invalidate();
        selectedMenu.repaint();
        menuTriggerZone.setVisible(false);
    }

    /**
     * Méthode qui cache ou affiche les informations du joueur
     * @param visible si on veut cacher ou afficher les informations du joueur
     */
    public void setInformationVisible(boolean visible){
        labelFood.setVisible(visible);
        labelPopulation.setVisible(visible);
        labelDeaths.setVisible(visible);
        labelLevel.setVisible(visible);
        labelPickUpFood.setVisible(visible);
        labelScore.setVisible(visible);
        playerStatsBackground.setVisible(visible);
    }

    /**
     * Méthode qui rend visible le menuTriggerZone et ferme les menus
     */
    public void setGridToActive(){
        closeMenus();
        menuTriggerZone.setVisible(true);
    }
    /**
     * Méthode qui ferme les menus
     */
    public void closeMenus(){
        if (selectedMenu!=null){
        selectedMenu.setVisible(false);
        selectedMenu=null;
        }
    }

    /**
     * Méthode qui crée une nouvelle question et qui l'ajoute à l'interface du joueur
     */
    public void creationQuestion(){
        masterController.getPlayerDataController().setItTimeForChaman(true);
        actualQuestion = masterController.getChamanController().getQuestion();
        questionLabel = new Chaman(masterController, actualQuestion);
        questionLabel.setVisible(true);
        questionLabel.setLocation(75, 163);
        resetLayers();
        this.add(questionLabel, UILayers.QUESTIONS.getLayerIndex());

    }

    /**
     * Méthode qui crée une laboratoire de modification génétique et qui l'ajoute à l'interface du joueur
     */
    public void creationGeneticModifications(){
        laboratoryLabel = new GeneticModifications(masterController);
        laboratoryLabel.setVisible(true);
        laboratoryLabel.setLocation(75,163);
        resetLayers();
        this.add(laboratoryLabel, UILayers.QUESTIONS.getLayerIndex());


    }

    /**
     * Méthode qui actualise les image du bouton pour quitter et du menu sélectionné
     */
    public void actualizeIcons(){
        if (quitIcon.isAnimatedNow()){
            quitIcon.actualiser();
        }
        if (selectedMenu!=null){
            selectedMenu.actualiser();
        }
    }

    /**
     * Méthode qui fait bougé les nouvelles de TVA et qui vérifie si l'on doit afficher les questions du chaman ou le laboratoire
     */
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
        if(actualQuestion != null && actualQuestion.getQuestionTaTuBienRepondu() !=0 && Laboratory.isFinish() && masterController.getPlayerDataController().isItTimeForChaman()) {
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

    /**
     * Méthode qui replace les éléments de l'interface du joueur à leur bon Layer
     */
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

    /**
     * Méthode qui actualise les informations sur le joueur
     */
    private void updateNumericInfos(){
        this.labelScore.setText("Score: "+this.playerDataController.getScore());
        this.labelFood.setText("Nourriture: "+this.playerDataController.getFood());
        this.labelLevel.setText("Niveau: "+this.playerDataController.getLevel());
        this.labelPopulation.setText("Population: "+this.playerDataController.getPopulation());
        this.labelDeaths.setText("Victimes: "+this.playerDataController.getDead());
        this.labelPickUpFood.setText("Food to pick up: "+masterController.getPlayerDataController().getNumberFoodToGo());
    }


    /**
     * Méthode qui case les menus
     */
    @Override
    public void update() {
        this.updateNumericInfos();
    }

    public void disableMenus(boolean invisible){
        hideMenus.setVisible(invisible);
    }
    public void hackEvent(boolean activate){
    hackEvent.setVisible(activate);
    }
}