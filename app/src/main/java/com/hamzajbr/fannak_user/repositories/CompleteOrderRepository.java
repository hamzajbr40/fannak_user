package com.hamzajbr.fannak_user.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hamzajbr.fannak_user.managers.RestClient;
import com.hamzajbr.fannak_user.models.responses.BasicResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompleteOrderRepository {
    private static CompleteOrderRepository completeOrderRepository;
    public static CompleteOrderRepository getInstance(){
        if (completeOrderRepository == null)
            completeOrderRepository = new CompleteOrderRepository();
        return completeOrderRepository;
    }
    public LiveData<BasicResponse> completeOrder(int orderId){
        MutableLiveData<BasicResponse> completeOrderResponse = new MutableLiveData<>();
        Call<BasicResponse> call = RestClient.getService().completeOrder(orderId);
        call.enqueue(new Callback<BasicResponse>() {
            @Override
            public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                completeOrderResponse.setValue(response.body());
            }

            @Override
            public void onFailure(Call<BasicResponse> call, Throwable t) {
                completeOrderResponse.setValue(null);
                Log.e("onFailure","COMPLETE ORDER API CALL FAIL",t);


            }
        });
        return completeOrderResponse;
    }

}
