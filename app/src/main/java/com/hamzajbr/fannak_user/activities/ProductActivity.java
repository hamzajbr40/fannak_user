package com.hamzajbr.fannak_user.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hamzajbr.fannak_user.R;
import com.hamzajbr.fannak_user.models.ProductItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductActivity extends AppCompatActivity {

    private int id;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ButterKnife.bind(this);
        ProductItem item = (ProductItem) getIntent().getExtras().get("product");
        id = item.itemID;

        initData(item);


    }
    @OnClick(R.id.back_btn)
    public void back(View view){
        onBackPressed();
    }
    private void initData(ProductItem item){
        productName.setText(item.name);
        sellerName.setText(item.sellerName);
        productPrice.setText(item.price+" JOD");
        productDescription.setText(item.description);
        categoryTv.setText(item.type);
        byte[] imageByteArray = Base64.decode(item.image,Base64.DEFAULT);
        Glide.with(this).load(imageByteArray).into(productImg);

    }

}
