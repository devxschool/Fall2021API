package practice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domains.Food;
import domains.ListCacheResponse;
import org.junit.Test;

public class JacksonAnnotations {

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void ignore() throws JsonProcessingException {

        ListCacheResponse listCacheResponse = new ListCacheResponse();
        listCacheResponse.setNumberOfAppetizers(12);
        listCacheResponse.setNumberOfMainDishes(44);
        listCacheResponse.setNumberOfFoodsInCache(2543);



        String json = objectMapper.writeValueAsString(listCacheResponse);
        System.out.println(json);
    }

    @Test
    public void ignoreProperties() throws JsonProcessingException {
        String json = "{\n" +
                "    \"numberOfFoodsInCache\": 3,\n" +
                "    \"numberOfAppetizers\": 0,\n" +
                "    \"numberOfMainDishes\": 3,\n" +
                "    \"numberOfUnknownFood\": 0,\n" +
                "    \"foodCached\": [\n" +
                "        {\n" +
                "            \"description\": \"Plov\",\n" +
                "            \"imageUrl\": \"https:foods\",\n" +
                "            \"price\": 15.00,\n" +
                "            \"name\": \"Plov\",\n" +
                "            \"foodType\": \"MainDish\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"description\": \"Plov\",\n" +
                "            \"imageUrl\": \"https:foods\",\n" +
                "            \"price\": 15.00,\n" +
                "            \"name\": \"Plov\",\n" +
                "            \"foodType\": \"MainDish\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"description\": \"Plov\",\n" +
                "            \"imageUrl\": \"https:foods\",\n" +
                "            \"price\": 15.00,\n" +
                "            \"name\": \"Plov\",\n" +
                "            \"foodType\": \"MainDish\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        ListCacheResponse response = objectMapper.readValue(json, ListCacheResponse.class);

        System.out.println(response);

    }


    @Test
    public void ignoreProps() throws JsonProcessingException {
        String json
                = "{   \n" +
                "    \"id\":\"1\",\n" +
                "   \"description\":\"Lagman\",\n" +
                "   \"imageUrl\": \"https:foods\",\n" +
                "   \"price\": \"25.00\",\n" +
                "   \"name\": \"Noodles\",\n" +
                "   \"foodType\": \"MainDish\",\n" +
                "\n" +
                "    \"Batch\": \"Sring2021\"\n" +
                "}";
        Food response = objectMapper.readValue(json, Food.class);

        System.out.println(response);
    }
}
