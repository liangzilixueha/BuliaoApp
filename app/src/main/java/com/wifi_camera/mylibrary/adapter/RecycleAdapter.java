package com.wifi_camera.mylibrary.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wifi_camera.mylibrary.R;

import java.util.List;

public class RecycleAdapter<T> extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
    private List<T> mData;
    private int mLayoutRes;           //布局id

    public RecycleAdapter(List<T> mData, int mLayoutRes) {
        this.mData = mData;
        this.mLayoutRes = mLayoutRes;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mLayoutRes, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapter.ViewHolder holder, int position) {
        holder.getTextView().setText(mData.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void add(T s) {
        mData.add(s);
        notifyDataSetChanged();
    }

    /**
     * 添加一个不重复的数据
     *
     * @param s
     * @return
     */
    public Boolean addOnly(T s) {
        if (mData.contains(s)) {
            return false;
        }
        mData.add(s);
        notifyDataSetChanged();
        return true;
    }

    public List<T> getData() {
        return mData;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
            textView.setOnClickListener(v -> {
                //删除这个item
                int position = getAdapterPosition();
                RecycleAdapter adapter = (RecycleAdapter) ((RecyclerView) itemView.getParent()).getAdapter();
                adapter.getData().remove(position);
                adapter.notifyItemRemoved(position);
            });
        }

        public TextView getTextView() {
            return textView;
        }
    }
}
