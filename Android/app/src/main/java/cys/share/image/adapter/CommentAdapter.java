package cys.share.image.adapter;

import android.annotation.TargetApi;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cys.share.image.BR;
import cys.share.image.Constant;
import cys.share.image.R;
import cys.share.image.auxiliary.ShareImageAuxiliaryTool;
import cys.share.image.databinding.GenericItemBinding;
import cys.share.image.databinding.ItemCommentBinding;
import cys.share.image.entity.Comment;
import cys.share.image.entity.CommentDatas;
import cys.share.image.entity.Cover;
import cys.share.image.entity.TContent;
import cys.share.image.entity.User;
import cys.share.image.listener.ShareImageEventListener;

/**
 * Created by Administrator on 2016/10/27.
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.BindingHolder>{


    private List<Comment> mData = new ArrayList<>();
    private ShareImageEventListener mListener;
    private FragmentActivity mActivity;
    public CommentAdapter(FragmentActivity activity, List<Comment> data){
        this.mActivity = activity;
        this.mData = data;
        mListener = new ShareImageEventListener();
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCommentBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_comment, parent, false);
        BindingHolder holder = new BindingHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        Comment comment = mData.get(position);
        holder.getBinding().setVariable(BR.item,comment);
        holder.getBinding().executePendingBindings();

        //child comment
        CommentDatas childComments = comment.getChildComments();
        if(childComments!=null&&childComments.getDatas().size()>0){
            holder.getBinding().setListener(new ShareImageEventListener());
            addChildComment(holder.getBinding().childCommentLayout,childComments.getDatas());
        }
    }

    private void addChildComment(LinearLayout childCommentLayout, List<Comment> data){
        childCommentLayout.removeAllViews();
        for (int i= 0;i<data.size();i++
             ) {
            Comment obj = data.get(i);
            if(i>= Constant.MAX_SHOW_COMMENT){
                break;
            }
            View view = LayoutInflater.from(mActivity).inflate(R.layout.item_childcoment, null, false);
            TextView tv = (TextView) view.findViewById(R.id.comment);
            int count = obj.getUser().getNickName().length()+1;
            String content = obj.getUser().getNickName()+":"+obj.getContent();
            SpannableStringBuilder builder = new SpannableStringBuilder(content);
            builder.setSpan(new UserNickClickableSpan(),0,count, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv.setMovementMethod(LinkMovementMethod.getInstance());
            tv.setText(builder);
            tv.setHighlightColor(0x00000000);
            childCommentLayout.addView(view);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class BindingHolder extends RecyclerView.ViewHolder {
        private ItemCommentBinding mBinding;

        public BindingHolder(View itemView) {
            super(itemView);
        }

        public ItemCommentBinding getBinding() {
            return mBinding;
        }

        public void setBinding(ItemCommentBinding binding) {
            this.mBinding = binding;
        }
    }
}
