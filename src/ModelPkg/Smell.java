package ModelPkg;

import java.util.ArrayList;
import java.util.Iterator;

public class Smell {

    public static final int MAX_VALUE = 100;
    private int intensity;
    private ArrayList<SmellID> contributors = new ArrayList<SmellID>();
    private int type;

    public Smell(int intensity, long sourceID, int type){
        this.contributors.add(new SmellID(sourceID,intensity));
        this.type = type;
    }

    public void diminish(){
        Iterator<SmellID> iterator = this.contributors.iterator();
        while (iterator.hasNext()){
            iterator.next().diminish();
        }
    }

    public void increase(SmellID smellID){
        Iterator<SmellID> iterator = this.contributors.iterator();
        if (this.oldOrigin(smellID.getiD())){
            while(iterator.hasNext()){
                SmellID instSmell = iterator.next();
                if (instSmell.getiD()== smellID.getiD()){
                    instSmell.setIntensity(smellID.getIntensity());

                }
            }

        }else{
            this.contributors.add(smellID);
        }
    }

    public void clearOldSmell(){
        Iterator<SmellID> iterator = this.contributors.iterator();
        while(iterator.hasNext()){
            SmellID instSmell = iterator.next();
            if (instSmell.getIntensity() == 0){
                this.contributors.remove(instSmell);   //TODO  UNSTABLE: Check for concurrent exception
            }
        }
    }

    public boolean oldOrigin(long iD){
        boolean returnValue = false;
        Iterator<SmellID> iterator = this.contributors.iterator();

        while(iterator.hasNext()){
            if (iterator.next().getiD() == iD){
                returnValue = true;
            }
        }

        return returnValue;

    }

    public int totalSmell(){
        int total = 0;
        Iterator<SmellID> iterator = this.contributors.iterator();

        while(iterator.hasNext()){
            total+=iterator.next().getIntensity();
        }

        if (total > 100){
            total = 100;
        }

        return total;

    }



    public int getIntensity() {
        return intensity;
    }


    public int getType() {
        return type;
    }
}
