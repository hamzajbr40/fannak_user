package com.hamzajbr.fannak_user.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hamzajbr.fannak_user.R;
import com.hamzajbr.fannak_user.activities.AllProductsActivity;
import com.hamzajbr.fannak_user.adapters.SubCategoryAdapter;
import com.hamzajbr.fannak_user.models.CategoryItem;
import com.hamzajbr.fannak_user.models.SubCategoryItem;
import com.hamzajbr.fannak_user.viewmodels.CategoriesViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HandCraftsFragment extends Fragment {

    @BindView(R.id.handcrafts_sub_category_rv)
    RecyclerView handcraftsRv;
    Unbinder unbinder;

    SubCategoryAdapter adapter;
    CategoriesViewModel categoriesViewModel;

    public HandCraftsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_hand_crafts, container, false);
        unbinder = ButterKnife.bind(this,v);

        initHandcraftsRecycler();
        categoriesViewModel = ViewModelProviders.of(getActivity()).get(CategoriesViewModel.class);
        categoriesViewModel.init();
        categoriesViewModel.getAllCategories().observe(getViewLifecycleOwner(), new Observer<List<CategoryItem>>() {
            @Override
            public void onChanged(List<CategoryItem> categoryItems) {
                adapter.setItems((ArrayList<SubCategoryItem>) categoryItems.get(1).subCategoryList);
            }
        });

        return v;
    }

    void initHandcraftsRecycler(){
        adapter = new SubCategoryAdapter(getContext(), item -> {
            Intent i = new Intent(getActivity(), AllProductsActivity.class);
            i.putExtra("search",2);
            i.putExtra("type",item.label);
            startActivity(i);

        });
        handcraftsRv.setAdapter(adapter);
        handcraftsRv.setLayoutManager(new GridLayoutManager(getContext(),2));
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.all_handcrafts_btn)
    void seeAllHandcraftsProducts(){
        Intent i = new Intent(getActivity(), AllProductsActivity.class);
        i.putExtra("search",1);
        i.putExtra("category","Handcrafts");
        startActivity(i);
    }



}
