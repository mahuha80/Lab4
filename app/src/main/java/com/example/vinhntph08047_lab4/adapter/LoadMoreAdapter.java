package com.example.vinhntph08047_lab4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vinhntph08047_lab4.R;
import com.example.vinhntph08047_lab4.model.RootModel;

import java.util.List;

public class LoadMoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<RootModel.Photos.Photo> list;
    private OnItemClickListener onItemClickListener;
    private int VIEW_TYPE_ITEM = 1;
    private int VIEW_TYPE_LOADING = 2;

    public LoadMoreAdapter(Context context, List<RootModel.Photos.Photo> list, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.list = list;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            return new LoadMoreHolder((LayoutInflater.from(context).inflate(R.layout.item, parent, false)));
        } else {
            return new LoadingHolder((LayoutInflater.from(context).inflate(R.layout.item_loadmore, parent, false)));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LoadMoreHolder) {
            ((LoadMoreHolder) holder).bindItem(position);
        } else {

        }
    }

    @Override
    public int getItemViewType(int position) {
        return (list.get(position) != null) ? VIEW_TYPE_ITEM : VIEW_TYPE_LOADING;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(List<RootModel.Photos.Photo> list) {
        int last_size = list.size() - 1;
        this.list.addAll(list);
        notifyItemRangeChanged(last_size, list.size() - 1);
    }

    public void replaceItem(List<RootModel.Photos.Photo> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onImageClicked(RootModel.Photos.Photo photo);
    }

    public class LoadMoreHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public LoadMoreHolder(@NonNull View itemView) {
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

    public class LoadingHolder extends RecyclerView.ViewHolder {

        public LoadingHolder(@NonNull View itemView) {
            super(itemView);
        }

    }
}
