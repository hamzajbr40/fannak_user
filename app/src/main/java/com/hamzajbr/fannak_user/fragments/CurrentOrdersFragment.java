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
import android.widget.Toast;

import com.hamzajbr.fannak_user.R;
import com.hamzajbr.fannak_user.adapters.CurrentOrderAdapter;
import com.hamzajbr.fannak_user.models.CurrentOrderItem;
import com.hamzajbr.fannak_user.models.ProductItem;
import com.hamzajbr.fannak_user.models.responses.BasicResponse;
import com.hamzajbr.fannak_user.utilities.Utils;
import com.hamzajbr.fannak_user.viewmodels.CompleteOrderViewModel;
import com.hamzajbr.fannak_user.viewmodels.CurrentOrderViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentOrdersFragment extends Fragment {

    @BindView(R.id.current_order_rv)
    RecyclerView currentOrdersRv;

    Unbinder unbinder;
    CurrentOrderAdapter adapter;

    CurrentOrderViewModel currentOrderViewModel;
    CompleteOrderViewModel completeOrderViewModel;

    public CurrentOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_current_orders, container, false);
        unbinder =  ButterKnife.bind(this,v);

        completeOrderViewModel = ViewModelProviders.of(this).get(CompleteOrderViewModel.class);
        currentOrderViewModel = ViewModelProviders.of(this).get(CurrentOrderViewModel.class);
        int id = Utils.getValue(getContext(),"id",0);
        currentOrderViewModel.init(id);
        currentOrderViewModel.getCurrentOrders().observe(getViewLifecycleOwner(), new Observer<List<CurrentOrderItem>>() {
            @Override
            public void onChanged(List<CurrentOrderItem> currentOrderItems) {
                adapter.setCurrentOrders((ArrayList<CurrentOrderItem>) currentOrderItems);
                adapter.notifyDataSetChanged();
            }
        });

        initCurrentOrderRecycler();
        return v;
    }

    private void initCurrentOrderRecycler(){
        adapter = new CurrentOrderAdapter(getContext(), new CurrentOrderAdapter.IComplete() {
            @Override
            public void onClick(CurrentOrderItem item) {
                completeOrderViewModel.init(item.orderId);
                completeOrderViewModel.getResponse().observe(getViewLifecycleOwner(), new Observer<BasicResponse>() {
                    @Override
                    public void onChanged(BasicResponse basicResponse) {
                        Toast.makeText(getActivity(),basicResponse.message,Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
        currentOrdersRv.setAdapter(adapter);
        currentOrdersRv.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        adapter.notifyDataSetChanged();

    }


}
