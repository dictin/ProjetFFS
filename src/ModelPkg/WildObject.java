package ModelPkg;

public class WildObject {

    private boolean traversable;
    private int type = 0;
    /*
     * 1: Rock  - Climb
     * 2: Tree  - Impassable
     * 3: Water - Swim
     * 4: Hole  - Fly
     * 5: Hive  - Impassable
     *
     * 0: Nothing
     *
     * -1: Corpse
     * -2: Food
     *
     */


    public WildObject(int type, boolean traversable){
        this.type = type;
        this.traversable = traversable;
    }

    public boolean isTraversable() {
        return traversable;
    }

    public int getType() {
        return type;
    }

}
