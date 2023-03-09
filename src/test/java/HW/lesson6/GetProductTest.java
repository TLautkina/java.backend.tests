package HW.lesson6;

import HW.Lesson6.db.dao.ProductsMapper;
import HW.Lesson6.dto.Product;
import HW.Lesson6.services.ProductService;
import HW.Lesson6.util.MyBatisUtility;
import HW.Lesson6.util.RetrofitUtility;
import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import retrofit2.Response;


import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetProductTest {

    static ProductService productService;
    static SqlSession session;
    static ProductsMapper mapper;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtility.getRetrofit().create(ProductService.class);
        session = MyBatisUtility.openSqlSession();
        mapper = session.getMapper(ProductsMapper.class);
    }

    @SneakyThrows
    @ParameterizedTest
    @CsvSource({"1, true", "0, false", "100, false", "-2, false"})
    void getProductTest(int id, boolean expectedResult) {
        Response<Product> response = productService.getProduct(id).execute();

        // check by API
        assertThat(response.isSuccessful(), is(expectedResult));
        if (response.body() != null) assertThat(response.body().getId(), is(id));

        // check by DB
        assertThat(mapper.selectByPrimaryKey(Long.valueOf(id)) != null, is(expectedResult));
    }

    @AfterAll
    static void cleanUp() {
        session.close();
    }

}
