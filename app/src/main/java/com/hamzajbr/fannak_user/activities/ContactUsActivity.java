package com.hamzajbr.fannak_user.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


import com.hamzajbr.fannak_user.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.back_btn)
    void back() {
        onBackPressed();
        finish();
    }

    @OnClick(R.id.call_btn)
    void dial() {
        Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+getResources().getString(R.string.customer_service_num)));
        startActivity(i);
    }
}
