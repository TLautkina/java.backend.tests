package HW.lesson3;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class CuisineTest extends AbstractTest{

    @Test
    void emptyRequest() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .assertThat()
                .statusCode(200)
                .header("Content-Type", "application/json")
                .body(containsString("cuisine"));
    }

    @Test
    void sendTitle() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Corned Beef And Cabbage With Irish Mustard Sauce")
                .formParam("language", "en")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .assertThat()
                .statusCode(200)
                .header("Content-Type", "application/json")
                .body("cuisine", equalTo("Irish"));
    }

}

