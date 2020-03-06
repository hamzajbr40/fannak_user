package com.hamzajbr.fannak_user.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.google.android.material.button.MaterialButton;
import com.hamzajbr.fannak_user.R;
import com.hamzajbr.fannak_user.utilities.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LanguageActivity extends AppCompatActivity {
    @BindView(R.id.next_btn)
    MaterialButton nextBtn;
    @BindView(R.id.arabic_rb)
    RadioButton arabicRb;
    @BindView(R.id.english_rb)
    RadioButton englishRb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.next_btn)
    void next(View v){
        //TODO check and set selected lang here

        Utils.goToActivity(this,LoginActivity.class,false);

    }
}
