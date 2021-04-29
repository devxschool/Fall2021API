package domains;

import java.util.ArrayList;
import java.util.List;

public class AddNewFoodToCacheResponseBody {

    private List<Food> foodCached = new ArrayList<>();

    public List<Food> getFoodCached() {
        return foodCached;
    }

    public void setFoodCached(List<Food> foodCached) {
        this.foodCached = foodCached;
    }
}
