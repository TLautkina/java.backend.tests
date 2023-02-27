package HW.lesson3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class MealplanTest extends AbstractTest {

    private static String id;

    @Test
    @DisplayName("Добавление плана и его удаление ")
    void ComplexSerchStatusCodeTest(){
        id = given()
                .queryParam("hash", getHash())
                .queryParam("apiKey", getApiKey())
                .body("{\n"
                        + " \"date\": 1589500800,\n"
                        + " \"slot\": 1,\n"
                        + " \"position\": 0,\n"
                        + " \"type\": \"MENU_ITEM\",\n"
                        + " \"value\": {\n"
                        + " \"id\": \"378557\",\n"
                        + "\"servings\": \"1\",\n"
                        + "\"title\": \"Pizza 73 BBQ Steak Pizza, 9\",\n"
                        + "\"imageType\": \"png\",\n"
                        + " }\n"
                        + "}")
                .when()
                .post("https://api.spoonacular.com/mealplanner/geekbrains/items")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("id")
                .toString();
    }
    @AfterEach
    void tearDown() {
        given()
                .queryParam("hash", getHash())
                .queryParam("apiKey", getApiKey())
                .delete("https://api.spoonacular.com/mealplanner/geekbrains/items/" + id)
                .then()
                .statusCode(200);
    }
}
