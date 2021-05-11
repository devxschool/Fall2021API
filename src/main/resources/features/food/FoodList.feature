Feature: List foods in the cache


  Scenario: User adds new item to cache
    Given add new food to FoodDelivery with the following fields
      | id | description | imageUrl        | price | name   | foodType  |
      | 1  | Wine        | https:foods.com | 20.00 | Merlot | Beverages |
    Then verify that status code is 200
    Given add new food to FoodDelivery with the following fields
      | id | description | imageUrl        | price | name   | foodType |
      | 2  | Lagman      | https:foods.com | 12.00 | Lagman | MainDish |
    Then verify that status code is 200
    Given add new food to FoodDelivery with the following fields
      | id | description | imageUrl        | price | name  | foodType |
      | 3  | Manty       | https:foods.com | 11.00 | Manty | MainDish |
    Then verify that status code is 200
    Then verify that food has been successfully added
      | id | description | imageUrl        | price | name   | foodType  |
      | 3  | Manty       | https:foods.com | 11.00 | Manty  | MainDish  |
      | 1  | Wine        | https:foods.com | 20.00 | Merlot | Beverages |
      | 2  | Lagman      | https:foods.com | 12.00 | Lagman | MainDish  |
    When list food cache request is sent
    Then verify that food cache has the following response
      | numberOfFoodsInCache | numberOfAppetizers | numberOfMainDishes | numberOfUnknownFood |
      | 3                    | 0                  | 2                  | 1                   |
    Then verify the following foods are in the response
      | id | description | imageUrl        | price | name   | foodType  |
      | 3  | Manty       | https:foods.com | 11.00 | Manty  | MainDish  |
      | 1  | Wine        | https:foods.com | 20.00 | Merlot | Beverages |
      | 2  | Lagman      | https:foods.com | 12.00 | Lagman | MainDish  |

