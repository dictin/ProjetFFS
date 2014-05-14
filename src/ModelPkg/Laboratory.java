package ModelPkg;


public class Laboratory {

    private static int speedEnduranceBefore = MapData.getFourmilierStats(0);
    private static int attackDefenceBefore = MapData.getFourmilierStats(1);
    private static int sensitivitySmellStrengthBefore = MapData.getFourmilierStats(2);
    private static int cost = 0;
    private static boolean isFinish = false;
    private static boolean reset = false;
    private static boolean moving = false;
    private static boolean stopMoving = false;

    public static int getSpeedEnduranceBefore() {
        return speedEnduranceBefore;
    }

    public static void setSpeedEnduranceBefore(int speedEnduranceBefore) {
        Laboratory.speedEnduranceBefore = speedEnduranceBefore;
    }

    public static int getAttackDefenceBefore() {
        return attackDefenceBefore;
    }

    public static void setAttackDefenceBefore(int attackDefenceBefore) {
        Laboratory.attackDefenceBefore = attackDefenceBefore;
    }

    public static int getSensitivitySmellStrengthBefore() {
        return sensitivitySmellStrengthBefore;
    }

    public static void setSensitivitySmellStrengthBefore(int sensitivitySmellStrengthBefore) {
        Laboratory.sensitivitySmellStrengthBefore = sensitivitySmellStrengthBefore;
    }

    public static int getCost() {
        return cost;
    }

    public static void setCost(int cost) {
        Laboratory.cost = cost;
    }
    public static boolean isFinish() {
        return isFinish;
    }

    public static void setFinish(boolean finish) {
        isFinish =finish;
    }


    public static boolean isMoving() {
        return moving;
    }

    public static void setMoving(boolean moving) {
        Laboratory.moving = moving;
    }

    public static boolean isStopMoving() {
        return stopMoving;
    }

    public static void setStopMoving(boolean stopMoving) {
        Laboratory.stopMoving = stopMoving;
    }

    public static boolean isReset() {
        return reset;
    }

    public static void setReset(boolean reset) {
        Laboratory.reset = reset;
    }
}
