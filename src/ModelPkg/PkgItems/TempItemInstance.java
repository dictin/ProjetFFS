package ModelPkg.PkgItems;

//
public class TempItemInstance {

    /**
     * Effet de l'item instancié
     */
    private ItemEffect itemEffect;
    /**
     * Durée de l'item instancié
     */
    private int duration;

    public TempItemInstance(BoostEffect effect){
        this.itemEffect = effect;
        this.duration = effect.getDuration();
    }

    /**
     * Active l'effet de l'item
     */
    public void activate(){
        this.itemEffect.activate();
    }

    /**
     * Réduit la durée restante de l'effet d'un tour
     */
    public void turn(){
        duration--;
    }

    public int getDuration() {
        return duration;
    }
}
