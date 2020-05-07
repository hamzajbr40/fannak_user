package com.hamzajbr.fannak_user.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hamzajbr.fannak_user.models.CategoryItem;
import com.hamzajbr.fannak_user.models.SubCategoryItem;
import com.hamzajbr.fannak_user.repositories.CategoryRepository;

import java.util.List;

public class CategoriesViewModel extends ViewModel {
    private LiveData<List<CategoryItem>> categories;

    private CategoryRepository categoryRepository;
    public void init(){
        if (categories != null){
            return;
        }
        categoryRepository = CategoryRepository.getInstance();
        categories = categoryRepository.getAllCategories();

    }

    public LiveData<List<CategoryItem>> getAllCategories(){
        return categories;
    }

}
