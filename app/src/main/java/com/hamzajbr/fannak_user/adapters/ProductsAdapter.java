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

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {

    private Context context;
    private ArrayList<ProductItem> myList = new ArrayList<>();
    private IProducts callback;

    public ProductsAdapter(Context context, IProducts callback) {
        this.context = context;

        this.callback = callback;
    }
    public void setProducts( ArrayList<ProductItem> myList){

        this.myList = myList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);

        return new ProductsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {
        ProductItem item = myList.get(position);
        holder.productName.setText(item.name);

        holder.sellerName.setText(( item.sellerName));
        holder.categoryName.setText(item.category);
        holder.productPrice.setText(item.price+" JD");
        byte[] imageByteArray = Base64.decode(item.image,Base64.DEFAULT);
        Glide.with(context).load(imageByteArray).into(holder.productImg);

        holder.itemView.setOnClickListener(v -> callback.onClick(item));


    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    class ProductsViewHolder extends RecyclerView.ViewHolder{
        ImageView productImg;
        TextView productName;
        TextView sellerName;
        TextView categoryName;
        TextView productPrice;


        public ProductsViewHolder(@NonNull View itemView) {
            super(itemView);
            productImg = itemView.findViewById(R.id.item_img);
            productName = itemView.findViewById(R.id.item_name);
            productPrice = itemView.findViewById(R.id.item_price);
            sellerName = itemView.findViewById(R.id.seller_name);
            categoryName = itemView.findViewById(R.id.item_category);
        }
    }
    public interface IProducts {
        void onClick(ProductItem item);
    }
}
