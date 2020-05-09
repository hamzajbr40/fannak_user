package com.hamzajbr.fannak_user.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hamzajbr.fannak_user.managers.RestClient;
import com.hamzajbr.fannak_user.models.CurrentOrderItem;
import com.hamzajbr.fannak_user.models.ProductItem;
import com.hamzajbr.fannak_user.models.responses.BaseResponse;
import com.hamzajbr.fannak_user.utilities.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrdersRepository {
    private static OrdersRepository ordersRepository;
    public static OrdersRepository getInstance(){
        if(ordersRepository == null){
            ordersRepository = new OrdersRepository();
        }
        return ordersRepository;
    }

    public LiveData<List<CurrentOrderItem>> getCurrentOrders(int id){
        MutableLiveData<List<CurrentOrderItem>> currentOrderData = new MutableLiveData<>();
        Call<BaseResponse<List<CurrentOrderItem>>> call = RestClient.getService().getCurrentOrders(id);
        call.enqueue(new Callback<BaseResponse<List<CurrentOrderItem>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<CurrentOrderItem>>> call, Response<BaseResponse<List<CurrentOrderItem>>> response) {
                currentOrderData.setValue(response.body().data);
            }

            @Override
            public void onFailure(Call<BaseResponse<List<CurrentOrderItem>>> call, Throwable t) {
                currentOrderData.setValue(null);
                Log.e("onFailure","GET CURRENT ORDERS API CALL FAIL",t);

            }
        });




        return currentOrderData;
    }

    public LiveData<List<ProductItem>> getPreviousOrders(int id){
        MutableLiveData<List<ProductItem>> previousOrderData = new MutableLiveData<>();
        Call<BaseResponse<List<ProductItem>>> call = RestClient.getService().getPreviousOrders(id);
        call.enqueue(new Callback<BaseResponse<List<ProductItem>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<ProductItem>>> call, Response<BaseResponse<List<ProductItem>>> response) {
                previousOrderData.setValue(response.body().data);
            }

            @Override
            public void onFailure(Call<BaseResponse<List<ProductItem>>> call, Throwable t) {
                previousOrderData.setValue(null);
                Log.e("onFailure","GET PREVIOUS ORDERS API CALL FAIL",t);

            }
        });
        return previousOrderData;
    }



}
