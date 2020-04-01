package com.hamzajbr.fannak_user.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hamzajbr.fannak_user.R;
import com.hamzajbr.fannak_user.adapters.BannersAdapter;
import com.hamzajbr.fannak_user.models.BannerItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private Unbinder unbinder;
    @BindView(R.id.banner_rv)
    RecyclerView bannerRv;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this,view);

        initBannerRecycler(bannerRv,initBannerArraylist());

        return view;
    }

    void initBannerRecycler(RecyclerView bannerRv, ArrayList<BannerItem> myList){
        BannersAdapter adapter = new BannersAdapter(getContext(),myList,item -> {
        });
        SnapHelper snapHelper = new LinearSnapHelper();

        bannerRv.setAdapter(adapter);
        bannerRv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        snapHelper.attachToRecyclerView(bannerRv);
    }

    ArrayList<BannerItem> initBannerArraylist(){
        ArrayList<BannerItem> bannerItems = new ArrayList<>();
        BannerItem item;
        item = new BannerItem();
        item.bannerImg = R.drawable.banner1;
        bannerItems.add(item);

        item = new BannerItem();
        item.bannerImg = R.drawable.banner2;
        bannerItems.add(item);

        return bannerItems;
    }
}
