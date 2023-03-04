package HW.lesson5;

import HW.Lesson5.marketAPI.dto.Category;
import HW.Lesson5.marketAPI.services.CategoryService;
import HW.Lesson5.marketAPI.util.RetrofitUtility;
import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import retrofit2.Response;

import static org.hamcrest.MatcherAssert.assertThat;

public class GetCategoryTest {

    static CategoryService categoryService;


    @BeforeAll
    static void beforeAll() {
        categoryService = RetrofitUtility.getRetrofit().create(CategoryService.class);
    }

    @SneakyThrows
    @ParameterizedTest
    @CsvSource({"1, true", "0, false", "100, false"})
    void getCategoryTest(int id, boolean expectedResult) {
        Response<Category> response = categoryService.getCategory(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(expectedResult));

    }


}
