package com.hamzajbr.fannak_user.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.hamzajbr.fannak_user.R;
import com.hamzajbr.fannak_user.utilities.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.sign_up_btn)
    MaterialButton signUpBtn;
    @BindView(R.id.email_et)
    EditText emailEt;
    @BindView(R.id.password_et)
    EditText passwordEt;
    @BindView(R.id.email_card)
    MaterialCardView emailCard;
    @BindView(R.id.password_card)
    MaterialCardView passwordCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


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
    @OnClick(R.id.sign_up_btn)
    void signUp(View view){
        Utils.goToActivity(this,SignUpActivity.class,false);
    }

    @OnClick(R.id.guest_btn)
    void guest(View view){
        Utils.goToActivity(this,MainActivity.class,false);
    }

}
