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
import org.junit.jupiter.api.Test;
import retrofit2.Response;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class EditProductTest {

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
    void editProductWithSpecificIdTest () {

        Product product = new Product()
                .withCategoryTitle(properties.getProperty("categoryTitle"))
                .withTitle(properties.getProperty("title"))
                .withPrice(Integer.parseInt(properties.getProperty("price")));

        Response<Product> response = productService.createProduct(product).execute();

        assert response.body() != null;
        int id = response.body().getId();

        Product editedProduct = new Product()
                .withId(id)
                .withCategoryTitle(properties.getProperty("categoryTitle"))
                .withTitle(properties.getProperty("editedTitle"))
                .withPrice(Integer.parseInt(properties.getProperty("editedPrice")));

        Response<Product> editedResponse = productService.editProduct(editedProduct).execute();

        // check by API
        assertThat(editedResponse.isSuccessful(), is(true));
        assertThat(editedResponse.body().getTitle(), is(properties.getProperty("editedTitle")));
        assertThat(editedResponse.body().getPrice(), is(Integer.parseInt(properties.getProperty("editedPrice"))));

        // check by DB
        assertThat(mapper.selectByPrimaryKey(Long.valueOf(id)).getTitle(), is(properties.getProperty("editedTitle")));
        assertThat(mapper.selectByPrimaryKey(Long.valueOf(id)).getPrice(), is(Integer.parseInt(properties.getProperty("editedPrice"))));

    }

    @AfterAll
    static void cleanUp() {
        session.close();
    }

}
