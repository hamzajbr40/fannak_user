package com.hamzajbr.fannak_user.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.hamzajbr.fannak_user.R;

public class ContactUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
    }
    public void back(View view) {
        onBackPressed();
        finish();
    }

    public void dial(View view) {
        Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+getResources().getString(R.string.customer_service_num)));
        startActivity(i);
    }
}
