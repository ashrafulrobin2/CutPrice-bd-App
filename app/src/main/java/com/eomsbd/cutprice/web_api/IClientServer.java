package com.eomsbd.cutprice.web_api;

import com.eomsbd.cutprice.model.category_model.Category;
import com.eomsbd.cutprice.model.product_order_model.ProductOrder;
import com.eomsbd.cutprice.model.product_order_model.UserOrder;
import com.eomsbd.cutprice.model.products_model.Products;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IClientServer {
    @GET("products/getAllProducts")
    Call<Products> getALlProducts(@Query("api_key") String id);

    @GET("products/getCategory")
    Call<Category> getSubmenu();

    /*
        @POST("products/buy_now")
        @FormUrlEncoded
        Call<UserOrder> orderProduct(@Body ParamProductOrder paramProductOrder);
        */
    @POST("products/buy_now")
    Call<UserOrder> orderProduct(@Body ProductOrder productOrder);

}
