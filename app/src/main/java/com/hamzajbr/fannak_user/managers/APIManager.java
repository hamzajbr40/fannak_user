package com.hamzajbr.fannak_user.managers;

import com.hamzajbr.fannak_user.models.BannerItem;
import com.hamzajbr.fannak_user.models.CategoryItem;
import com.hamzajbr.fannak_user.models.ProductItem;
import com.hamzajbr.fannak_user.models.SellerItem;
import com.hamzajbr.fannak_user.models.requests.LoginRequest;
import com.hamzajbr.fannak_user.models.requests.SearchByCategoryRequest;
import com.hamzajbr.fannak_user.models.requests.SearchByNameRequest;
import com.hamzajbr.fannak_user.models.requests.SearchByTypeRequest;
import com.hamzajbr.fannak_user.models.requests.SignupRequest;
import com.hamzajbr.fannak_user.models.responses.BaseResponse;
import com.hamzajbr.fannak_user.models.responses.LoginResponse;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIManager {




    @POST("customerLogin")
    Call<BaseResponse<LoginResponse>> login(@Body LoginRequest body);

    @POST("customerSignup")
    Call<BaseResponse> signup(@Body SignupRequest body);

    @GET("getBanner")
    Call<BaseResponse<List<BannerItem>>> getBanner();

    @GET("getCategories")
    Call<BaseResponse<List<CategoryItem>>> getCategories();

    @GET("getFeatured")
    Call<BaseResponse<List<ProductItem>>> getFeatured();

    @POST("getSellerById")
    Call<BaseResponse<SellerItem>> getSellerByID(@Query("Id") int id);

    @POST("getCurrentOrders")
    Call<BaseResponse<List<ProductItem>>> getCurrentOrders(@Query("Id") int id);

    @POST("getPreviousOrders")
    Call<BaseResponse<List<ProductItem>>> getPreviousOrders(@Query("Id") int id);

    //Search by name
    @POST("searchItem")
    Call<BaseResponse<List<ProductItem>>> searchItem(@Body SearchByNameRequest body);

    //Search by category
    @POST("searchItem")
    Call<BaseResponse<List<ProductItem>>> searchItem(@Body SearchByCategoryRequest body);

    //Search by type
    @POST("searchItem")
    Call<BaseResponse<List<ProductItem>>> searchItem(@Body SearchByTypeRequest body);

    //Get all Products
    @GET("searchItem")
    Call<BaseResponse<List<ProductItem>>> getAllProducts();

}
