package com.hamzajbr.fannak_user.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hamzajbr.fannak_user.managers.RestClient;

import com.hamzajbr.fannak_user.models.responses.BasicResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddOrderRepository {
    private static AddOrderRepository addOrderRepository;
    public static AddOrderRepository getInstance(){
        if (addOrderRepository == null)
            addOrderRepository = new AddOrderRepository();
        return addOrderRepository;
    }
    public LiveData<BasicResponse> addOrder(int itemId,int itemBuyer){
        MutableLiveData<BasicResponse> addOrderResponse = new MutableLiveData<>();
        Call<BasicResponse> call = RestClient.getService().addOrder(itemId,itemBuyer);
        call.enqueue(new Callback<BasicResponse>() {
            @Override
            public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                addOrderResponse.setValue(response.body());
            }

            @Override
            public void onFailure(Call<BasicResponse> call, Throwable t) {
                addOrderResponse.setValue(null);
                Log.e("onFailure","ADD ORDER API CALL FAIL",t);
            }
        });

        return addOrderResponse;
    }
}
