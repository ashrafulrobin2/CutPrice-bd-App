package com.inducesmile.androidpayexample.web_api;

import com.inducesmile.androidpayexample.model.products_model.Products;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IClientServer {
    @GET("products/getAllProducts")
    Call<Products> getALlProducts(@Query("api_key") String id);
}
