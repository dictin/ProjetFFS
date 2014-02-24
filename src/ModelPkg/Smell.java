package ModelPkg;

import java.util.ArrayList;

public class Smell {

    public static final int MAX_VALUE = 100;
    private int intensity;
    private ArrayList<Long> contributorsID = new ArrayList<Long>();
    private ArrayList<Integer> individualIntensities = new ArrayList<Integer>();
    private int type;

    public Smell(int intensity, long sourceID, int type){
        this.intensity = intensity;
        this.sourceID = sourceID;
        this.type = type;
    }

    public void diminish(){
        if (this.intensity <= 10){
            this.intensity = 0;
        }else{
            this.intensity -= 10;
        }
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
