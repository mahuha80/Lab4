package com.example.vinhntph08047_lab4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vinhntph08047_lab4.R;
import com.example.vinhntph08047_lab4.model.GalleriesModel;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {
    private Context context;
    private List<GalleriesModel> galleriesModelList;
    private OnItemClickListener onItemClickListener;

    public CategoryAdapter(Context context, List<GalleriesModel> galleriesModelList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.galleriesModelList = galleriesModelList;
        this.onItemClickListener = onItemClickListener;
    }

    //ka đơ gua ri
    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryHolder(LayoutInflater.from(context).inflate(R.layout.item_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        holder.bindItem(position);
    }

    @Override
    public int getItemCount() {
        return galleriesModelList.size() == 0 ? null : galleriesModelList.size();
    }

    public interface OnItemClickListener {
        void onCategoryClicked(GalleriesModel galleriesModel);
    }

    public class CategoryHolder extends RecyclerView.ViewHolder {
        TextView tvItemCategory;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            tvItemCategory = itemView.findViewById(R.id.tv_item_category);
        }

        public void bindItem(int position) {
            tvItemCategory.setText(galleriesModelList.get(position).getName());
            itemView.setOnClickListener(view -> onItemClickListener.onCategoryClicked(galleriesModelList.get(position)));
        }
    }
}
