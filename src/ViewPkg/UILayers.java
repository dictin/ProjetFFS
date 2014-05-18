package ViewPkg;

/**
 * Created by Xav on 05/05/14.
 */
public enum UILayers {
    BACKGROUND(1),
    BACK(2),
    INFOLABELS(3),
    MAP(4),
    MENUS(5),
    QUESTIONS(6),
    EVENTS(7);

    private int layerIndex;

    private UILayers(int layerIndex){
        this.layerIndex=layerIndex;
    }
    public int getLayerIndex() {
        return layerIndex;
    }
}
