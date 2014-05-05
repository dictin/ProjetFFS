package ViewPkg;

/**
 * Created by Xav on 05/05/14.
 */
public enum UILayers {
    BACKGROUND(1),
    MAP(2),
    MENUS(3),
    QUESTIONS(4),
    EVENTS(5);

    private int layerIndex;

    private UILayers(int layerIndex){
        this.layerIndex=layerIndex;
    }
    public int getLayerIndex() {
        return layerIndex;
    }
}
