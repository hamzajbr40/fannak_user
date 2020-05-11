package com.hamzajbr.fannak_user.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hamzajbr.fannak_user.R;
import com.hamzajbr.fannak_user.activities.AllProductsActivity;
import com.hamzajbr.fannak_user.activities.ProductActivity;
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

    private Unbinder unbinder;
    @BindView(R.id.prev_order_rv)
    RecyclerView prevOrdersRv;

    private ProductsAdapter adapter;

    private PreviousOrderViewModel previousOrderViewModel;



    public PrevOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_prev_orders, container, false);
        unbinder = ButterKnife.bind(this,view);
        initPrevOrderRecycler();


        previousOrderViewModel = ViewModelProviders.of(this).get(PreviousOrderViewModel.class);
        int id = Utils.getValue(getContext(), "userId", 10);
        Log.i("id", "id = " + id);
        previousOrderViewModel.init(id);
        previousOrderViewModel.getPreviousOrders().observe(getViewLifecycleOwner(), new Observer<List<ProductItem>>() {
            @Override
            public void onChanged(List<ProductItem> productItems) {
                if (productItems != null) {
                    adapter.setProducts((ArrayList<ProductItem>) productItems);
                    adapter.notifyDataSetChanged();
                }
            }

        });



        return view;
    }
    private void initPrevOrderRecycler(){
        adapter = new ProductsAdapter(getContext(), item -> {
            Intent i = new Intent(getActivity(), ProductActivity.class);
            i.putExtra("product", item);
            startActivity(i);
        });
        prevOrdersRv.setAdapter(adapter);
        prevOrdersRv.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        adapter.notifyDataSetChanged();
    }


}
