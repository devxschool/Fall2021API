$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("features/food/Food.feature");
formatter.feature({
  "line": 2,
  "name": "User should be able to ADD food to the cache",
  "description": "",
  "id": "user-should-be-able-to-add-food-to-the-cache",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@regression"
    }
  ]
});
formatter.before({
  "duration": 1131544956,
  "status": "passed"
});
formatter.background({
  "line": 4,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 5,
  "name": "\"food\" table is truncated",
  "keyword": "Given "
});
formatter.match({
  "arguments": [
    {
      "val": "food",
      "offset": 1
    }
  ],
  "location": "DbSteps.tableIsTruncated(String)"
});
formatter.result({
  "duration": 829329923,
  "status": "passed"
});
formatter.scenario({
  "line": 7,
  "name": "User adds new item to cache",
  "description": "",
  "id": "user-should-be-able-to-add-food-to-the-cache;user-adds-new-item-to-cache",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 8,
  "name": "add new food to FoodDelivery with the following fields",
  "rows": [
    {
      "cells": [
        "description",
        "imageUrl",
        "price",
        "name",
        "foodType"
      ],
      "line": 9
    },
    {
      "cells": [
        "Wine",
        "https:foods.com",
        "20.00",
        "Merlot",
        "Beverages"
      ],
      "line": 10
    }
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 11,
  "name": "verify that status code is 200",
  "keyword": "Then "
});
formatter.step({
  "line": 12,
  "name": "verify that food has been successfully added",
  "rows": [
    {
      "cells": [
        "description",
        "imageUrl",
        "price",
        "name",
        "foodType"
      ],
      "line": 13
    },
    {
      "cells": [
        "Wine",
        "https:foods.com",
        "20.00",
        "Merlot",
        "Beverages"
      ],
      "line": 14
    }
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "FoodSteps.add_new_food_to_FoodDelivery_with_the_following_fields(Food\u003e)"
});
formatter.result({
  "duration": 280486526,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 27
    }
  ],
  "location": "FoodSteps.verify_that_status_code_is(int)"
});
formatter.result({
  "duration": 207119,
  "status": "passed"
});
formatter.match({
  "location": "FoodSteps.verify_that_food_has_been_successfully_added(Food\u003e)"
});
formatter.result({
  "duration": 47096505,
  "status": "passed"
});
formatter.before({
  "duration": 75801060,
  "status": "passed"
});
formatter.background({
  "line": 4,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 5,
  "name": "\"food\" table is truncated",
  "keyword": "Given "
});
formatter.match({
  "arguments": [
    {
      "val": "food",
      "offset": 1
    }
  ],
  "location": "DbSteps.tableIsTruncated(String)"
});
formatter.result({
  "duration": 156327832,
  "status": "passed"
});
formatter.scenario({
  "line": 17,
  "name": "User adds new food to FoodDelivery without image url",
  "description": "",
  "id": "user-should-be-able-to-add-food-to-the-cache;user-adds-new-food-to-fooddelivery-without-image-url",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 18,
  "name": "add new food to FoodDelivery with the following fields",
  "rows": [
    {
      "cells": [
        "description",
        "price",
        "name",
        "foodType"
      ],
      "line": 19
    },
    {
      "cells": [
        "Wine",
        "20.00",
        "Merlot",
        "Beverages"
      ],
      "line": 20
    }
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 21,
  "name": "verify that status code is 403",
  "keyword": "Then "
});
formatter.step({
  "line": 22,
  "name": "verify response error message \"Invalid request - Food image url cannot be null or empty.\"",
  "keyword": "Then "
});
formatter.match({
  "location": "FoodSteps.add_new_food_to_FoodDelivery_with_the_following_fields(Food\u003e)"
});
formatter.result({
  "duration": 99984584,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "403",
      "offset": 27
    }
  ],
  "location": "FoodSteps.verify_that_status_code_is(int)"
});
formatter.result({
  "duration": 76699,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Invalid request - Food image url cannot be null or empty.",
      "offset": 31
    }
  ],
  "location": "FoodSteps.verifyResponseErrorMessage(String)"
});
formatter.result({
  "duration": 908406,
  "status": "passed"
});
formatter.before({
  "duration": 75771334,
  "status": "passed"
});
formatter.background({
  "line": 4,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 5,
  "name": "\"food\" table is truncated",
  "keyword": "Given "
});
formatter.match({
  "arguments": [
    {
      "val": "food",
      "offset": 1
    }
  ],
  "location": "DbSteps.tableIsTruncated(String)"
});
formatter.result({
  "duration": 152170226,
  "status": "passed"
});
formatter.scenario({
  "line": 24,
  "name": "User commits the food in cache",
  "description": "",
  "id": "user-should-be-able-to-add-food-to-the-cache;user-commits-the-food-in-cache",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 25,
  "name": "add new food to FoodDelivery with the following fields",
  "rows": [
    {
      "cells": [
        "description",
        "imageUrl",
        "price",
        "name",
        "foodType"
      ],
      "line": 26
    },
    {
      "cells": [
        "Wine",
        "https:foods.com",
        "20.00",
        "Merlot",
        "Beverages"
      ],
      "line": 27
    }
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 28,
  "name": "verify that status code is 200",
  "keyword": "And "
});
formatter.step({
  "line": 29,
  "name": "verify that food has been successfully added",
  "rows": [
    {
      "cells": [
        "description",
        "imageUrl",
        "price",
        "name",
        "foodType"
      ],
      "line": 30
    },
    {
      "cells": [
        "Wine",
        "https:foods.com",
        "20.00",
        "Merlot",
        "Beverages"
      ],
      "line": 31
    }
  ],
  "keyword": "And "
});
formatter.step({
  "line": 32,
  "name": "users commits the cache to db",
  "keyword": "When "
});
formatter.step({
  "line": 33,
  "name": "app should send the following response",
  "rows": [
    {
      "cells": [
        "numberOfFoodsSaved",
        "message"
      ],
      "line": 34
    },
    {
      "cells": [
        "1",
        "Food Cache is committed to db"
      ],
      "line": 35
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 36,
  "name": "the following food should be committed to db",
  "rows": [
    {
      "cells": [
        "description",
        "food_type",
        "image_url",
        "name",
        "price"
      ],
      "line": 37
    },
    {
      "cells": [
        "Wine",
        "0",
        "https:foods.com",
        "Merlot",
        "20.00"
      ],
      "line": 38
    }
  ],
  "keyword": "And "
});
formatter.match({
  "location": "FoodSteps.add_new_food_to_FoodDelivery_with_the_following_fields(Food\u003e)"
});
formatter.result({
  "duration": 83499981,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 27
    }
  ],
  "location": "FoodSteps.verify_that_status_code_is(int)"
});
formatter.result({
  "duration": 86573,
  "status": "passed"
});
formatter.match({
  "location": "FoodSteps.verify_that_food_has_been_successfully_added(Food\u003e)"
});
formatter.result({
  "duration": 1732715,
  "status": "passed"
});
formatter.match({
  "location": "FoodSteps.users_commits_the_cache_to_db()"
});
formatter.result({
  "duration": 76106720,
  "status": "passed"
});
formatter.match({
  "location": "FoodSteps.app_should_send_the_following_response(CommitToDbResponse\u003e)"
});
formatter.result({
  "duration": 1420738,
  "status": "passed"
});
formatter.match({
  "location": "DbSteps.the_following_food_should_be_committed_to_db(Food\u003e)"
});
formatter.result({
  "duration": 81297340,
  "error_message": "java.lang.AssertionError: Invalid number of DB entries expected:\u003c1\u003e but was:\u003c0\u003e\n\tat org.junit.Assert.fail(Assert.java:88)\n\tat org.junit.Assert.failNotEquals(Assert.java:743)\n\tat org.junit.Assert.assertEquals(Assert.java:118)\n\tat org.junit.Assert.assertEquals(Assert.java:555)\n\tat steps.DbSteps.the_following_food_should_be_committed_to_db(DbSteps.java:22)\n\tat ✽.And the following food should be committed to db(features/food/Food.feature:36)\n",
  "status": "failed"
});
});