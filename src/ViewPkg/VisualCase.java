package ViewPkg;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Xav on 24/02/14.
 */
public class VisualCase extends JComponent {

    public static final int CASE_SIDE_PIXEL_SIZE=20;
    private Point caseCoord;
    public VisualCase(int i, int j, Point visualCaseOrigin){
        caseCoord=new Point(i, j);
        this.setSize(new Dimension(CASE_SIDE_PIXEL_SIZE, CASE_SIDE_PIXEL_SIZE));
        this.setLocation((int) (visualCaseOrigin.getX()+i*CASE_SIDE_PIXEL_SIZE), (int) (visualCaseOrigin.getY()+j*CASE_SIDE_PIXEL_SIZE));
    }
    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.drawRect(0, 0, CASE_SIDE_PIXEL_SIZE, CASE_SIDE_PIXEL_SIZE);
    }
}
