package cys.share.image.adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cys.share.image.BR;
import cys.share.image.R;
import cys.share.image.databinding.GenericItemBinding;
import cys.share.image.entity.Cover;
import cys.share.image.entity.TagContent;
import cys.share.image.listener.ShareImageEventListener;

/**
 * Created by Administrator on 2016/10/27.
 */
public class GenericAdapter extends RecyclerView.Adapter<GenericAdapter.BindingHolder>{


    private List<TagContent> mData = new ArrayList<>();
    private ShareImageEventListener mListener;
    private FragmentActivity mActivity;
    public GenericAdapter(FragmentActivity activity,List<TagContent> data){
        this.mActivity = activity;
        this.mData = data;
        mListener = new ShareImageEventListener();
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
        Cover cover = mData.get(position).getCover();
        if(cover!=null&& !TextUtils.isEmpty(cover.getMiddleUrl())){
            holder.getBinding().setCoverUrl(cover.getMiddleUrl());
        }
        String userAvatar = mData.get(position).getUser().getAvatar();
        if(!TextUtils.isEmpty(userAvatar)){
            holder.getBinding().setAvatarUrl(userAvatar);
        }
        holder.getBinding().setActivity(mActivity);
        holder.getBinding().setListener(mListener);
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