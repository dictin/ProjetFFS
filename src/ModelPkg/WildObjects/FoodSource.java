package ModelPkg.WildObjects;

/**
 * Created by Xav on 07/05/2014.
 */
public class FoodSource extends WildObject {

    private int foodQuantity;

    public FoodSource(int type, int quantity) {
        super(type, true);
        this.foodQuantity=quantity;
        this.getSmellSource().setIntensity(getFoodQuantity());
    }

    public int getFoodQuantity() {
        return foodQuantity;
    }

    public void setFoodQuantity(int foodQuantity) {
        this.foodQuantity = foodQuantity;
        this.getSmellSource().setIntensity(foodQuantity);
    }

}
