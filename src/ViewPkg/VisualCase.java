package ViewPkg;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Xav on 24/02/14.
 */
public class VisualCase extends JComponent {

    public static final int CASE_SIDE_PIXEL_SIZE=20;
    private Point caseCoord;
    public VisualCase(int i, int j){
        caseCoord=new Point(i, j);
        this.setSize(new Dimension(CASE_SIDE_PIXEL_SIZE, CASE_SIDE_PIXEL_SIZE));
        System.out.println(i*CASE_SIDE_PIXEL_SIZE+";"+j*CASE_SIDE_PIXEL_SIZE);
        this.setLocation(i*CASE_SIDE_PIXEL_SIZE, j*CASE_SIDE_PIXEL_SIZE);
    }
    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.fillRect(0, 0, CASE_SIDE_PIXEL_SIZE, CASE_SIDE_PIXEL_SIZE);
    }
}
