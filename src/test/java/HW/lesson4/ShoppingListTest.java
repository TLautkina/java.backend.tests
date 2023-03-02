package HW.lesson4;

import HW.Lesson4.PostResp.AddToShoppingListRequest;
import HW.Lesson4.PostResp.Response;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ShoppingListTest extends AbstractTest {
    private  final String USER_NAME = "test262";

    @Test
    void addItemToShoppingList() {

        Response response = given()
                .spec(getRequestSpecification())
                .when()
                .body(new AddToShoppingListRequest("Sesame Flank Steak Salad", "Salad", true))
                .post(getBaseUrl() +"mealplanner/" + USER_NAME + "/shopping-list/items").prettyPeek()
                .then()
                .extract()
                .response()
                .body()
                .as(Response.class);
        assertThat(response.getName(), containsString("sesame flank steak salad"));
        assertThat(response.getAisle(),containsString("Salad"));

    }

    @Test
    void getItemFromShoppingList() {
        given()
                .spec(getRequestSpecification())
                .when()
                .get(getBaseUrl() +"mealplanner/" + USER_NAME + "/shopping-list").prettyPeek()
                .then()
                .spec(responseSpecification);
    }

    @Test
    void deleteItem() {

        Response response = given()
                .spec(getRequestSpecification())
                .when()
                .body(new AddToShoppingListRequest("tomato", "Salad", true))
                .post(getBaseUrl() +"mealplanner/" + USER_NAME + "/shopping-list/items").prettyPeek()
                .then()
                .extract()
                .response()
                .body()
                .as(Response.class);
        int id = response.getId();

        given()
                .queryParam("hash", getHash())
                .when()
                .delete(getBaseUrl() + "mealplanner/" + USER_NAME+ "/shopping-list/items/" + id)
                .then()
                .statusCode(200);
    }
}