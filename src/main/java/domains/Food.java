package domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Food {

    @JsonIgnore
    private String id;
    private String description;
    private String imageUrl;
    private double price;
    private String name;
    private String foodType;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String food_type;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String image_url;
}
