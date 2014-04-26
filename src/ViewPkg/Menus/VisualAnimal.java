package ViewPkg.Menus;

import javax.swing.*;
import java.awt.*;

public class VisualAnimal extends JComponent {

    private Image sprite;

    public VisualAnimal(Image animalSprite){
        sprite=animalSprite;
    }

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.drawImage(sprite, 0,0,this);
    }
}
