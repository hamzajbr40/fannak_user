package com.hamzajbr.fannak_user.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.hamzajbr.fannak_user.models.CurrentOrderItem;
import com.hamzajbr.fannak_user.repositories.OrdersRepository;

import java.util.List;

public class CurrentOrderViewModel extends ViewModel {
    private LiveData<List<CurrentOrderItem>> currentOrders;
    private OrdersRepository ordersRepository;

    public void init(int id){
        if (currentOrders != null){
            return;
        }
        ordersRepository = OrdersRepository.getInstance();
        currentOrders = ordersRepository.getCurrentOrders(id);
    }
    public LiveData<List<CurrentOrderItem>> getCurrentOrders(){
        return currentOrders;
    }

}
