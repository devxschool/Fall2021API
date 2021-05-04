Feature: User should be able to ADD food to the cache

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




