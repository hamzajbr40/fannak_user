package com.hamzajbr.fannak_user.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.hamzajbr.fannak_user.R;
import com.hamzajbr.fannak_user.adapters.OrdersSectionPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrdersFragment extends Fragment {

    public OrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_orders, container, false);
        OrdersSectionPagerAdapter sectionPagerAdapter = new OrdersSectionPagerAdapter(getActivity(),getChildFragmentManager());
        ViewPager viewPager = v.findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionPagerAdapter);
        viewPager.setOffscreenPageLimit(3);
        TabLayout tabs = v.findViewById(R.id.profile_tabs);
        tabs.setupWithViewPager(viewPager);

        v.setSystemUiVisibility(0);
        return v;
    }
}
