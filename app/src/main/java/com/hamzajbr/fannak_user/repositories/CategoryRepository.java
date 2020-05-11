package com.hamzajbr.fannak_user.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hamzajbr.fannak_user.managers.RestClient;
import com.hamzajbr.fannak_user.models.CategoryItem;

import com.hamzajbr.fannak_user.models.responses.BaseResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRepository {
    private static CategoryRepository categoryRepository;
    public static CategoryRepository getInstance(){
        if(categoryRepository == null){
            categoryRepository = new CategoryRepository();
        }
        return categoryRepository;
    }

    public LiveData<List<CategoryItem>> getAllCategories(){
        MutableLiveData<List<CategoryItem>> categoriesData = new MutableLiveData<>();
        Call<BaseResponse<List<CategoryItem>>> call = RestClient.getService().getCategories();
        call.enqueue(new Callback<BaseResponse<List<CategoryItem>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<CategoryItem>>> call, Response<BaseResponse<List<CategoryItem>>> response) {
                if (response.isSuccessful()){
                    assert response.body() != null;
                    categoriesData.setValue(response.body().data);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<CategoryItem>>> call, Throwable t) {
                categoriesData.setValue(null);
                Log.e("onFailure","GET CATEGORIES API CALL FAIL",t);
            }
        });
        return categoriesData;
    }

}
