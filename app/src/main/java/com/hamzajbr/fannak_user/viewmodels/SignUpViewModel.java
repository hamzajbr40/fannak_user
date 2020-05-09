package com.hamzajbr.fannak_user.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.hamzajbr.fannak_user.models.requests.SignupRequest;
import com.hamzajbr.fannak_user.models.responses.BaseResponse;
import com.hamzajbr.fannak_user.models.responses.BasicResponse;
import com.hamzajbr.fannak_user.repositories.SignUpRepository;

public class SignUpViewModel extends ViewModel {
    private LiveData<BasicResponse> response;
    private SignUpRepository signUpRepository;


    public void init(SignupRequest request){
        if (response!= null)
            return;
        signUpRepository = SignUpRepository.getInstance();
        if (innerValidation(request)){
            response = signUpRepository.signUp(request);
            Log.i("validate","SignUp request body is valid");
        }
    }

    public LiveData<BasicResponse> getResponse(){
        return response;
    }


    private boolean innerValidation(SignupRequest request){
        if (request.email.isEmpty()){
            return false;
        }
        if (request.password.isEmpty()){
            return false;
        }
        if(request.name.isEmpty()){
            return false;
        }
        return true;
    }
}
