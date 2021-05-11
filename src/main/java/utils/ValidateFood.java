package utils;

import domains.Food;
import org.junit.Assert;

import java.util.List;

public class ValidateFood {


    public static void validateFood(List<Food> expectedResponsePayload, List<Food> actualResponseBody) {

        for (Food expectedFood : expectedResponsePayload) {
            for (Food actualFood : actualResponseBody) {
                if (actualFood.getName().equals(expectedFood.getName())) {
                    Assert.assertEquals("Description doesn't match", expectedFood.getDescription(), actualFood.getDescription());
                    Assert.assertEquals("ImageUrl doesn't match", expectedFood.getImageUrl(), actualFood.getImageUrl());
                    Assert.assertEquals("FoodType doesn't match", expectedFood.getFoodType(), actualFood.getFoodType());
                    Assert.assertEquals("Name doesn't match", expectedFood.getName(), actualFood.getName());
                    Assert.assertEquals("Price doesn't match", expectedFood.getPrice(), actualFood.getPrice(), 0);
                }
            }
        }

    }
}
