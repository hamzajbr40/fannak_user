package com.hamzajbr.fannak_user.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.TextView;

import com.hamzajbr.fannak_user.R;
import com.hamzajbr.fannak_user.utilities.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountActivity extends AppCompatActivity {
    @BindView(R.id.name_tv)
    TextView userName;
    @BindView(R.id.email_tv)
    TextView emailTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ButterKnife.bind(this);

        String name = Utils.getValue(this,"name","Guest");
        String email = Utils.getValue(this,"email","example@yourdomain.com");
        userName.setText(name);
        emailTv.setText(email);

    }

    @OnClick(R.id.back_btn)
    public void back() {
        finish();
    }
}
