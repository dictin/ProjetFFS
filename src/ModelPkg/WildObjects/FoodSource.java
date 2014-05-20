package ModelPkg.WildObjects;

/**
 * Created by Xav on 07/05/2014.
 */
public class FoodSource extends WildObject {

    /**
     * Quantité de nourriture présente dans cette source.
     */
    private int foodQuantity;

    public FoodSource(int quantity) {
        super(WildObject.FOOD_ID, true);
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

    public boolean isEmpty(){
        boolean isEmpty = (foodQuantity <= 0);
        return isEmpty;
    }

    public void decreaseFoodQuantity() {
        this.foodQuantity--;

    }
}
