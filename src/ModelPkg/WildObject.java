package ModelPkg;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class WildObject {

    private boolean traversable;
    private int type = 0;
    /*
     * 1: Rock  - Climb
     * 2: Tree  - Impassable
     * 3: Water - Swim
     * 4: Hole  - Fly
     * 5: Hive  - Impassable
     * 6: Corpse
     * 7: Food
     *
     * 0: Nothing
     *
     *
     */

    private Image[] images = new Image[8];


    public WildObject(int type, boolean traversable){
        this.initializeImages();
        this.type = type;
        this.traversable = traversable;

    }

    private void initializeImages(){
        try{
        this.images[0] = ImageIO.read(new File("IMG/empty.gif"));
        this.images[1] = ImageIO.read(new File("IMG/rock.gif"));
        this.images[2] = ImageIO.read(new File("IMG/tree.gif"));
        this.images[3] = ImageIO.read(new File("IMG/water.gif"));
        this.images[4] = ImageIO.read(new File("IMG/hole.gif"));
        this.images[5] = ImageIO.read(new File("IMG/colony.gif"));
        this.images[6] = ImageIO.read(new File("IMG/corpse.gif"));
        this.images[7] = ImageIO.read(new File("IMG/food.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isTraversable() {
        return traversable;
    }

    public int getType() {
        return type;
    }

    public Image getSprite(){
        return this.images[this.type];
    }



}
