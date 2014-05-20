package ControllerPkg;

import ModelPkg.*;
import ModelPkg.PkgEvents.GameEventSunnyWeather;
import ModelPkg.PkgEvents.LingeringGameEvents;
import ModelPkg.PkgEvents.LingeringHackTroll;
import ModelPkg.WildObjects.WildObject;
import ViewPkg.MasterFrame;
import ViewPkg.MasterUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class MasterController extends Thread{

    private ItemController itemController = new ItemController(this);
    private ShopInfoController shopInfoController = new ShopInfoController();
    private static MapController mapController = new MapController();
    private QuestionChamanController chamanController = new QuestionChamanController();
    private PlayerDataController playerDataController = new PlayerDataController();

    private MasterFrame mF;
    private static MasterUI mUI=null;
    private int sleepTime;
    private static int time =0;
    private int smellDecayTime=60;
    private int eventFrequency=300;
    private EventController eventRoller;

    /**
     * Constructeur du controller principal
     * @param FPS vitesse du jeux
     */
    public MasterController(int FPS){
        Name.initialize();
        eventRoller=new EventController(this);
        QuestionData.initialize();
        this.sleepTime=1000/FPS;

        mF=new MasterFrame(this);
        this.start();

    }

    /**
     * Méthode pour supprimer un animal mort
     * @param deadAnimal l'animal mort
     */
    public static void disposeAnimal(Animal deadAnimal){
        mapController.removeSmellSourceOf(deadAnimal);
        Point targetPosition=deadAnimal.getPosition();
        Case targetCase = MapData.getCase(targetPosition);
        targetCase.setOccupant(null);
        MapController.getAnimalList().remove(deadAnimal);
        deadAnimal=null;


    }

    /**
     * Méthode pour diminuer de 1 la population et augmenter de 1 la quantitié de victimes
     */
    public void victims(){
        this.playerDataController.newVictime();
    }


    /**
     *Thread principal qui fait fonctionner le jeux
     */
    @Override
    public void run(){
        while (true){
            try {
                ArrayList<Animal> animalArrayList = MapData.getAnimalList();
                ArrayList<Animal> deadAnimalArrayList = new ArrayList<>();
                this.sleep(sleepTime);
                this.time++;
                int i = 0;



               // deadAnimal();

                if(this.getPlayerDataController().getNumberFoodToGo() <= 0){
                //if(time == 300){
                    JOptionPane.showMessageDialog(null, "Vous avez ramassé toute la nourriture nécessaire pour passer au prochain niveau.\n " +
                            "Le Chaman va maintenant vous posez 3 questions.");
                    this.getPlayerDataController().setTheLevelFinish(true);
                    mUI.disableMenus(true);
                    mUI.creationQuestion();
                    mUI.actualiser();
                    this.getPlayerDataController().setLevel(this.playerDataController.getLevel() + 1);

                }
                if(this.getPlayerDataController().getQuestionNumber()%4 ==0 && Laboratory.isFinish() && this.getPlayerDataController().isTheLevelFinish()){
                  removeAllFourmilier();
                    MapData.addNewsList("Niveau "+ this.getPlayerDataController().getLevel());
                    MapData.changeLevel();
                    mUI.update();
                    this.getPlayerDataController().setTheLevelFinish(false);
                    mUI.disableMenus(false);

                }


                if (time!=0&&time%smellDecayTime==0){
                MapData.updateSmells();
                }

                if (time!=0&&time%eventFrequency==0){
                    if (getPlayerDataController().getCurrentEvent() == null){
                        getPlayerDataController().setCurrentEvent(new GameEventSunnyWeather());
                    }
                    else{
                        if (getPlayerDataController().getCurrentEvent() instanceof LingeringGameEvents){
                            ((LingeringGameEvents)getPlayerDataController().getCurrentEvent()).lingeringActivation();
                        }
                        int duration=getPlayerDataController().getCurrentEvent().getDuration();
                        if (duration==0){
                            getPlayerDataController().setCurrentEvent(eventRoller.whatIsTheWeather());
                            getPlayerDataController().getCurrentEvent().firstTimeActivation();
                        }
                        else{
                            getPlayerDataController().getCurrentEvent().decreaseDuration();
                        }
                    }
                }


                mUI.actualiser();
                mUI.invalidate();
                mUI.repaint();

                this.moveAnimals();

                mUI.actualizeIcons();
                mUI.actualiser();
                mUI.invalidate();
                mUI.repaint();

                this.playerDataController.activateInstancesForTurn();
            } catch (InterruptedException ex) {
                System.out.println("Interrupted Exception: ");
                ex.printStackTrace();
            }
        }
    }

    /**
     * Méthode pour avoir le temps
     * @return le temps
     */
    public static int getTime(){
        return time;
    }

    /**
     *
     */
    public void openMainMenu(){
        mUI.popMenu("main_menu");
    }

    /**
     * Méthode pour modifier l'interface de l'utilisateur
     * @param mUI nouvelle interface
     */
    public void setMUI(MasterUI mUI){
        this.mUI=mUI;
    }


    /**
     *
     */
    public void closeAllMenus(){
        mUI.closeMenus();
    }

    /**
     * Méthode pour vérifier quel bouton du menu a été clické
     * @param menuButtonName nom du bouton clické
     */
    public void menuButtonClick(String menuButtonName){

        if (menuButtonName=="quit_button"){
            System.exit(0);
        }
        else {
            String menuName=menuButtonName.split("_")[0]+"_menu";
            mUI.popMenu(menuName);
        }
    }

    /**
     * Méthode pour déclencher le menu principal
     */
    public void enterMenuTriggerZone(){
        mUI.popMenu("main_menu");
    }

    /**
     * Méthode pour activé la grille
     */
    public void setGridToActive(){
        this.mUI.setGridToActive();
    }

    /**
     * Méthode pour avoir le controller des objets
     * @return le controller des objets
     */
    public ItemController getItemController() {
        return itemController;
    }

    /**
     * Méthode pour avoir les information sur le magasin
     * @return le controller sur les information du magasin
     */
    public ShopInfoController getShopInfoController() {
        return shopInfoController;
    }

    /**
     * Méthode pour avoir le contrôleur de la carte
     * @return le  contrôleur de la carte
     */
    public MapController getMapController() {
        return mapController;
    }

    /**
     * Méthode pour avoir le contrôleur des questions du Chaman
     * @return le  contrôleur des questions du Chaman
     */
    public QuestionChamanController getChamanController() { return chamanController;}

    /**
     * Méthode pour avoir le  contrôleur des données du joueur
     * @return le  contrôleur des données du joueur
     */
    public PlayerDataController getPlayerDataController() {
        return playerDataController;
    }

    /**
     * Méthode qui vérifie quels animaux doivent bougés et les ajoutent à une liste. Cette liste est ensuite parcouru
     * en donnant une nouvelle position aux fourmiliers.
     */
    public void moveAnimals(){
        ArrayList<Animal> animalArrayList = MapData.getAnimalList();
        ArrayList<Animal> toMoveAnimals = new ArrayList<>();
        for(int i = 0; i < animalArrayList.size(); i++){
            if (animalArrayList.get(i).isToMove(MasterController.getTime())){
                toMoveAnimals.add(animalArrayList.get(i));
            }
        }

        while (!toMoveAnimals.isEmpty()){

            Point oldPosition = toMoveAnimals.get(0).getPosition();
            Point newPosition;

            toMoveAnimals.get(0).activate(MasterController.getTime());

            if (!toMoveAnimals.get(0).isDead()){
                newPosition = toMoveAnimals.get(0).getPosition();

                MapData.getCase(oldPosition).setOccupant(null);
                MapData.getCase(newPosition).setOccupant(toMoveAnimals.get(0));

            }else {
                MapData.getCase(toMoveAnimals.get(0).getPosition()).setOccupant(null);
            }
            toMoveAnimals.remove(0);
        }
    }

    /**
     * Méthode pour supprimer un objet sauvage
     * @param target position de l'objet à détruire
     */
    public void disposeWildObject(Point target) {
        long defunctSmellID=MapData.getCase(target).getWildObject().getSmellSource().getID();


        for (int i=0; i<MapData.MAP_SIZE; i++){
            for (int j=0; j<MapData.MAP_SIZE;j++){
                Case selectedCase=MapData.getCase(new Point(i,j));
                for (int k=0; k<selectedCase.getSortedSmellArrayList().size();k++){
                    if (selectedCase.getSortedSmellArrayList().get(k).getID()==defunctSmellID){
                        selectedCase.set0ToSmellIntensityIndex(k);
                    }
                }
                for (int k=0; k<selectedCase.getSortedSmellSourceArrayList().size();k++){
                    if (selectedCase.getSortedSmellSourceArrayList().get(k).getID()==defunctSmellID){
                        selectedCase.set0ToSmellSourceIntensityIndex(k);
                    }
                }
            }
        }

        MapData.getCase(target).setWildObject(new WildObject(WildObject.EMPTY_ID, true));
    }

    /**
     * Méthode pour supprimer tous les fourmiliers de la carte (utilisé lors de la fin d'un niveau)
     */
    public void removeAllFourmilier(){
        ArrayList<Animal> animalArrayList = MapData.getAnimalList();
        while(animalArrayList.size() !=0){
            disposeAnimal(animalArrayList.remove(0));
            //this.getPlayerDataController().newVictime();

        }
    }
    public void removeOneFourmilier( ArrayList<Animal> animalArrayList){
        while(animalArrayList.size() !=0){
            MapData.addNewsList(animalArrayList.get(0).getName() + " est malheureusement décédé!!");
            disposeAnimal(animalArrayList.remove(0));

        }
        }


    /**
     *
     */
        public static void checkIfDeadFourmilier(){
        ArrayList<Animal> animalArrayList = MapData.getAnimalList();
        ArrayList<Animal> animalArrayListTemporaire = new ArrayList<>();
        while(animalArrayList.size() !=0){
            if(animalArrayList.get(0).getHealth() >0){
                animalArrayListTemporaire.add(animalArrayList.remove(0));
            }
            else{
                disposeAnimal(animalArrayList.remove(0));
            }
        }
        while(animalArrayListTemporaire.size() != 0){
            animalArrayList.add(animalArrayListTemporaire.remove(0));
        }
    }
    public void deadAnimal(){
    ArrayList<Animal> animalArrayList = MapData.getAnimalList();
    ArrayList<Integer> deadAnimalIndex = new ArrayList<>();
    for(int i = 0; i< animalArrayList.size(); i++){
            if (animalArrayList.get(i).getHealth() <= 0){
                deadAnimalIndex.add(i);
            }
        }
        for(int y = 0; y < deadAnimalIndex.size(); y++){
            int animalIndex = deadAnimalIndex.get(y);
            animalArrayList.remove(animalIndex);
        }
    while(!deadAnimalIndex.isEmpty()){
         deadAnimalIndex.remove(0);
        }
    }
    public void activateHackView(boolean active){
        mUI.hackEvent(active);
    }
}


