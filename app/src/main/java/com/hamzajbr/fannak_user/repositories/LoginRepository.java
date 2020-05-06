package com.hamzajbr.fannak_user.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hamzajbr.fannak_user.managers.RestClient;
import com.hamzajbr.fannak_user.models.requests.LoginRequest;
import com.hamzajbr.fannak_user.models.responses.BaseResponse;
import com.hamzajbr.fannak_user.models.responses.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {
    private static LoginRepository loginRepository;

    public static LoginRepository getInstance(){
        if (loginRepository==null){
            loginRepository = new LoginRepository();
        }
        return loginRepository;
    }
    public LiveData<BaseResponse<LoginResponse>> login(LoginRequest request){
        MutableLiveData<BaseResponse<LoginResponse>> responseData = new MutableLiveData<>();
        BaseResponse baseResponse = new BaseResponse();

        Call<BaseResponse<LoginResponse>> call = RestClient.getService().login(request);
        call.enqueue(new Callback<BaseResponse<LoginResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<LoginResponse>> call, Response<BaseResponse<LoginResponse>> response) {
                if (response.isSuccessful()){
                    baseResponse.message = response.body().message;
                    baseResponse.executionSuccessful = response.body().executionSuccessful;
                    baseResponse.code = response.body().code;
                    baseResponse.data = response.body().data;

                }
            }

            @Override
            public void onFailure(Call<BaseResponse<LoginResponse>> call, Throwable t) {
                Log.e("onFailure","LOGIN API CALL FAIL",t);
            }
        });
        responseData.setValue(baseResponse);

        return responseData;
    }
}
