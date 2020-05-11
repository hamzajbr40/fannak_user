package com.hamzajbr.fannak_user.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hamzajbr.fannak_user.R;
import com.hamzajbr.fannak_user.models.ProductItem;

import com.hamzajbr.fannak_user.utilities.Utils;
import com.hamzajbr.fannak_user.viewmodels.AddOrderViewModel;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductActivity extends AppCompatActivity {

    private int itemId;
    @BindView(R.id.product_name_tv)
    TextView productName;
    @BindView(R.id.seller_name_tv)
    TextView sellerName;
    @BindView(R.id.price_tv)
    TextView productPrice;
    @BindView(R.id.description_tv)
    TextView productDescription;
    @BindView(R.id.category_tv)
    TextView categoryTv;
    @BindView(R.id.product_img)
    ImageView productImg;

    private AddOrderViewModel addOrderViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ButterKnife.bind(this);
        ProductItem item = (ProductItem) Objects.requireNonNull(getIntent().getExtras()).get("product");
        itemId = Objects.requireNonNull(item).itemID;
        addOrderViewModel = ViewModelProviders.of(this).get(AddOrderViewModel.class);




        initData(item);


    }


    @OnClick(R.id.back_btn)
    public void back(){
        onBackPressed();
    }

    @SuppressLint("SetTextI18n")
    private void initData(ProductItem item){
        if (item != null) {
            productName.setText(item.name);
            sellerName.setText(item.sellerName);
            productPrice.setText(item.price + " JOD");
            productDescription.setText(item.description);
            categoryTv.setText(item.type);
            byte[] imageByteArray = Base64.decode(item.image, Base64.DEFAULT);
            Glide.with(this).load(imageByteArray).into(productImg);
        }

    }

    @OnClick(R.id.order_btn)
    void addOrder(){
        boolean signedIn = Utils.getValue(this,"signed in",true);
        if (signedIn){
            int buyerId = Utils.getValue(this,"id",0);
            addOrderViewModel.init(itemId,buyerId);
            addOrderViewModel.getResponse().observe(this, basicResponse -> {
                if (basicResponse.executionSuccessful) {
                    Toast.makeText(ProductActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ProductActivity.this, basicResponse.message, Toast.LENGTH_SHORT).show();

                }
            });


        }else {
            Toast.makeText(this,"You must login to Order this product",Toast.LENGTH_SHORT).show();

        }


    }



}
