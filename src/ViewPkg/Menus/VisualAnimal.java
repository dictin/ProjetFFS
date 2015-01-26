package ViewPkg.Menus;

import javax.swing.*;
import java.awt.*;

public class VisualAnimal extends JComponent {

    /**
     * Image de l'animal.
     */
    private Image sprite;

    /**
     * Constructeur du visuel des animaux
     * @param animalSprite image de l'animal
     */
    public VisualAnimal(Image animalSprite){
        sprite=animalSprite;
    }

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.drawImage(sprite, 0,0,this);
    }
}
