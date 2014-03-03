package ViewPkg;

import ControllerPkg.Controller;

import javax.swing.*;
import java.awt.*;

public class MasterUI extends JPanel{


    private Controller controller;
    private GotoMenuButton quitIcon;
    private GotoMenuButton shopIcon;
    private ContextualMenu shopMenu;

    public MasterUI(final Controller controller){
        this.controller=controller;

        shopMenu=new ContextualMenu(controller, "shop_menu");

        this.setSize(MasterFrame.GAME_FRAME_SIZE);
        this.setLocation(0,0);
        this.setLayout(null);

        int xGridSize=30;
        int tailleYGrille=30;
        VisualCase[][] visualCasesGrid=new VisualCase[xGridSize][tailleYGrille];
        Point visualCasesGridOrigin= new Point(25, (MasterFrame.GAME_FRAME_SIZE.height-tailleYGrille*VisualCase.CASE_SIDE_PIXEL_SIZE)/2);

        for (int i=0; i<xGridSize; i++){
            for (int j=0; j<tailleYGrille; j++){
                visualCasesGrid[i][j]=new VisualCase(i, j, visualCasesGridOrigin);
                this.add(visualCasesGrid[i][j]);
            }
        }
        quitIcon = new GotoMenuButton(controller, "quit_button", new Dimension(25,25));
        this.add(quitIcon);
        quitIcon.setLocation(0, 0);

        int visualGridXEnd=25+xGridSize*VisualCase.CASE_SIDE_PIXEL_SIZE;

        shopIcon = new GotoMenuButton(controller, "shop_button", new Dimension(250,100));
        this.add(shopIcon);
        shopIcon.setLocation(visualGridXEnd, 25);
        //TODO Ajouter éléments visuels d'un niveau de jeu.
    }

    public void popMenu(String menuName){

    }

    public void actualiser(){
        quitIcon.actualiser();
        shopIcon.actualiser();
        this.invalidate();
        this.repaint();
    }
}