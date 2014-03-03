package ModelPkg.PkgItems;

//
public class TempItemInstance {

    private ItemEffect itemEffect;
    private int duration;

    public TempItemInstance(BoostEffect effect){
        this.itemEffect = effect;
        this.duration = effect.getDuration();
    }

    public void activate(){
        this.itemEffect.activate();
    }

    public void turn(){
        duration--;
    }

    public int getDuration() {
        return duration;
    }
}
