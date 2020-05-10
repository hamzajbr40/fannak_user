package com.hamzajbr.fannak_user.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.hamzajbr.fannak_user.R;
import com.hamzajbr.fannak_user.models.requests.LoginRequest;
import com.hamzajbr.fannak_user.models.responses.BaseResponse;
import com.hamzajbr.fannak_user.models.responses.LoginResponse;
import com.hamzajbr.fannak_user.utilities.Utils;
import com.hamzajbr.fannak_user.viewmodels.LoginViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.hamzajbr.fannak_user.utilities.Utils.isEmpty;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.email_et)
    EditText emailEt;
    @BindView(R.id.password_et)
    EditText passwordEt;
    @BindView(R.id.email_card)
    MaterialCardView emailCard;
    @BindView(R.id.password_card)
    MaterialCardView passwordCard;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);


        //for card stroke color indicator
        emailEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                emailCard.setStrokeColor(getResources().getColor(R.color.black));
                passwordCard.setStrokeColor(getResources().getColor(R.color.card_default_color));
            }
        });

        //for card stroke color indicator
        passwordEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                passwordCard.setStrokeColor(getResources().getColor(R.color.black));
                emailCard.setStrokeColor(getResources().getColor(R.color.card_default_color));
            }
        });

    }

    @OnClick(R.id.sign_in_btn)
    void signIn() {
        if (isDataAreValid()) {

            loginViewModel.init(createLoginRequest());
            loginViewModel.getResponse().observe(this, new Observer<BaseResponse<LoginResponse>>() {
                @Override
                public void onChanged(BaseResponse<LoginResponse> loginResponseBaseResponse) {
                    int id = 10;
                    if(loginResponseBaseResponse.executionSuccessful) {

                        if(loginResponseBaseResponse.data!=null) {
                            String name = loginResponseBaseResponse.data.name;
                            //id = loginResponseBaseResponse.data.id;
                            Utils.setValue(LoginActivity.this, "email", emailEt.getText().toString());
                            Utils.setValue(LoginActivity.this, "name", name);
                            Utils.setValue(LoginActivity.this, "userId", id);
                            Log.i("id","id = "+loginResponseBaseResponse.data.id);
                            Utils.setValue(LoginActivity.this, "signed in", true);
                            Utils.goToActivity(LoginActivity.this, MainActivity.class, true);
                        }
                    }
                }
            });
        }
    }

    private LoginRequest createLoginRequest() {
        LoginRequest request = new LoginRequest();
        request.email = emailEt.getText().toString();
        request.password = passwordEt.getText().toString();
        return request;
    }

    private boolean isDataAreValid() {
        boolean v = true;
        if (!Utils.isEmail(emailEt)) {
            emailEt.setError("Enter your email!");
            emailCard.setStrokeColor(getResources().getColor(R.color.error));
            v = false;
        }
        if (isEmpty(passwordEt)) {
            passwordEt.setError("Enter your password");
            passwordCard.setStrokeColor(getResources().getColor(R.color.error));
            v = false;
        }

        return v;
    }


    @OnClick(R.id.sign_up_btn)
    void signUp() {
        Utils.goToActivity(this, SignUpActivity.class, false);
    }

    @OnClick(R.id.guest_btn)
    void guest() {
        Utils.setValue(LoginActivity.this, "name", "Guest");

        Utils.setValue(this, "signed in", false);
        Utils.goToActivity(this, MainActivity.class, false);
    }

}
