package com.eomsbd.cutprice.web_api;

import com.eomsbd.cutprice.model.category_model.Category;
import com.eomsbd.cutprice.model.login_model.LoginResponse;
import com.eomsbd.cutprice.model.login_model.UserLogin;
import com.eomsbd.cutprice.model.registration_model.RegistrationResponse;
import com.eomsbd.cutprice.model.registration_model.UserRegistration;
import com.eomsbd.cutprice.model.sub_category.CategoryId;
import com.eomsbd.cutprice.model.product_order_model.ProductOrder;
import com.eomsbd.cutprice.model.product_order_model.UserOrder;
import com.eomsbd.cutprice.model.products_model.Products;
import com.eomsbd.cutprice.model.sub_category.SubCategory;
import com.eomsbd.cutprice.model.sub_category_menu_item.SubCategoryMenu;
import com.eomsbd.cutprice.model.update_password.SuccessResponse;
import com.eomsbd.cutprice.model.update_password.UpdateResponse;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IClientServer {
    @Headers({"Cache-Control: max-age=640000", "Session-Agent: Cut Price BD"})
    @GET("products/getAllProducts")
    Call<Products> getALlProducts(@Query("api_key") String id, @Query("start") int start,@Query("limit") int limit);
    Call<Products> getALlProducts1(@Query("api_key") String id, @Query("start") int start, @Query("limit") int limit);

    @GET("products/getCategory")
    Call<Category> getSubmenu();


    @GET("menu/allMenus")
    Call<SubCategoryMenu>getSubCategoryMenu();


    //post api
    @POST("products/buy_now")
    Call<UserOrder> orderProduct(@Body ProductOrder productOrder);

    @POST("products/getProductByCatId")
    Call<SubCategory> getSubCategory(@Body CategoryId catId);

    @POST("login")
    Call<LoginResponse> loginInfo(@Body UserLogin userLogin);

    @POST("registration")
    Call<RegistrationResponse> registrationInfo(@Body UserRegistration userRegistration);

    @POST("login/update_password")
    Call<SuccessResponse>getUpdatePassword(@Body UpdateResponse updateResponse);




}
