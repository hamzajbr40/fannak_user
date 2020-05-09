package com.hamzajbr.fannak_user.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hamzajbr.fannak_user.R;
import com.hamzajbr.fannak_user.adapters.ProductsAdapter;
import com.hamzajbr.fannak_user.models.ProductItem;
import com.hamzajbr.fannak_user.utilities.Utils;
import com.hamzajbr.fannak_user.viewmodels.PreviousOrderViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrevOrdersFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.prev_order_rv)
    RecyclerView prevOrdersRv;

    ProductsAdapter adapter;

    PreviousOrderViewModel previousOrderViewModel;

    public PrevOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_prev_orders, container, false);
        unbinder = ButterKnife.bind(this,view);
        previousOrderViewModel = ViewModelProviders.of(this).get(PreviousOrderViewModel.class);
        int id = Utils.getValue(getContext(),"id",0);
        previousOrderViewModel.init(id);
        previousOrderViewModel.getPreviousOrders().observe(getViewLifecycleOwner(), new Observer<List<ProductItem>>() {
            @Override
            public void onChanged(List<ProductItem> productItems) {
                adapter.setProducts((ArrayList<ProductItem>) productItems);
                adapter.notifyDataSetChanged();
            }
        });


        return view;
    }
    private void initPrevOrderRecycler(){
        adapter = new ProductsAdapter(getContext(), new ProductsAdapter.IProducts() {
            @Override
            public void onClick(ProductItem item) {

            }
        });
        prevOrdersRv.setAdapter(adapter);
        prevOrdersRv.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        adapter.notifyDataSetChanged();
    }

}
