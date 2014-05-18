package ViewPkg;

/**
 * Created by Xav on 05/05/14.
 */
public enum UILayers {
    BACKGROUND(1),
    BACK(2),
    MAP(3),
    MENUS(new Integer(4)),
    QUESTIONS(5),
    EVENTS(6);

    private int layerIndex;

    private UILayers(int layerIndex){
        this.layerIndex=layerIndex;
    }
    public int getLayerIndex() {
        return layerIndex;
    }
}
