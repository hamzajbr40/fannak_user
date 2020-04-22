package com.hamzajbr.fannak_user.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
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
import com.hamzajbr.fannak_user.adapters.BannersAdapter;
import com.hamzajbr.fannak_user.models.BannerItem;
import com.hamzajbr.fannak_user.viewmodels.BannersViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    private void initBannerRecycler(RecyclerView bannerRv, ArrayList<BannerItem> myList){
        BannersAdapter adapter = new BannersAdapter(getContext(),myList,item -> {
        });
        SnapHelper snapHelper = new LinearSnapHelper();

        bannerRv.setAdapter(adapter);
        bannerRv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        snapHelper.attachToRecyclerView(bannerRv);
        adapter.notifyDataSetChanged();
    }

    private ArrayList<BannerItem> initBannerArraylist(){
        ArrayList<BannerItem> list = new ArrayList<>();
        BannersViewModel bannersViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(BannersViewModel.class);
        bannersViewModel.init();
        bannersViewModel.getBanners().observe(getViewLifecycleOwner(), bannerItems -> {
            list.addAll(bannerItems);
        });

        return list;
    }
}
