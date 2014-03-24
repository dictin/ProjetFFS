package ViewPkg;

import ControllerPkg.MasterController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Xav on 24/02/14.
 */
public class MasterFrame extends JFrame {
    public static final Dimension GAME_FRAME_SIZE =new Dimension(1000,700);
    //TODO choisir taille fenetre.

    private MasterUI mUI;

// TODO remove or update menuBar
//    private JMenuBar menuBar = new JMenuBar();
//    private JMenu menu1 = new JMenu("Menu");
//    private JMenuItem quit = new JMenuItem("Quitter");

    public MasterFrame(final MasterController controller){
        this.setSize(GAME_FRAME_SIZE);
        mUI =new MasterUI(controller);
        this.add(mUI);
        controller.setMUI(mUI);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);



        //TODO Implement load and save game.
//        private JMenuItem load = new JMenuItem("Charger");
//        private JMenuItem save = new JMenuItem("Sauvegarder");











        this.setVisible(true);
    }
}
