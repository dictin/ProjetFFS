package ViewPkg;

import ControllerPkg.MasterController;

import javax.swing.*;
import java.awt.*;


public class MasterFrame extends JFrame {
    public static final Dimension GAME_FRAME_SIZE =new Dimension(1000,700);

    /**
     * Panneau principal et unique du jeu
     */
    private MasterUI mUI;

    /**
     * Constructeur de la fenêtre principale
     * @param controller le contrôleur principal
     */
    public MasterFrame(final MasterController controller){
        this.setSize(GAME_FRAME_SIZE);
        mUI =new MasterUI(controller);
        this.add(mUI);
        controller.setMUI(mUI);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);

        this.setVisible(true);
    }
}
