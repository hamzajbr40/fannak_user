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
import com.hamzajbr.fannak_user.models.OptionItem;

import java.util.ArrayList;

public class OptionsAdapter extends RecyclerView.Adapter<OptionsAdapter.OptionsViewHolder> {

    private Context context;
    private ArrayList<OptionItem> options;
    private IOption callback;

    public OptionsAdapter(Context context, ArrayList<OptionItem> options, IOption callback) {
        this.context = context;
        this.options = options;
        this.callback = callback;
    }


    @NonNull
    @Override
    public OptionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_options,parent,false);

        return new OptionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OptionsViewHolder holder, int position) {
        OptionItem item = options.get(position);
        holder.icon.setImageResource(item.icon);
        holder.label.setText(item.label);
        holder.itemView.setOnClickListener(v -> callback.onClick(item));


    }

    @Override
    public int getItemCount() {
        return options.size();
    }

    class OptionsViewHolder extends RecyclerView.ViewHolder{
        TextView label;
        ImageView icon;

        public OptionsViewHolder(@NonNull View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.label_tv);
            icon = itemView.findViewById(R.id.icon_img);

        }
    }
    public interface IOption{
        void onClick(OptionItem item);
    }

}
