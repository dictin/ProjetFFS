package ControllerPkg;

import ViewPkg.MasterFrame;
import ViewPkg.MasterUI;

/**
 * Created by Xav on 24/02/14.
 */
public class Controller extends Thread{


    private int testCounter=0;
    private MasterFrame mF;
    private MasterUI mUI=null;
    private int sleepTime;
    private int time=0;

    public Controller(int FPS){
        this.sleepTime=1000/FPS;
        mF=new MasterFrame(this);
        this.start();
    }

    @Override
    public void run(){
        while (true){
            try {

                this.sleep(sleepTime);
                time++;
                mUI.actualiser();
                mUI.invalidate();
                mUI.repaint();

            } catch (InterruptedException ex) {
                System.out.println("Interrupted Exception: ");
                ex.printStackTrace();
            }
        }
    }

    public int getTime(){
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
            System.out.println("else");
            String menuName=menuButtonName.split("_")[0]+"_menu";
            mUI.popMenu(menuName);
        }
    }

    public void enterMenuTriggerZone(){
        mUI.popMenu("main_menu");
    }

    public void pointAtVisualCase(){
        mUI.closeMenus();
    }
}
