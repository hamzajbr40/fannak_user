package com.hamzajbr.fannak_user.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.hamzajbr.fannak_user.models.ProductItem;
import com.hamzajbr.fannak_user.models.requests.SearchByCategoryRequest;
import com.hamzajbr.fannak_user.models.requests.SearchByNameRequest;
import com.hamzajbr.fannak_user.models.requests.SearchByTypeRequest;
import com.hamzajbr.fannak_user.repositories.SearchRepository;

import java.util.List;

public class SearchViewModel extends ViewModel {
    private LiveData<List<ProductItem>> searchedResult;
    private SearchRepository searchRepository;
    //for returns All Products
    public void init() {

        searchRepository = SearchRepository.getInstance();
        searchedResult = searchRepository.getAllProducts();
    }
    //By category
    public void init(SearchByCategoryRequest request) {

        searchRepository = SearchRepository.getInstance();
        searchedResult = searchRepository.search(request);
    }
    //By type
    public void init(SearchByTypeRequest request) {

        searchRepository = SearchRepository.getInstance();
        searchedResult = searchRepository.search(request);
    }
    //By name
    public void init(SearchByNameRequest request) {

        searchRepository = SearchRepository.getInstance();
        searchedResult = searchRepository.search(request);
    }
    public LiveData<List<ProductItem>> getSearchedResult(){
        return searchedResult;
    }


}
