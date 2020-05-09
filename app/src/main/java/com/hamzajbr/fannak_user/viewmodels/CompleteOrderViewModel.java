package com.hamzajbr.fannak_user.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.hamzajbr.fannak_user.models.responses.BasicResponse;
import com.hamzajbr.fannak_user.repositories.CompleteOrderRepository;

public class CompleteOrderViewModel extends ViewModel {


    private LiveData<BasicResponse> response;
    private CompleteOrderRepository completeOrderRepository;

    public void init(int orderId){
        if (response!=null)
            return;
        completeOrderRepository = CompleteOrderRepository.getInstance();
        response = completeOrderRepository.completeOrder(orderId);
    }
    public LiveData<BasicResponse> getResponse() {
        return response;
    }

}
