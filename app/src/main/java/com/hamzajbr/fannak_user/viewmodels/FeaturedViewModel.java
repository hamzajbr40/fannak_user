package com.hamzajbr.fannak_user.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.hamzajbr.fannak_user.models.ProductItem;
import com.hamzajbr.fannak_user.repositories.FeaturedRepository;

import java.util.List;

public class FeaturedViewModel extends ViewModel {
    private LiveData<List<ProductItem>> featuredProducts;
    private FeaturedRepository featuredRepository;

    public void init(){
        if (featuredProducts!= null){
            return;
        }
        featuredRepository = FeaturedRepository.getInstance();
        featuredProducts = featuredRepository.getFeatured();
    }
    public LiveData<List<ProductItem>> getFeaturedProducts(){
        return featuredProducts;
    }
}
