package com.app.motion.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.LayoutRes;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.app.motion.ui.callbacks.OnItemClickListener;
import com.app.motion.ui.model.BaseModel;

import java.util.List;

public class CommonAdapter<T extends BaseModel> extends RecyclerView.Adapter<CommonAdapter.GRViewHolder> {

    public final List<T> itemList;
    public OnItemClickListener onItemClickListener;
    @LayoutRes
    public int itemLayout;

    public CommonAdapter(@LayoutRes int itemLayout, List<T> itemList, OnItemClickListener onItemClickListener) {
        this.itemList = itemList;
        this.onItemClickListener = onItemClickListener;
        this.itemLayout = itemLayout;
    }

    public CommonAdapter(@LayoutRes int itemLayout, List<T> itemList) {
        this.itemList = itemList;
        this.itemLayout = itemLayout;
    }

    public List<T> getItemList() {
        return itemList;
    }

    public void add(List<T> newList) {
        if (newList != null && !newList.isEmpty()) {
            int oldSize = getItemList().size();
            getItemList().addAll(newList);
            notifyItemRangeChanged(oldSize - 1, newList.size());
        }
    }

    public void add(T item) {
        itemList.add(item);
        notifyItemInserted(itemList.size() - 1);
    }

    public void add(T item, int position) {
        if (position > 0 && position <= getItemList().size()) {
            getItemList().add(item);
            notifyItemInserted(position);
        }
    }

    public void remove(int position) {
        if (position > 0 && position < getItemList().size()) {
            getItemList().remove(position);
            notifyItemRemoved(position);
        }
    }

    public void remove(T item) {
        int position = getItemList().indexOf(item);
        if (position >= 0 && position < getItemList().size()) {
            getItemList().remove(position);
            notifyItemRemoved(position);
        }
    }

    public void removeAll() {
        if (itemList == null) return;
        int oldSize = getItemList().size();
        getItemList().clear();
        notifyItemRangeRemoved(0, oldSize);
    }

    @Override
    public CommonAdapter.GRViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new CommonAdapter.GRViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CommonAdapter.GRViewHolder holder, int position) {
        holder.binding.setVariable(com.app.motion.ui.BR.item, itemList.get(position));
        if (onItemClickListener != null) {
            holder.binding.setVariable(com.app.motion.ui.BR.onItemClickListener, onItemClickListener);
        }
    }

    @BindingAdapter({"url"})
    public static void loadImage(ImageView imageView, int[] url) {
        imageView.setColorFilter(3);
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class GRViewHolder extends RecyclerView.ViewHolder {

        ViewDataBinding binding;

        GRViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
