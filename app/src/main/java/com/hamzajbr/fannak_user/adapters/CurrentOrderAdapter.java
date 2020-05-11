package com.hamzajbr.fannak_user.adapters;

import android.content.Context;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.hamzajbr.fannak_user.R;
import com.hamzajbr.fannak_user.models.CurrentOrderItem;
import com.hamzajbr.fannak_user.models.ProductItem;

import java.util.ArrayList;

public class CurrentOrderAdapter extends RecyclerView.Adapter<CurrentOrderAdapter.CurrentOrderViewHolder> {

    private Context context;
    private ArrayList<CurrentOrderItem> myList = new ArrayList<>();
    private IComplete callback;

    public CurrentOrderAdapter(Context context, IComplete callback) {
        this.context = context;
        this.callback = callback;
    }

    public void setCurrentOrders( ArrayList<CurrentOrderItem> myList){
        this.myList = myList;
        notifyDataSetChanged();
    }





    @NonNull
    @Override
    public CurrentOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_current_order,parent,false);
        return new CurrentOrderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrentOrderViewHolder holder, int position) {
        CurrentOrderItem item = myList.get(position);
        holder.productName.setText(item.name);

        holder.sellerName.setText(( item.sellerName));
        holder.categoryName.setText(item.category);
        holder.productPrice.setText(item.price+" JD");
        byte[] imageByteArray = Base64.decode(item.image,Base64.DEFAULT);
        Glide.with(context).load(imageByteArray).into(holder.productImg);

        holder.completeBtn.setOnClickListener(v -> callback.onClick(item));

    }

    @Override
    public int getItemCount() {
        if(myList != null) {
            return myList.size();
        }else
            return 0;
    }

    class CurrentOrderViewHolder extends RecyclerView.ViewHolder {
        ImageView productImg;
        TextView productName;
        TextView sellerName;
        TextView categoryName;
        TextView productPrice;
        MaterialButton completeBtn;


        public CurrentOrderViewHolder(@NonNull View itemView) {
            super(itemView);

            completeBtn = itemView.findViewById(R.id.complete_order_btn);
            productImg = itemView.findViewById(R.id.item_img);
            productName = itemView.findViewById(R.id.item_name);
            productPrice = itemView.findViewById(R.id.item_price);
            sellerName = itemView.findViewById(R.id.seller_name);
            categoryName = itemView.findViewById(R.id.item_category);
        }
    }
    public interface IComplete{
        void onClick(CurrentOrderItem item);
    }

}
