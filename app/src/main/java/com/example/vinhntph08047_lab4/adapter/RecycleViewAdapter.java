package com.example.vinhntph08047_lab4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vinhntph08047_lab4.model.RootModel;
import com.example.vinhntph08047_lab4.R;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.RecycleViewHolder> {
    private Context context;
    private List<RootModel.Photos.Photo> list;
    private OnItemClickListener onItemClickListener;

    public RecycleViewAdapter(Context context, List<RootModel.Photos.Photo> list, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.list = list;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecycleViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {
        holder.bindItem(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_item);
        }

        public void bindItem(int position) {
            Glide.with(itemView).load(list.get(position).getUrlM()).into(imageView);
            imageView.setOnClickListener(view -> {
                onItemClickListener.onImageClicked(list.get(position));
            });
        }
    }

    public interface OnItemClickListener {
        void onImageClicked(RootModel.Photos.Photo photo);
    }
}
