package com.hamzajbr.fannak_user.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hamzajbr.fannak_user.managers.RestClient;
import com.hamzajbr.fannak_user.models.requests.SignupRequest;
import com.hamzajbr.fannak_user.models.responses.BaseResponse;
import com.hamzajbr.fannak_user.models.responses.BasicResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpRepository {
    private static SignUpRepository signUpRepository;

    public static SignUpRepository getInstance(){
        if (signUpRepository==null){
            signUpRepository = new SignUpRepository();
        }
        return signUpRepository;
    }
    public LiveData<BasicResponse> signUp(SignupRequest request){
        MutableLiveData<BasicResponse> responseData = new MutableLiveData<>();
        BasicResponse baseResponse = new BasicResponse();

        Call<BasicResponse> call = RestClient.getService().signup(request);
        call.enqueue(new Callback<BasicResponse>() {
            @Override
            public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                if (response.isSuccessful()){
                    baseResponse.message = response.body().message;
                    baseResponse.executionSuccessful = response.body().executionSuccessful;
                    baseResponse.code = response.body().code;
                }
            }

            @Override
            public void onFailure(Call<BasicResponse> call, Throwable t) {
                Log.e("onFailure","SIGN UP API CALL FAIL",t);
            }
        });
        responseData.setValue(baseResponse);

        return responseData;
    }

}
