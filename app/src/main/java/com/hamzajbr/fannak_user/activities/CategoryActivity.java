package com.hamzajbr.fannak_user.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.card.MaterialCardView;
import com.hamzajbr.fannak_user.R;
import com.hamzajbr.fannak_user.fragments.ArtsFragment;
import com.hamzajbr.fannak_user.fragments.HandCraftsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryActivity extends AppCompatActivity {

    private ArtsFragment artsFragment = new ArtsFragment();
    private HandCraftsFragment handCraftsFragment = new HandCraftsFragment();

    @BindView(R.id.arts_category_card)
    MaterialCardView artsCard;
    @BindView(R.id.handcrafts_category_card)
    MaterialCardView handcraftsCard;

    @BindView(R.id.arts_checked_img)
    ImageView artsCheck;
    @BindView(R.id.handcrafts_checked_img)
    ImageView handcraftsCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        int i = getIntent().getIntExtra("cardId",R.id.arts_category_card);
        if(i==R.id.handcrafts_category_card){
            tabs(handcraftsCard);
        }
        else {
            tabs(artsCard);
        }







    }
    @OnClick(R.id.back_btn)
    void back(){onBackPressed(); finish();}

    @OnClick({R.id.handcrafts_category_card,R.id.arts_category_card})
    void tabs(View card){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(card.getId() == R.id.arts_category_card){
            artsCard.setStrokeColor(getResources().getColor(R.color.colorPrimary));
            artsCheck.setVisibility(View.VISIBLE);
            handcraftsCard.setStrokeColor(getResources().getColor(R.color.transparent));
            handcraftsCheck.setVisibility(View.GONE);

            //load fragment
            fragmentTransaction.replace(R.id.sub_category_container,artsFragment,"Arts");
            fragmentTransaction.addToBackStack("Arts");

        }
        else{
            handcraftsCard.setStrokeColor(getResources().getColor(R.color.colorPrimary));
            handcraftsCheck.setVisibility(View.VISIBLE);
            artsCard.setStrokeColor(getResources().getColor(R.color.transparent));
            artsCheck.setVisibility(View.GONE);

            //load fragment
            fragmentTransaction.replace(R.id.sub_category_container,handCraftsFragment,"Handcrafts");
            fragmentTransaction.addToBackStack("Handcrafts");
        }
        fragmentTransaction.commit();


    }
}
