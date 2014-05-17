package ControllerPkg;

import ModelPkg.*;
import ModelPkg.PkgEvents.GameEventSunnyWeather;
import ModelPkg.PkgEvents.LingeringGameEvents;
import ModelPkg.WildObjects.FoodSource;
import ModelPkg.WildObjects.WildObject;
import ViewPkg.MasterFrame;
import ViewPkg.MasterUI;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Xav on 24/02/14.
 */
public class MasterController extends Thread{

    ItemController itemController = new ItemController(this);
    ShopInfoController shopInfoController = new ShopInfoController();
    static MapController mapController = new MapController();
    QuestionChamanController chamanController = new QuestionChamanController();
    PlayerDataController playerDataController = new PlayerDataController();

    private MasterFrame mF;
    private MasterUI mUI=null;
    private int sleepTime;
    private static int time =0;
    private int smellDecayTime=60;
    private int eventFrequency=300;
    private EventController eventRoller;


    public MasterController(int FPS){
        Name.initialize();
        eventRoller=new EventController(this);
        QuestionData.initialize();
        this.sleepTime=1000/FPS;

        mF=new MasterFrame(this);
        this.start();

    }

    public static void disposeAnimal(Animal deadAnimal){
        mapController.removeSmellSourceOf(deadAnimal);
        Point targetPosition=deadAnimal.getPosition();
        Case targetCase = MapData.getCase(targetPosition);
        targetCase.setOccupant(null);
        MapController.getAnimalList().remove(deadAnimal);
        deadAnimal=null;
        MapController.getAnimalList().remove(deadAnimal);
        targetCase.setOccupant(null);
    }
    public void victims(){
        this.playerDataController.newVictime();
    }

    @Override
    public void run(){
        while (true){
            try {

                //TODO tester infinite loop (Si runnin arrete, break glass; it's an emergency)
                //System.out.println("Runnin'");

                this.sleep(sleepTime);
                this.time++;


                //TODO remove this
                if (time>=5){
                    MapData.getCase(new Point(18, 16)).setWildObject(new WildObject(WildObject.ROCK_ID, false));
                    MapData.getCase(new Point(19, 16)).setWildObject(new FoodSource(300));
                }

                if (time!=0&&time%smellDecayTime==0){
                MapData.updateSmells();
                }

                if (time!=0&&time%eventFrequency==0){
                    if (getPlayerDataController().getCurrentEvent() == null){
                        getPlayerDataController().setCurrentEvent(new GameEventSunnyWeather());
                    }
                    else{
                    int duration=getPlayerDataController().getCurrentEvent().getDuration();
                    if (duration==0){
                        getPlayerDataController().setCurrentEvent(eventRoller.whatIsTheWeather());
                        getPlayerDataController().getCurrentEvent().firstTimeActivation();
                    }
                    else{
                        getPlayerDataController().getCurrentEvent().decreaseDuration();
                        ((LingeringGameEvents)getPlayerDataController().getCurrentEvent()).lingeringActivation();
                    }
                    }
                }

                mUI.actualiser();
                mUI.invalidate();
                mUI.repaint();


                this.moveAnimals();//TODO fix me

                mUI.actualizeIcons();
                mUI.actualiser();
                mUI.invalidate();
                mUI.repaint();

            } catch (InterruptedException ex) {
                System.out.println("Interrupted Exception: ");
                ex.printStackTrace();
            }
        }
    }

//NOTE DE CHLOÉ: Cette méthode n'est plus nécessaire parce qu'elle existe déjà dans la classe Time. Est-ce que c'est ok de la supprimée?

    public static int getTime(){
        return time;
    }

    public void openMainMenu(){
        mUI.popMenu("main_menu");
    }

    public void setMUI(MasterUI mUI){
        this.mUI=mUI;
    }

    public void closeAllMenus(){
        mUI.closeMenus();
    }

    public void menuButtonClick(String menuButtonName){

        if (menuButtonName=="quit_button"){
            System.exit(0);
        }
        else {
            String menuName=menuButtonName.split("_")[0]+"_menu";
            mUI.popMenu(menuName);
        }
    }

    public void enterMenuTriggerZone(){
        mUI.popMenu("main_menu");
    }

    public void pointAtVisualCase(){
        mUI.setGridToActive();
        mUI.showCaseContents();
    }

    public void clickVisualCase(Point caseCoord){

    }

    public void setGridToActive(){
        this.mUI.setGridToActive();
    }

    public ItemController getItemController() {
        return itemController;
    }

    public ShopInfoController getShopInfoController() {
        return shopInfoController;
    }

    public MapController getMapController() {
        return mapController;
    }

    public QuestionChamanController getChamanController() { return chamanController;}

    public PlayerDataController getPlayerDataController() {
        return playerDataController;
    }

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

    public void disposeWildObject(Point target) {
        long defunctSmellID=MapData.getCase(target).getWildObject().getSmellSource().getID();

        System.out.println("Test ids");
        System.out.println("Target");
        System.out.println(defunctSmellID);

        System.out.println("Ids:");
        for (int i=0; i<MapData.MAP_SIZE; i++){
            for (int j=0; j<MapData.MAP_SIZE;j++){
                Case selectedCase=MapData.getCase(new Point(i,j));
                for (int k=0; k<selectedCase.getSortedSmellArrayList().size();k++){
                    if (selectedCase.getSortedSmellArrayList().get(k).getID()==defunctSmellID){
                        System.out.println("Befor:"+selectedCase.getSortedSmellArrayList().get(k).getIntensity());
                        selectedCase.set0ToSmellIntensityIndex(k);
                        System.out.println("After:"+selectedCase.getSortedSmellArrayList().get(k).getIntensity());
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
}
