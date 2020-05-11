package com.hamzajbr.fannak_user.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hamzajbr.fannak_user.managers.APIManager;
import com.hamzajbr.fannak_user.managers.RestClient;
import com.hamzajbr.fannak_user.models.BannerItem;
import com.hamzajbr.fannak_user.models.responses.BaseResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BannersRepository {
    private static BannersRepository bannersRepository;

    public static BannersRepository getInstance(){
        if(bannersRepository == null){
            bannersRepository = new BannersRepository();
        }
        return bannersRepository;
    }

    public LiveData<List<BannerItem>> getBanners(){
        MutableLiveData<List<BannerItem>> bannersData = new MutableLiveData<>();
        Call<BaseResponse<List<BannerItem>>> call = RestClient.getService().getBanner();
        call.enqueue(new Callback<BaseResponse<List<BannerItem>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<BannerItem>>> call, Response<BaseResponse<List<BannerItem>>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    bannersData.setValue(response.body().data);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<BannerItem>>> call, Throwable t) {
                bannersData.setValue(null);
                Log.e("onFailure","GET BANNERS API CALL FAIL",t);
            }
        });

        return bannersData;
    }
}
