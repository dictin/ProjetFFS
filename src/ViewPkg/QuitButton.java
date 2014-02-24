package ViewPkg;

import ControllerPkg.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Xav on 24/02/14.
 */
public class QuitButton extends JComponent {

    private Controller controller;
    private boolean isAnimatedNow=false;
    private int anmtnStartTime=0;
    /**
     * # of frames the object will keep the same sprite.
     */
    private int anmtnWaitTime=10;
    private int numberOfSprites =2;
    private int currentSpriteIndex=0;
    private String name="quit_button";

    public QuitButton(final Controller controller){
        this.controller=controller;
        this.setSize(new Dimension(25,25));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                QuitButton quitButton =(QuitButton)e.getSource();
                quitButton.setAnimated(true);
                quitButton.setAnmtnStartTime(controller.getTime());
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                QuitButton quitButton =(QuitButton)e.getSource();
                quitButton.setAnimated(false);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                controller.quitGame();
            }
        });
        this.setVisible(true);
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

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.drawImage(Toolkit.getDefaultToolkit().getImage(name + currentSpriteIndex + ".gif"), 0, 0, this);
    }
}