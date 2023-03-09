package HW.lesson6;

import HW.Lesson6.db.dao.ProductsMapper;
import HW.Lesson6.dto.Product;
import HW.Lesson6.services.ProductService;
import HW.Lesson6.util.MyBatisUtility;
import HW.Lesson6.util.RetrofitUtility;
import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSession;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteProductTest {

    static Properties properties = new Properties();
    static ProductService productService;
    static SqlSession session;
    static ProductsMapper mapper;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtility.getRetrofit().create(ProductService.class);

        try {
            properties.load(new FileInputStream("src/test/resources/tests.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        session = MyBatisUtility.openSqlSession();
        mapper = session.getMapper(ProductsMapper.class);

    }

    @SneakyThrows
    @Test
    void deleteProductWithSpecificIdTest() {

        Product product = new Product()
                .withCategoryTitle(properties.getProperty("categoryTitle"))
                .withTitle(properties.getProperty("title"))
                .withPrice(Integer.parseInt(properties.getProperty("price")));

        Response<Product> response = productService.createProduct(product).execute();
        assert response.body() != null;
        int id = response.body().getId();

        productService.deleteProduct((id)).execute();

        // check by API
        assertThat(productService.getProduct(
                id).execute().body(),
                CoreMatchers.nullValue());

        // check by DB
        assertThat(mapper.selectByPrimaryKey(Long.valueOf(id)), is(nullValue()));

    }

    @AfterAll
    static void cleanUp() {
        session.close();
    }

}
