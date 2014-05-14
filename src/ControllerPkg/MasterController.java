package ControllerPkg;

import ModelPkg.*;
import ModelPkg.PkgEvents.GameEventSunnyWeather;
import ModelPkg.PkgEvents.LingeringGameEvents;
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
    MapController mapController = new MapController();
    QuestionChamanController chamanController = new QuestionChamanController();
    PlayerDataController playerDataController = new PlayerDataController();

    private static int animalListIndex=0;
    private int testCounter=0;
    private MasterFrame mF;
    private MasterUI mUI=null;
    private int sleepTime;
    private static int time =0;
    private int smellDecayTime=60;
    private int eventFrequency=300;
    private static int uniqueID=0;
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
        deadAnimal.getOccupiedCase().setOccupant(null);
        MapController.getAnimalList().remove(MapController.getAnimalList().indexOf(deadAnimal));
        animalListIndex--;


    }
    public void victimes(){
        this.playerDataController.newVictime();
    }


    public static int getUniqueID() {
        uniqueID++;
        return uniqueID;
    }

    @Override
    public void run(){
        while (true){
            try {

                //TODO tester infinite loop (Si runnin arrete, break glass; it's an emergency)
                //System.out.println("Runnin'");

                this.sleep(sleepTime);
                this.time++;



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


                this.moveAnimals();//TODO fix me

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
            if (animalArrayList.get(i).isToMove()){
                toMoveAnimals.add(animalArrayList.indexOf(animalArrayList.get(i)),animalArrayList.get(i));
            }
        }

        for (int i = 0; i < toMoveAnimals.size(); i++){
            if (toMoveAnimals.size() != 0){

                Point oldPosition = animalArrayList.get(i).getPosition();

                Point newPosition;

                toMoveAnimals.get(i).activate(MasterController.getTime());

                newPosition = animalArrayList.get(i).getPosition();

                animalArrayList.remove(animalArrayList.get(i));

                MapData.getCase(oldPosition).setOccupant(null);
                MapData.getCase(newPosition).setOccupant(animalArrayList.get(i));

                animalArrayList.add(toMoveAnimals.indexOf(animalArrayList.get(i)), animalArrayList.get(i));
                MapData.setAnimalList(animalArrayList);
            }
        }
    }
}
