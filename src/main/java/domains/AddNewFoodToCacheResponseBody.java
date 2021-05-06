package domains;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AddNewFoodToCacheResponseBody {
    private List<Food> foodCached = new ArrayList<>();
}
