package com.hamzajbr.fannak_user.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.hamzajbr.fannak_user.models.CurrentOrderItem;
import com.hamzajbr.fannak_user.models.ProductItem;
import com.hamzajbr.fannak_user.repositories.OrdersRepository;

import java.util.List;

public class PreviousOrderViewModel extends ViewModel {

    private LiveData<List<ProductItem>> previousOrders;
    private OrdersRepository ordersRepository;

    public void init(int id){
        if (previousOrders != null){
            return;
        }
        ordersRepository = OrdersRepository.getInstance();
        previousOrders = ordersRepository.getPreviousOrders(id);
    }
    public LiveData<List<ProductItem>> getPreviousOrders() {
        return previousOrders;
    }

}
