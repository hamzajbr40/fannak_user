package com.hamzajbr.fannak_user.fragments;

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
import com.hamzajbr.fannak_user.adapters.SubCategoryAdapter;
import com.hamzajbr.fannak_user.models.CategoryItem;
import com.hamzajbr.fannak_user.models.SubCategoryItem;
import com.hamzajbr.fannak_user.viewmodels.CategoriesViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HandCraftsFragment extends Fragment {

    @BindView(R.id.handcrafts_sub_category_rv)
    RecyclerView handcraftsRv;

    public HandCraftsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_hand_crafts, container, false);
        ButterKnife.bind(this,v);

        initHandcraftsRecycler();

        return v;
    }
    void initHandcraftsRecycler(){
        SubCategoryAdapter adapter = new SubCategoryAdapter(getContext(), initHandcraftsArrayLIst(), new SubCategoryAdapter.ISubCategory() {
            @Override
            public void onClick(SubCategoryItem item) {

            }
        });
        handcraftsRv.setAdapter(adapter);
        handcraftsRv.setLayoutManager(new GridLayoutManager(getContext(),2));
        adapter.notifyDataSetChanged();
    }
    ArrayList<SubCategoryItem> initHandcraftsArrayLIst(){
        ArrayList<SubCategoryItem> list = new ArrayList<>();
        CategoriesViewModel categoriesViewModel = ViewModelProviders.of(getActivity()).get(CategoriesViewModel.class);
        categoriesViewModel.init();
        categoriesViewModel.getAllCategories().observe(getViewLifecycleOwner(), new Observer<List<CategoryItem>>() {
            @Override
            public void onChanged(List<CategoryItem> categoryItems) {
                list.addAll(categoryItems.get(1).subCategoryList);
            }
        });
        return list;
    }


}
