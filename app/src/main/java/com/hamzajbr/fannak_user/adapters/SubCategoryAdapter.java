package com.hamzajbr.fannak_user.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hamzajbr.fannak_user.R;
import com.hamzajbr.fannak_user.models.SubCategoryItem;

import java.util.ArrayList;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.SubCategoryViewHolder> {

    Context context;
    ArrayList<SubCategoryItem> items;
    ISubCategory callback;

    public SubCategoryAdapter(Context context, ArrayList<SubCategoryItem> items, ISubCategory callback) {
        this.context = context;
        this.items = items;
        this.callback = callback;
    }

    @NonNull
    @Override
    public SubCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sub_category,parent,false);
        return new SubCategoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoryViewHolder holder, int position) {
        SubCategoryItem item = items.get(position);
        holder.label.setText(item.label);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onClick(item);
            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class SubCategoryViewHolder extends RecyclerView.ViewHolder{
        TextView label;

        public SubCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.sub_category_label);

        }
    }
    public interface ISubCategory{
        public void onClick(SubCategoryItem item);
    }

}
