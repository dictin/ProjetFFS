package ViewPkg;

import ControllerPkg.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Xav on 24/02/14.
 */
public class VisualCase extends JComponent {

    public static final int CASE_SIDE_PIXEL_SIZE=20;
    private Point caseCoord;
    private Controller controller;
    public VisualCase(int i, int j, Point visualCaseOrigin, final Controller controller){
        this.controller=controller;
        caseCoord=new Point(i, j);
        this.setSize(new Dimension(CASE_SIDE_PIXEL_SIZE, CASE_SIDE_PIXEL_SIZE));
        this.setLocation((int) (visualCaseOrigin.getX()+i*CASE_SIDE_PIXEL_SIZE), (int) (visualCaseOrigin.getY()+j*CASE_SIDE_PIXEL_SIZE));
        this.addMouseListener(new MouseAdapter() {
            @Override
        public void mouseEntered(MouseEvent e){
                super.mouseEntered(e);
                controller.pointAtVisualCase();
            }
        });
    }
    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.drawRect(0, 0, CASE_SIDE_PIXEL_SIZE, CASE_SIDE_PIXEL_SIZE);
    }
}
