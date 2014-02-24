package ViewPkg;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Xav on 24/02/14.
 */
public class MasterFrame extends JFrame {
    public static final Dimension GAME_FRAME_SIZE =new Dimension(900,700);
    //TODO choisir taille fenetre.
    private MasterUI Mui;
    public MasterFrame(){
        this.setSize(GAME_FRAME_SIZE);
        Mui=new MasterUI();
        this.add(Mui);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);











        this.setVisible(true);
    }
}
