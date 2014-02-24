package ViewPkg;

import ControllerPkg.Controller;

import javax.swing.*;
import java.awt.*;

public class MasterUI extends JPanel{


    private Controller controller;
    private QuitButton qI;

    public MasterUI(final Controller controller){
        this.controller=controller;
        this.setSize(MasterFrame.GAME_FRAME_SIZE);
        this.setLocation(0,0);
        this.setLayout(null);

        int tailleXGrille=30;
        int tailleYGrille=30;
        VisualCase[][] visualCasesGrid=new VisualCase[tailleXGrille][tailleYGrille];
        Point visualCasesGridOrigin= new Point(25, (MasterFrame.GAME_FRAME_SIZE.height-tailleYGrille*VisualCase.CASE_SIDE_PIXEL_SIZE)/2);

        for (int i=0; i<tailleXGrille; i++){
            for (int j=0; j<tailleYGrille; j++){
                visualCasesGrid[i][j]=new VisualCase(i, j, visualCasesGridOrigin);
                this.add(visualCasesGrid[i][j]);
            }
        }
        qI= new QuitButton(controller);
        this.add(qI);
        qI.setLocation(0,0);

        //TODO Ajouter éléments visuels d'un niveau de jeu.
    }

    public void actualiser(){
        qI.actualiser();
        this.invalidate();
        this.repaint();
    }
}