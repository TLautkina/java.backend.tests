package HW.Lesson6.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import HW.Lesson6.dto.Category;


public interface CategoryService {

    @GET("categories/{id}")
    Call<Category> getCategory(@Path("id") int id);

}
