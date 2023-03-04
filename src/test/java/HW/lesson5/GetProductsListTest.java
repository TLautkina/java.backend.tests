package HW.lesson5;

import HW.Lesson5.marketAPI.dto.Product;
import HW.Lesson5.marketAPI.services.ProductService;
import HW.Lesson5.marketAPI.util.RetrofitUtility;
import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class GetProductsListTest {

    static ProductService productService;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtility.getRetrofit().create(ProductService.class);
    }

    @SneakyThrows
    @Test
    void getProductListPositiveTest() {
        Response<List<Product>> response = productService.getProducts().execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }




}
