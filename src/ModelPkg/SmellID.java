package ModelPkg;

public class SmellID {

    private long iD;
    private int intensity;

    public SmellID(long iD, int intensity){
        this.iD = iD;
        this.intensity = intensity;

    }

    public long getiD() {
        return iD;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public void diminish(){
        if (this.intensity <= 10){
            this.intensity = 0;
        }else{
            this.intensity -= 10;
        }
    }
}
