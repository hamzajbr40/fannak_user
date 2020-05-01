package com.hamzajbr.fannak_user.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hamzajbr.fannak_user.managers.RestClient;
import com.hamzajbr.fannak_user.models.ProductItem;
import com.hamzajbr.fannak_user.models.responses.BaseResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeaturedRepository {
    private static FeaturedRepository featuredRepository;

    public static FeaturedRepository getInstance(){
        if (featuredRepository== null){
            featuredRepository = new FeaturedRepository();
        }
        return featuredRepository;
    }

    public LiveData<List<ProductItem>> getFeatured(){
        MutableLiveData<List<ProductItem>> featuredData = new MutableLiveData<>();
        Call<BaseResponse<List<ProductItem>>> call = RestClient.getService().getFeatured();
        call.enqueue(new Callback<BaseResponse<List<ProductItem>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<ProductItem>>> call, Response<BaseResponse<List<ProductItem>>> response) {
                if(response.isSuccessful())
                    featuredData.setValue(response.body().data);
            }

            @Override
            public void onFailure(Call<BaseResponse<List<ProductItem>>> call, Throwable t) {
                featuredData.setValue(null);
            }
        });
        return featuredData;
    }

}
