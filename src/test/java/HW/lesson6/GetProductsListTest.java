package HW.lesson6;

import HW.Lesson6.dto.Product;
import HW.Lesson6.services.ProductService;
import HW.Lesson6.util.RetrofitUtility;
import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSession;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterAll;
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
        // check by API
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

}
