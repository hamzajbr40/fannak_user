package com.hamzajbr.fannak_user.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.hamzajbr.fannak_user.models.responses.BasicResponse;
import com.hamzajbr.fannak_user.repositories.AddOrderRepository;

public class AddOrderViewModel extends ViewModel {

    private LiveData<BasicResponse> response;
    private AddOrderRepository addOrderRepository;

    public void init(int itemId,int itemBuyer){
        if (response!=null)
            return;
        addOrderRepository = AddOrderRepository.getInstance();
        response = addOrderRepository.addOrder(itemId,itemBuyer);


    }
    public LiveData<BasicResponse> getResponse() {
        return response;
    }

}
