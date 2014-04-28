package ControllerPkg;

import ModelPkg.*;
import ViewPkg.MasterFrame;
import ViewPkg.MasterUI;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Xav on 24/02/14.
 */
public class MasterController extends Thread{

    ItemController itemController = new ItemController();
    ShopInfoController shopInfoController = new ShopInfoController();
    MapController mapController = new MapController();
    QuestionChamanController chamanController = new QuestionChamanController();

    private static int animalListIndex=0;
    private int testCounter=0;
    private MasterFrame mF;
    private MasterUI mUI=null;
    private int sleepTime;
    private static int time =0;
    private int smellDecayTime=60;
    private static int uniqueID=0;


    public MasterController(int FPS){
        Name.initialize();
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

    public static int getUniqueID() {
        uniqueID++;
        return uniqueID;
    }

    @Override
    public void run(){
        while (true){
            try {

                this.sleep(sleepTime);
                this.time++;

                //TODO remove this test
                /*
                if (time==200){
                    MapData.getCase(new Point(0,0)).getSmellSourceArrayList().add(new SmellSource(0,100,1, Smell.SmellType2.animal));
                }
                if (time==250){
                MapData.getCase(new Point(5,0)).getSmellSourceArrayList().add(new SmellSource(0,75,1, Smell.SmellType2.animal));
                }
                */


                if (time!=0&&time%smellDecayTime==0){
                MapData.updateSmells();
                }

                ArrayList<Animal> animalList=getMapController().getAnimalList();
                if (!animalList.isEmpty()){
                    for (animalListIndex=0; animalListIndex<animalList.size();animalListIndex++){
                        Animal animal=animalList.get(animalListIndex);
                        animal.activate(getTime());
                    }
                }

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

        //TODO remplacer chaîne de ifs par un switch
        if (menuButtonName=="quit_button"){
            System.exit(0);
        }
        else {
            String menuName=menuButtonName.split("_")[0]+"_menu";
            System.out.println(menuName);
            mUI.popMenu(menuName);
        }
    }

    public void enterMenuTriggerZone(){
        System.out.println("EMTZ");
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
}
