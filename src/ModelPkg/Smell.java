package ModelPkg;

public class Smell {

    private int intensity;
    private long sourceID;
    private int type;

    public Smell(int intensity, long sourceID, int type){
        this.intensity = intensity;
        this.sourceID = sourceID;
        this.type = type;
    }

    public int getIntensity() {
        return intensity;
    }

    public long getSourceID() {
        return sourceID;
    }

    public int getType() {
        return type;
    }
}
