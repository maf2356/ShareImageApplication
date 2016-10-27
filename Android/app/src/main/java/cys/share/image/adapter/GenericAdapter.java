package cys.share.image.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cys.share.image.BR;
import cys.share.image.R;
import cys.share.image.databinding.GenericItemBinding;
import cys.share.image.entity.TagContent;

/**
 * Created by Administrator on 2016/10/27.
 */
public class GenericAdapter extends RecyclerView.Adapter<GenericAdapter.BindingHolder>{


    private List<TagContent> mData = new ArrayList<>();

    public GenericAdapter(List<TagContent> data){
        this.mData = data;
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        GenericItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.generic_item, parent, false);
        BindingHolder holder = new BindingHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        holder.getBinding().setVariable(BR.item,mData.get(position));
        holder.getBinding().setCoverUrl(mData.get(position).getCover());
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class BindingHolder extends RecyclerView.ViewHolder {
        private GenericItemBinding mBinding;

        public BindingHolder(View itemView) {
            super(itemView);
        }

        public GenericItemBinding getBinding() {
            return mBinding;
        }

        public void setBinding(GenericItemBinding binding) {
            this.mBinding = binding;
        }
    }
}
