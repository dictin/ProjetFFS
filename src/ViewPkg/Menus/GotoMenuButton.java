package ViewPkg.Menus;

import ControllerPkg.MasterController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GotoMenuButton extends JComponent {

    /**
     * Contrôleur principal.
     */
    private MasterController controller;
    /**
     * Si le bouton est présentement animé.
     */
    private boolean animatedNow =false;
    /**
     * Contrôleur temps ou l'image a changé pour la dernière fois.
     */
    private int anmtnStartTime=0;
    /**
     * Nombres de tours de thread où l'image ne change pas.
     */
    private int anmtnWaitTime=5;
    /**
     * Nombres d'image dans une boucle d'animation.
     */
    private int numberOfSprites;
    /**
     * Numéro de l'image à afficher.
     */
    private int currentSpriteIndex=0;
    /**
     * Nom du bouton.
     */
    private String menuButtonName ="quit_button";

    /**
     * Constructeur du GotoMenuButton
     * @param controller le contrôleur principal
     * @param menuButtonName nom du button du menu
     * @param buttonMaxSize dimension maximale du bouton
     * @param numberOfSprites quantité d'image (de sprites)
     */
    public GotoMenuButton(final MasterController controller, String menuButtonName, Dimension buttonMaxSize, int numberOfSprites){
        this.numberOfSprites=numberOfSprites;
        this.controller=controller;
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

    /**
     * Méthode qui modifie si l'animation est active
     * @param isAnimatedNow  si l'animation est active
     */
    public void setAnimated(boolean isAnimatedNow){
        this.animatedNow =isAnimatedNow;
    }

    /**
     * Méthode qui détermine le temps du début de l'animation
     * @param anmtnStartTime temps du début de l'animation
     */
    public void setAnmtnStartTime(int anmtnStartTime) {
        this.anmtnStartTime = anmtnStartTime;
    }

    /**
     *
     * @return
     */
    public int getAnmtnStartTime() {
        return this.anmtnStartTime;
    }

    /**
     * Méthode qui actualise les sprites
     */
    public void actualiser(){
        if ((((controller.getTime()-this.anmtnStartTime)%this.anmtnWaitTime)==0)){
            switchActiveSprite();
        }
    }

    /**
     * Méthode qui change le sprite
     */
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

    /**
     * Méthode qui retourne le  contrôleur principal
     * @return le contrôleur principal
     */
    public MasterController getMasterController() {
        return controller;
    }

    /**
     * Méthode qui retourne si le button est animé en ce moment
     * @return si le bouton est animé en ce moment
     */
    public boolean isAnimatedNow() {
        return animatedNow;
    }
}