package ViewPkg;

import javax.swing.*;
import java.awt.*;

public class MasterUI extends JPanel{
    public MasterUI(){
        this.setSize(MasterFrame.GAME_FRAME_SIZE);
        this.setLocation(0,0);
        this.setLayout(null);

        int tailleXGrille=30;
        int tailleYGrille=30;
        VisualCase[][] visualCasesGrid=new VisualCase[tailleXGrille][tailleYGrille];
        for (int i=0; i<tailleXGrille; i++){
            for (int j=0; j<tailleYGrille; j++){
                visualCasesGrid[i][j]=new VisualCase(i, j);
                this.add(visualCasesGrid[i][j]);
            }
        }

        //TODO Ajouter éléments visuels d'un niveau de jeu.
    }
}