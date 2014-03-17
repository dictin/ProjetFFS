package ViewPkg.Menus;

import ControllerPkg.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Xav on 24/02/14.
 */
public class GotoMenuButton extends JComponent {

    private Controller controller;
    private boolean isAnimatedNow=false;
    private int anmtnStartTime=0;
    /**
     * # of frames the object will keep the same sprite.
     */
    private int anmtnWaitTime=10;
    private int numberOfSprites =2;
    private int currentSpriteIndex=0;
    private String menuButtonName ="quit_button";

    public GotoMenuButton(final Controller controller, String menuButtonName, Dimension buttonMaxSize){
        this.controller=controller;
        this.menuButtonName= menuButtonName;
        this.setSize(buttonMaxSize);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                GotoMenuButton gotoMenuButton =(GotoMenuButton)e.getSource();
                gotoMenuButton.setAnimated(true);
                gotoMenuButton.setAnmtnStartTime(controller.getTime());
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                GotoMenuButton gotoMenuButton =(GotoMenuButton)e.getSource();
                gotoMenuButton.setAnimated(false);
                gotoMenuButton.currentSpriteIndex=0;
            }
            @Override
            public void mouseClicked(MouseEvent e) {

                super.mouseClicked(e);
                GotoMenuButton gotoMenuButton =(GotoMenuButton)e.getSource();
                controller.menuButtonClick(gotoMenuButton.getMenuButtonName());
            }
        });
    }

    public void setAnimated(boolean isAnimatedNow){
        this.isAnimatedNow=isAnimatedNow;
    }

    public void setAnmtnStartTime(int anmtnStartTime) {
        this.anmtnStartTime = anmtnStartTime;
    }

    public int getAnmtnStartTime() {
        return this.anmtnStartTime;
    }

    public void actualiser(){
        if (isAnimatedNow&&(((controller.getTime()-this.anmtnStartTime)%this.anmtnWaitTime)==0)){
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
        graphics.drawImage(Toolkit.getDefaultToolkit().getImage(menuButtonName + currentSpriteIndex + ".gif"), 0, 0, this);
    }
}