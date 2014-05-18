package ViewPkg.Menus;

import ControllerPkg.MasterController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Xav on 24/02/14.
 */
public class GotoMenuButton extends JComponent {

    private MasterController controller;
    //TODO remove bgColor and put realz sprites
    private Color defBGColor;
    private boolean animatedNow =false;
    private int anmtnStartTime=0;
    /**
     * # of frames the object will keep the same sprite.
     */
    private int anmtnWaitTime=5;
    private int numberOfSprites;
    private int currentSpriteIndex=0;
    private String menuButtonName ="quit_button";
    private MasterController masterController;

    public GotoMenuButton(final MasterController controller, String menuButtonName, Dimension buttonMaxSize, int numberOfSprites){
        this.numberOfSprites=numberOfSprites;
        this.controller=controller;
        this.defBGColor=defBGColor;
        this.menuButtonName= menuButtonName;
        this.setSize(buttonMaxSize);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                GotoMenuButton gotoMenuButton = (GotoMenuButton) e.getSource();
                gotoMenuButton.setAnimated(true);
                gotoMenuButton.setAnmtnStartTime(controller.getTime());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                GotoMenuButton gotoMenuButton = (GotoMenuButton) e.getSource();
                gotoMenuButton.setAnimated(false);
                gotoMenuButton.currentSpriteIndex = 0;
            }

            @Override
            public void mouseClicked(MouseEvent e) {

                super.mouseClicked(e);
                GotoMenuButton gotoMenuButton = (GotoMenuButton) e.getSource();
                controller.menuButtonClick(gotoMenuButton.getMenuButtonName());
            }
        });
    }

    public void setAnimated(boolean isAnimatedNow){
        this.animatedNow =isAnimatedNow;
    }

    public void setAnmtnStartTime(int anmtnStartTime) {
        this.anmtnStartTime = anmtnStartTime;
    }

    public int getAnmtnStartTime() {
        return this.anmtnStartTime;
    }

    public void actualiser(){
        if ((((controller.getTime()-this.anmtnStartTime)%this.anmtnWaitTime)==0)){
            switchActiveSprite();
        }
    }

    public void switchActiveSprite(){
        if (currentSpriteIndex+1>= numberOfSprites){
            currentSpriteIndex=0;
        }
        else{
            currentSpriteIndex+=1;
        }
    }

    public String getMenuButtonName(){
        return this.menuButtonName;
    }

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.drawImage(Toolkit.getDefaultToolkit().getImage("IMG/"+menuButtonName + currentSpriteIndex + ".jpg"), 0, 0, this);
    }

    public MasterController getMasterController() {
        return masterController;
    }

    public boolean isAnimatedNow() {
        return animatedNow;
    }
}