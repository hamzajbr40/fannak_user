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
import com.hamzajbr.fannak_user.R;
import com.hamzajbr.fannak_user.models.ProductItem;

import java.util.ArrayList;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder> {

    private Context context;
    private ArrayList<ProductItem> myList;
    private IFeatured callback;

    public FeaturedAdapter(Context context, ArrayList<ProductItem> myList, IFeatured callback) {
        this.context = context;
        this.myList = myList;
        this.callback = callback;
    }


    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);

        return new FeaturedViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {
        ProductItem item = myList.get(position);
        holder.productName.setText(item.name);

        //Todo change it to seller name
        holder.sellerName.setText(( item.sellerName));
        holder.categoryName.setText(item.category);
        holder.productPrice.setText(item.price+" JD");
        byte[] imageByteArray = Base64.decode(item.image,Base64.DEFAULT);
        Glide.with(context).load(imageByteArray).into(holder.productImg);


    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    class FeaturedViewHolder extends RecyclerView.ViewHolder{
        ImageView productImg;
        TextView productName;
        TextView sellerName;
        TextView categoryName;
        TextView productPrice;


        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);
            productImg = itemView.findViewById(R.id.item_img);
            productName = itemView.findViewById(R.id.item_name);
            productPrice = itemView.findViewById(R.id.item_price);
            sellerName = itemView.findViewById(R.id.seller_name);
            categoryName = itemView.findViewById(R.id.item_category);
        }
    }
    public interface IFeatured{
        void onClick(ProductItem item);
    }
}
