package com.hamzajbr.fannak_user.adapters;

import android.content.Context;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hamzajbr.fannak_user.R;
import com.hamzajbr.fannak_user.models.BannerItem;
import com.hamzajbr.fannak_user.utilities.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BannersAdapter extends RecyclerView.Adapter<BannersAdapter.BannersViewHolder> {

    private Context context;
    private ArrayList<BannerItem> myList;
    private IBanner callback;

    public BannersAdapter(Context context, ArrayList<BannerItem> myList, IBanner callback) {
        this.context = context;
        this.myList = myList;
        this.callback = callback;
    }


    @NonNull
    @Override
    public BannersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner,parent,false);
        return new BannersViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BannersViewHolder holder, int position) {
        BannerItem item = myList.get(position);
        byte[] imageByteArray = Base64.decode(item.bannerImg,Base64.DEFAULT);
        Glide.with(context).load(imageByteArray).into(holder.bannerImg);


    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    class BannersViewHolder extends RecyclerView.ViewHolder {
        ImageView bannerImg;

        public BannersViewHolder(@NonNull View itemView) {
            super(itemView);
            bannerImg = itemView.findViewById(R.id.banner_img);
        }
    }

    public interface IBanner{
        void onClick(BannerItem item);
    }
}
