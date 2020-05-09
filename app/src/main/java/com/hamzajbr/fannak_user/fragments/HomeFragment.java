package com.hamzajbr.fannak_user.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hamzajbr.fannak_user.R;
import com.hamzajbr.fannak_user.activities.AllProductsActivity;
import com.hamzajbr.fannak_user.activities.CategoryActivity;
import com.hamzajbr.fannak_user.activities.ProductActivity;
import com.hamzajbr.fannak_user.adapters.BannersAdapter;
import com.hamzajbr.fannak_user.adapters.ProductsAdapter;
import com.hamzajbr.fannak_user.models.BannerItem;
import com.hamzajbr.fannak_user.models.ProductItem;
import com.hamzajbr.fannak_user.viewmodels.BannersViewModel;
import com.hamzajbr.fannak_user.viewmodels.FeaturedViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    BannersAdapter bannersAdapter;
    ProductsAdapter productsAdapter;

    BannersViewModel bannersViewModel;
    FeaturedViewModel featuredViewModel;

    private Unbinder unbinder;
    @BindView(R.id.banner_rv)
    RecyclerView bannerRv;

    @BindView(R.id.featured_items_rv)
    RecyclerView featuredRv;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this,view);

        bannersViewModel = ViewModelProviders.of(getActivity()).get(BannersViewModel.class);
        bannersViewModel.init();
        bannersViewModel.getBanners().observe(getViewLifecycleOwner(), bannerItems -> {
            if(bannerItems!= null){
                bannersAdapter.setBannersList((ArrayList<BannerItem>) bannerItems);
            }
        });

        featuredViewModel = ViewModelProviders.of(getActivity()).get(FeaturedViewModel.class);
        featuredViewModel.init();
        featuredViewModel.getFeaturedProducts().observe(getViewLifecycleOwner(), productItems -> {
            if(productItems!= null){
                productsAdapter.setProducts((ArrayList<ProductItem>) productItems);
            }

        });



        initBannerRecycler(bannerRv);
        initFeaturedRecycler(featuredRv);

        return view;
    }

    @OnClick({R.id.arts_category_card,R.id.handcrafts_category_card})
    void category(View card){
        Intent i = new Intent(getActivity(), CategoryActivity.class);
        i.putExtra("cardId",card.getId());
        startActivity(i);

    }


    private void initBannerRecycler(RecyclerView bannerRv){
        bannersAdapter = new BannersAdapter(getContext(),item -> {
        });
        SnapHelper snapHelper = new LinearSnapHelper();

        bannerRv.setAdapter(bannersAdapter);
        bannerRv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        snapHelper.attachToRecyclerView(bannerRv);
        bannersAdapter.notifyDataSetChanged();
    }


    private void initFeaturedRecycler(RecyclerView FeaturedRv){
        productsAdapter = new ProductsAdapter(getContext(), item -> {
            Intent i = new Intent(getActivity(), ProductActivity.class);
            i.putExtra("product",item);
            startActivity(i);
        });
        FeaturedRv.setAdapter(productsAdapter);
        FeaturedRv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        productsAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.search_btn,R.id.all_items_btn})
    void search(){
        Intent i = new Intent(getActivity(), AllProductsActivity.class);
        i.putExtra("search",0);
        startActivity(i);

    }

}
