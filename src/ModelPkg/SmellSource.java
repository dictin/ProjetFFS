package ModelPkg;

import java.awt.*;

/**
 * Created by Xav on 27/04/14.
 */
public class SmellSource extends Smell{
    private String animalID;
        private Point coords;
        private int intensity;
        private int team;

    public SmellSource(int animalID, Point coords, int intensity, int team, Smell.SmellType2 type) {
        super(animalID, coords, intensity, team, type);
    }

    public void fade() {
        this.intensity-=10;
    }

    public enum SmellType2{
            food, animal, foe, item;
        }
}

