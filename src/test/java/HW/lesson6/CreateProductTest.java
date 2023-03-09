package HW.lesson6;

import HW.Lesson6.db.dao.ProductsMapper;
import HW.Lesson6.db.model.ProductsExample;
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

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class CreateProductTest {

    static ProductService productService;
    static Properties properties = new Properties();
    static SqlSession session;
    static ProductsMapper mapper;
    static ProductsExample example;
    static int price;


    @BeforeAll
    static void beforeAll() throws IOException {
        productService = RetrofitUtility.getRetrofit().create(ProductService.class);

        try {
            properties.load(new FileInputStream("src/test/resources/tests.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        price = Integer.parseInt(properties.getProperty("price"));

        session = MyBatisUtility.openSqlSession();
        mapper = session.getMapper(ProductsMapper.class);
        example = new ProductsExample();

    }


    @SneakyThrows
    @Test
    void createProductTest() {
        Product product = new Product()
                .withCategoryTitle(properties.getProperty("categoryTitle"))
                .withTitle(properties.getProperty("title"))
                .withPrice(price);

        Response<Product> response = productService.createProduct(product).execute();

        // check by API
        assertThat(response.isSuccessful(), is(true));
        assertThat(response.code(), is(201));
        assert response.body() != null;

        // check by DB
        int id = response.body().getId();
        assertThat(mapper.selectByPrimaryKey(Long.valueOf(id)).getTitle(), is(properties.getProperty("title")));
        assertThat(mapper.selectByPrimaryKey(Long.valueOf(id)).getPrice(), is(price));

    }


    @SneakyThrows
    @Test
    void createNewProductWithIdErrorTest() {
        Product product = new Product()
                .withId(999)
                .withCategoryTitle(properties.getProperty("categoryTitle"))
                .withTitle(properties.getProperty("title"))
                .withPrice(Integer.parseInt(properties.getProperty("price")));

        Response<Product> response = productService.createProduct(product).execute();

        // check by API
        assertThat(response.code(), is(400));
        // check by DB
        assertThat(mapper.selectByPrimaryKey(Long.valueOf(999)), nullValue());
    }


    @AfterAll
    static void cleanUp() {
        session.close();
    }


}
