package ModelPkg.PkgItems;

import java.util.Random;

public enum LotteryEffects implements LotteryItemEffect {

    LOW_VALUE_TICKET(90, 50),
    MED_VALUE_TICKET(60, 100),
    HIGH_VALUE_TICKET(30, 500),
    SUPER_VALUE_TICKET(10, 1000);

    private int probability;
    private int value;
    private int winnings;

    private LotteryEffects(int probability, int value){
        this .probability = probability;
        this.value = value;
    }

    public void activate(){

    }

    @Override
    public int activateLottery() {
        Random random = new Random();
        int rndNbr = random.nextInt(100) +  1;
        int winnings;

        if (rndNbr <= this.probability){
            winnings = this.value;
        }else{
            winnings = 0;
        }

        return winnings;
    }

}
