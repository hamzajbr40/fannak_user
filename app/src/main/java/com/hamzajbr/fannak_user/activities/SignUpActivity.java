package com.hamzajbr.fannak_user.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.hamzajbr.fannak_user.R;
import com.hamzajbr.fannak_user.models.requests.SignupRequest;
import com.hamzajbr.fannak_user.models.responses.BaseResponse;
import com.hamzajbr.fannak_user.models.responses.BasicResponse;
import com.hamzajbr.fannak_user.utilities.Utils;
import com.hamzajbr.fannak_user.viewmodels.SignUpViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.hamzajbr.fannak_user.utilities.Utils.isEmpty;

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.email_et)
    EditText emailEt;
    @BindView(R.id.name_et)
    EditText nameEt;
    @BindView(R.id.password_et)
    EditText passwordEt;
    @BindView(R.id.confirm_pass_et)
    EditText confirmPassEt;
    @BindView(R.id.email_card)
    MaterialCardView emailCard;
    @BindView(R.id.name_card)
    MaterialCardView nameCard;
    @BindView(R.id.password_card)
    MaterialCardView passwordCard;
    @BindView(R.id.confirm_pass_card)
    MaterialCardView confirmPassCard;

    private SignUpViewModel signUpViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        signUpViewModel = ViewModelProviders.of(this).get(SignUpViewModel.class);




        //for card stroke color indicator
        emailEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                emailCard.setStrokeColor(getResources().getColor(R.color.black));
                nameCard.setStrokeColor(getResources().getColor(R.color.card_default_color));
                passwordCard.setStrokeColor(getResources().getColor(R.color.card_default_color));
                confirmPassCard.setStrokeColor(getResources().getColor(R.color.card_default_color));


            }
        });

        //for card stroke color indicator
        nameEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                nameCard.setStrokeColor(getResources().getColor(R.color.black));
                emailCard.setStrokeColor(getResources().getColor(R.color.card_default_color));
                passwordCard.setStrokeColor(getResources().getColor(R.color.card_default_color));
                confirmPassCard.setStrokeColor(getResources().getColor(R.color.card_default_color));

            }
        });

        //for card stroke color indicator
        passwordEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                passwordCard.setStrokeColor(getResources().getColor(R.color.black));
                nameCard.setStrokeColor(getResources().getColor(R.color.card_default_color));
                emailCard.setStrokeColor(getResources().getColor(R.color.card_default_color));
                confirmPassCard.setStrokeColor(getResources().getColor(R.color.card_default_color));

            }
        });

        //for card stroke color indicator
        confirmPassEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                emailCard.setStrokeColor(getResources().getColor(R.color.card_default_color));
                nameCard.setStrokeColor(getResources().getColor(R.color.card_default_color));
                passwordCard.setStrokeColor(getResources().getColor(R.color.card_default_color));
                confirmPassCard.setStrokeColor(getResources().getColor(R.color.black));

            }
        });

    }

    private boolean isDataAreValid(){
        boolean v = true;
        String pass = passwordEt.getText().toString();
        String cPass = confirmPassEt.getText().toString();
        if(Utils.isEmail(emailEt)==false){
            emailEt.setError("Enter valid email!");
            emailCard.setStrokeColor(getResources().getColor(R.color.error));
            v = false;
        }
        if (isEmpty(nameEt)){
            nameEt.setError("Your name is required!");
            nameCard.setStrokeColor(getResources().getColor(R.color.error));
            v = false;
        }
        if (isEmpty(passwordEt)){
            passwordEt.setError("Enter your password");
            passwordCard.setStrokeColor(getResources().getColor(R.color.error));
            v = false;
        }
        else if(!pass.equals(cPass)) {
            confirmPassEt.setError("Password not matching");
            confirmPassCard.setStrokeColor(getResources().getColor(R.color.error));
            v = false;
        }

        return v;
    }
    private SignupRequest createSignUpRequest(){
        SignupRequest request = new SignupRequest();
        request.name = nameEt.getText().toString();
        request.email = emailEt.getText().toString();
        request.password = passwordEt.getText().toString();
        return request;
    }


    @OnClick(R.id.sign_up_btn)
    void signUp(){

        if(isDataAreValid()){

            signUpViewModel.init(createSignUpRequest());
            signUpViewModel.getResponse().observe(this, baseResponse -> {
                if(baseResponse.executionSuccessful){
                    Utils.goToActivity(SignUpActivity.this,LoginActivity.class,true);
                }
                else {
                    Toast.makeText(SignUpActivity.this,baseResponse.message,Toast.LENGTH_SHORT).show();
                }

            });



        }
    }


}
