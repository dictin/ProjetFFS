package ViewPkg;

import ControllerPkg.MasterController;
import ControllerPkg.VisualCaseHandler;
import ObserverPkg.Observer;

import javax.swing.*;
import java.awt.*;

public class VisualCase extends JComponent implements Observer {

    /**
     * Taille d'un côté de la case.
     */
    public static final int CASE_SIDE_PIXEL_SIZE=20;
    /**
     * Point contenant les coordonnées de la case dans la grille de jeu.
     */
    private Point caseCoord;
    /**
     * Contrôleur principal
     */
    private MasterController controller;
    /**
     * Image de l'animal présent dans la case.
     */
    private Image occupantImage = null;
    /**
     * Image de l'objet de décor présent dans la case.
     */
    private Image backgroundImage;


    /**
     * Constructeur du visuel d'une case
     * @param i coordonnée en x de la case
     * @param j coordonnée en y de la case
     * @param visualCaseOrigin point d'origine de la case
     * @param controller le contrôleur principal
     */
    public VisualCase(int i, int j, Point visualCaseOrigin, final MasterController controller){
        this.controller=controller;
        caseCoord=new Point(i, j);
        this.setSize(new Dimension(CASE_SIDE_PIXEL_SIZE, CASE_SIDE_PIXEL_SIZE));
        this.setLocation((int) (visualCaseOrigin.getX() + i * CASE_SIDE_PIXEL_SIZE), (int) (visualCaseOrigin.getY() + j * CASE_SIDE_PIXEL_SIZE));
        this.addMouseListener(new VisualCaseHandler(this.controller));
        this.controller.getMapController().addObserver(this);

        this.backgroundImage = this.controller.getMapController().getVisualWildObject(caseCoord);
    }

    /**
     * Méthode qui retourne le point avec les coordonnées de la case
     * @return le point avec les coordonnées de la case
     */
    public Point getCaseCoord(){
        return this.caseCoord;
    }

    @Override
    public void paintComponent(Graphics graphics){
        graphics.drawImage(this.backgroundImage, 0,0,this);

        if (this.occupantImage != null){
            graphics.drawImage(this.occupantImage,0,0,this);
        }else{
            graphics.drawImage(this.backgroundImage,0,0,this);
        }
        graphics.setColor(new Color(Integer.parseInt("FFFFF0", 16)));
        graphics.drawRect(0, 0, CASE_SIDE_PIXEL_SIZE-1, CASE_SIDE_PIXEL_SIZE-1);
    }

    /**
     * Cette méthode met à jour les informations de la case visuelle avec celles de son homologue du Model
     */
    @Override
    public void update() {
        this.backgroundImage = controller.getMapController().getVisualWildObject(caseCoord);
        this.occupantImage = controller.getMapController().getOccupancy(caseCoord);
        this.invalidate();
        this.repaint();
    }
}
