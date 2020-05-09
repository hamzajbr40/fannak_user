package com.hamzajbr.fannak_user.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.hamzajbr.fannak_user.R;
import com.hamzajbr.fannak_user.adapters.ProductsAdapter;
import com.hamzajbr.fannak_user.models.ProductItem;
import com.hamzajbr.fannak_user.models.requests.SearchByCategoryRequest;
import com.hamzajbr.fannak_user.models.requests.SearchByTypeRequest;
import com.hamzajbr.fannak_user.viewmodels.SearchViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllProductsActivity extends AppCompatActivity {
    @BindView(R.id.products_rv)
    RecyclerView productsRv;
    @BindView(R.id.search_et)
    EditText searchEt;
    SearchViewModel searchViewModel;

    ProductsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);
        ButterKnife.bind(this);

        int searchCall = getIntent().getIntExtra("search",0);

        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);


        switch (searchCall){
            case 0:
                searchViewModel.init();
                break;
            case 1:
                String category = getIntent().getStringExtra("category");
                SearchByCategoryRequest request = new SearchByCategoryRequest();
                request.category = category;
                searchViewModel.init(request);
                break;
            case 2:
                String type = getIntent().getStringExtra("type");
                SearchByTypeRequest typeRequest = new SearchByTypeRequest();
                typeRequest.type = type;
                searchViewModel.init(typeRequest);
                break;
        }

        adapter = new ProductsAdapter(this, new ProductsAdapter.IProducts() {
            @Override
            public void onClick(ProductItem item) {
                Intent i = new Intent(AllProductsActivity.this, ProductActivity.class);
                i.putExtra("product",item);
                startActivity(i);
            }
        });
        productsRv.setAdapter(adapter);
        productsRv.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));



        searchViewModel.getSearchedResult().observe(this, new Observer<List<ProductItem>>() {
            @Override
            public void onChanged(List<ProductItem> productItems) {
                if(productItems != null){
                    adapter.setProducts((ArrayList<ProductItem>) productItems);
                }
            }
        });
        adapter.notifyDataSetChanged();




        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {
                adapter.notifyDataSetChanged();
            }
        });


    }





}
