package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import db_utils.DBUtils;
import db_utils.ResultSetHandler;
import domains.Food;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class DbSteps {

    @Then("^the following food should be committed to db$")
    public void the_following_food_should_be_committed_to_db(List<Food> expectedFoodInDb) {
        System.out.println(expectedFoodInDb);
        ResultSetHandler rs = DBUtils.query("SELECT * FROM food");
        List<Food> actualFoodFromDb = rs.toBeans(Food.class);

        assertEquals("Invalid number of DB entries",expectedFoodInDb.size(),actualFoodFromDb.size());
        assertEquals("Invalid description in DB",expectedFoodInDb.get(0).getDescription(),actualFoodFromDb.get(0).getDescription());
        assertEquals("Invalid food type in DB",expectedFoodInDb.get(0).getFood_type(),actualFoodFromDb.get(0).getFood_type());
        assertEquals("Invalid name in DB",expectedFoodInDb.get(0).getName(),actualFoodFromDb.get(0).getName());
        assertEquals("Invalid image url in DB",expectedFoodInDb.get(0).getImage_url(),actualFoodFromDb.get(0).getImage_url());
        assertEquals("Invalid price in DB",expectedFoodInDb.get(0).getPrice(),actualFoodFromDb.get(0).getPrice(),0.0);
    }

    @Given("^\"([^\"]*)\" table is truncated$")
    public void tableIsTruncated(String table) throws Throwable {
        DBUtils.query("SET FOREIGN_KEY_CHECKS = 0;");
        DBUtils.truncateTable(table);
    }
}
