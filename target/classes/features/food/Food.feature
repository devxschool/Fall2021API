@regression
Feature: User should be able to ADD food to the cache

  Background:
    Given "food" table is truncated

  Scenario: User adds new item to cache
    Given add new food to FoodDelivery with the following fields
      | description | imageUrl        | price | name   | foodType  |
      | Wine        | https:foods.com | 20.00 | Merlot | Beverages |
    Then verify that status code is 200
    Then verify that food has been successfully added
      | description | imageUrl        | price | name   | foodType  |
      | Wine        | https:foods.com | 20.00 | Merlot | Beverages |


  Scenario: User adds new food to FoodDelivery without image url
    Given add new food to FoodDelivery with the following fields
      | description | price | name   | foodType  |
      | Wine        | 20.00 | Merlot | Beverages |
    Then verify that status code is 403
    Then verify response error message "Invalid request - Food image url cannot be null or empty."

  Scenario: User commits the food in cache
    Given add new food to FoodDelivery with the following fields
      | description | imageUrl        | price | name   | foodType  |
      | Wine        | https:foods.com | 20.00 | Merlot | Beverages |
    And verify that status code is 200
    And verify that food has been successfully added
      | description | imageUrl        | price | name   | foodType  |
      | Wine        | https:foods.com | 20.00 | Merlot | Beverages |
    When users commits the cache to db
    Then app should send the following response
      | numberOfFoodsSaved | message                       |
      | 1                  | Food Cache is committed to db |
    And the following food should be committed to db
      | description | food_type | image_url       | name   | price |
      | Wine        | 0         | https:foods.com | Merlot | 20.00 |




