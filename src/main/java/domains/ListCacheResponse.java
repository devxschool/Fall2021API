package domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import java.util.List;


@Data
public class ListCacheResponse {

    
    //@JsonIgnore - is used to ignore some properties from being serialized or deserialized
    
    
    private int numberOfFoodsInCache;
    private int numberOfAppetizers;
    private int numberOfMainDishes;
    private int numberOfUnknownFood;

    private List<Food> foodCached;
}
