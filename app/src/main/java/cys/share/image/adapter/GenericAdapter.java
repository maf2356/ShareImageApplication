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
import cys.share.image.auxiliary.ShareImageAuxiliaryTool;
import cys.share.image.database.ShareImageRealm;
import cys.share.image.databinding.GenericItemBinding;
import cys.share.image.entity.Cover;
import cys.share.image.entity.TContent;
import cys.share.image.entity.TagContent;
import cys.share.image.entity.User;
import cys.share.image.listener.ShareImageEventListener;

/**
 * Created by Administrator on 2016/10/27.
 */
public class GenericAdapter extends RecyclerView.Adapter<GenericAdapter.BindingHolder>{


    private List<TContent> mData = new ArrayList<>();
    private ShareImageEventListener mListener;
    private FragmentActivity mActivity;
    private String token;
    public GenericAdapter(FragmentActivity activity,List<TContent> data){
        this.mActivity = activity;
        this.mData = data;
        mListener = new ShareImageEventListener();
        User user = ShareImageRealm.getInstance(activity).queryUserInfo();
        if(user!=null){
            token = user.getToken();
        }
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        GenericItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.generic_item, parent, false);
        BindingHolder holder = new BindingHolder(binding.getRoot());


        //set imageview size before load
        holder.setBinding(binding);
        return holder;
    }



    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        TContent tContent = mData.get(position);
        holder.getBinding().setVariable(BR.item,tContent);
        Cover cover = tContent.getCover();
        if(cover!=null&& !TextUtils.isEmpty(cover.getMiddleUrl())){
            float dimension = mActivity.getResources().getDimension(R.dimen.activity_horizontal_margin);
            int width  = cover.getWidth();
            int height  = cover.getHeight();
            int widthImg = (int) ((ShareImageAuxiliaryTool.getWindowsWidth(mActivity)) - dimension);
            int heightImg = (int) (widthImg / (((float) width / height)));
            ViewGroup.LayoutParams para = holder.getBinding().coverImg.getLayoutParams();
            para.height = heightImg;
            para.width = widthImg;
            holder.getBinding().coverImg.setLayoutParams(para);
            holder.getBinding().setCoverUrl(cover.getMiddleUrl());
        }
        holder.getBinding().like.setText(mActivity.getResources().getString(R.string.like_count,tContent.getLikeCount()));
        holder.getBinding().comment.setText(mActivity.getResources().getString(R.string.comment_count,tContent.getCommentCount()));
        User user = tContent.getUser();
        if(user!=null&&!TextUtils.isEmpty(user.getAvatar())){
            holder.getBinding().setAvatarUrl(user.getAvatar());
        }
        holder.getBinding().setToken(token);
        holder.getBinding().setAdapter(this);
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
