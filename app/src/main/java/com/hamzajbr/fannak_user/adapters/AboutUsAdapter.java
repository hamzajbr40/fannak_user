package com.hamzajbr.fannak_user.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hamzajbr.fannak_user.R;
import com.hamzajbr.fannak_user.models.DeveloperItem;

import java.util.ArrayList;

public class AboutUsAdapter extends RecyclerView.Adapter<AboutUsAdapter.AboutUsViewHolder> {
    Context context;
    ArrayList<DeveloperItem> mylist;

    public AboutUsAdapter(Context context, ArrayList<DeveloperItem> mylist) {
        this.context = context;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public AboutUsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_developer,parent,false);

        return new AboutUsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AboutUsViewHolder holder, int position) {
        DeveloperItem item = mylist.get(position);
        holder.nameTv.setText(item.name);
        holder.descriptionTv.setText(item.description);
        holder.profileImg.setImageResource(item.image);


    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    class AboutUsViewHolder extends RecyclerView.ViewHolder{
        ImageView profileImg;
        TextView nameTv;
        TextView descriptionTv;
        public AboutUsViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.name_tv);
            profileImg = itemView.findViewById(R.id.profile_img);
            descriptionTv = itemView.findViewById(R.id.description_tv);
        }
    }
}
