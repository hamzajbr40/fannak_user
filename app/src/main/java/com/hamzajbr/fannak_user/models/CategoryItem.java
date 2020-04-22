package com.hamzajbr.fannak_user.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryItem {
    @SerializedName("Sub-Categories")
    public List<SubCategoryItem> subCategoryList;
    @SerializedName("Label")
    public String label;
    @SerializedName("IconName")
    public String icon;
}
