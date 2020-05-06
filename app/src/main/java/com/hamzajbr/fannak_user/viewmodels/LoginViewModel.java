package com.hamzajbr.fannak_user.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.hamzajbr.fannak_user.models.requests.LoginRequest;
import com.hamzajbr.fannak_user.models.responses.BaseResponse;
import com.hamzajbr.fannak_user.models.responses.LoginResponse;
import com.hamzajbr.fannak_user.repositories.LoginRepository;

public class LoginViewModel extends ViewModel {
    private LiveData<BaseResponse<LoginResponse>> response;
    private LoginRepository loginRepository;

    public void init(LoginRequest request){
        if (response!= null){
            return;
        }
        loginRepository = LoginRepository.getInstance();
        if (innerValidation(request)){
            response = loginRepository.login(request);
            Log.i("validate","Login request body is valid");
        }

    }

    public LiveData<BaseResponse<LoginResponse>> getResponse(){
        return response;
    }

    private boolean innerValidation(LoginRequest request){

        if (request.email.isEmpty()){
            return false;
        }
        if (request.password.isEmpty()){
            return false;
        }

        return true;
    }

}
