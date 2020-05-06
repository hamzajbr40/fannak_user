package com.hamzajbr.fannak_user.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.hamzajbr.fannak_user.R;

import butterknife.OnClick;

public class AccountActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
    }

    @OnClick(R.id.back_btn)
    public void back(View v){
        finish();
    }
}
