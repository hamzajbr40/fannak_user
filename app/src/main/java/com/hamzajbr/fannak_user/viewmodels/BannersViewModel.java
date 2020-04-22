package com.hamzajbr.fannak_user.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hamzajbr.fannak_user.models.BannerItem;
import com.hamzajbr.fannak_user.repositories.BannersRepository;

import java.util.List;

public class BannersViewModel extends ViewModel {
    private LiveData<List<BannerItem>> banners;
    private BannersRepository bannersRepository;

    public void init(){
        if (banners != null){
            return;
        }
        bannersRepository = BannersRepository.getInstance();
        banners = bannersRepository.getBanners();
    }
    public LiveData<List<BannerItem>> getBanners(){
        return banners;
    }

}
