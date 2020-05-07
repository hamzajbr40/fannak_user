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
public class ArtsFragment extends Fragment {

    @BindView(R.id.arts_sub_category_rv)
    RecyclerView artsRv;

    public ArtsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_arts, container, false);
        ButterKnife.bind(this,v);

        initArtsRecycler();

        return v;
    }

    void initArtsRecycler(){
        SubCategoryAdapter adapter = new SubCategoryAdapter(getContext(), initArtsArrayLIst(), new SubCategoryAdapter.ISubCategory() {
            @Override
            public void onClick(SubCategoryItem item) {

            }
        });
        artsRv.setAdapter(adapter);
        artsRv.setLayoutManager(new GridLayoutManager(getContext(),2));
        adapter.notifyDataSetChanged();
    }
    ArrayList<SubCategoryItem> initArtsArrayLIst(){
        ArrayList<SubCategoryItem> list = new ArrayList<>();
        CategoriesViewModel categoriesViewModel = ViewModelProviders.of(getActivity()).get(CategoriesViewModel.class);
        categoriesViewModel.init();
        categoriesViewModel.getAllCategories().observe(getViewLifecycleOwner(), new Observer<List<CategoryItem>>() {
            @Override
            public void onChanged(List<CategoryItem> categoryItems) {
                list.addAll(categoryItems.get(0).subCategoryList);
            }
        });
        return list;
    }

}
