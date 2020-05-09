package com.hamzajbr.fannak_user.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hamzajbr.fannak_user.managers.RestClient;
import com.hamzajbr.fannak_user.models.ProductItem;
import com.hamzajbr.fannak_user.models.requests.SearchByCategoryRequest;
import com.hamzajbr.fannak_user.models.requests.SearchByTypeRequest;
import com.hamzajbr.fannak_user.models.responses.BaseResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchRepository {
    private static SearchRepository searchRepository;
    public static SearchRepository getInstance(){
        if(searchRepository == null){
            searchRepository = new SearchRepository();
        }
        return searchRepository;
    }

    public LiveData<List<ProductItem>> search(SearchByCategoryRequest request){
        MutableLiveData<List<ProductItem>> searchedData = new MutableLiveData<>();
        Call<BaseResponse<List<ProductItem>>> call = RestClient.getService().searchItem(request);
        call.enqueue(new Callback<BaseResponse<List<ProductItem>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<ProductItem>>> call, Response<BaseResponse<List<ProductItem>>> response) {
                searchedData.setValue(response.body().data);
            }

            @Override
            public void onFailure(Call<BaseResponse<List<ProductItem>>> call, Throwable t) {
                searchedData.setValue(null);
                Log.e("onFailure","SEARCH API CALL FAIL",t);
            }
        });

        return searchedData;
    }

    public LiveData<List<ProductItem>> search(SearchByTypeRequest request){
        MutableLiveData<List<ProductItem>> searchedData = new MutableLiveData<>();
        Call<BaseResponse<List<ProductItem>>> call = RestClient.getService().searchItem(request);
        call.enqueue(new Callback<BaseResponse<List<ProductItem>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<ProductItem>>> call, Response<BaseResponse<List<ProductItem>>> response) {
                searchedData.setValue(response.body().data);
            }

            @Override
            public void onFailure(Call<BaseResponse<List<ProductItem>>> call, Throwable t) {
                searchedData.setValue(null);
                Log.e("onFailure","SEARCH API CALL FAIL",t);
            }
        });


        return searchedData;
    }
    public LiveData<List<ProductItem>> getAllProducts(){
        MutableLiveData<List<ProductItem>> searchedData = new MutableLiveData<>();
        Call<BaseResponse<List<ProductItem>>> call = RestClient.getService().getAllProducts();
        call.enqueue(new Callback<BaseResponse<List<ProductItem>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<ProductItem>>> call, Response<BaseResponse<List<ProductItem>>> response) {
                searchedData.setValue(response.body().data);
            }

            @Override
            public void onFailure(Call<BaseResponse<List<ProductItem>>> call, Throwable t) {
                searchedData.setValue(null);
                Log.e("onFailure","SEARCH API CALL FAIL",t);
            }
        });


        return searchedData;
    }



}
