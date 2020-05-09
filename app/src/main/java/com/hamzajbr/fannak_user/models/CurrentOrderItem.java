package com.hamzajbr.fannak_user.models;

import com.google.gson.annotations.SerializedName;

public class CurrentOrderItem {
    @SerializedName("Item_Name")
    public String name;
    @SerializedName("Item_Type")
    public String type;
    @SerializedName("Item_Category")
    public String category;
    @SerializedName("Item_Seller")
    public int sellerID;
    @SerializedName("Seller_Name")
    public String sellerName;
    @SerializedName("ID")
    public int itemID;
    @SerializedName("Item_Description")
    public String description;
    @SerializedName("Order_id")
    public int orderId;
    @SerializedName("Item_Image")
    public String image;
    @SerializedName("Item_Price")
    public int price;

}
