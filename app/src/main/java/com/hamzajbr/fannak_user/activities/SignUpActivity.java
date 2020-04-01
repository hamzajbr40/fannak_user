package com.hamzajbr.fannak_user.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.card.MaterialCardView;
import com.hamzajbr.fannak_user.R;

import butterknife.BindView;
import butterknife.ButterKnife;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);


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
}
