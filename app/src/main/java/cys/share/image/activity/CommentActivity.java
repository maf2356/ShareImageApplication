package cys.share.image.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import java.util.Arrays;
import java.util.List;

import cys.share.image.R;
import cys.share.image.adapter.CommentAdapter;
import cys.share.image.api.ShareImageApi;
import cys.share.image.auxiliary.ShareImageAuxiliaryTool;
import cys.share.image.databinding.ActivityCommentBinding;
import cys.share.image.entity.Comment;
import cys.share.image.entity.CommentDatas;
import cys.share.image.entity.TContent;

/**
 * Created by 陈尤帅 on 2016/11/11.
 */

public class CommentActivity extends BaseActivity {

    ActivityCommentBinding mBinding;
    TContent item;
    CommentDatas mDatas;
    CommentAdapter mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_comment);
        item = (TContent) getIntent().getSerializableExtra("item");
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(this);
        mBinding.recyclerView.setLayoutManager(layoutManager);
        mBinding.recyclerView.setHasFixedSize(true);
        mBinding.setUser(getUser());
        initSwipeRefresh();
        requestData();
        initCommentLayout();
    }

    private void initCommentLayout(){
        if(mBinding!=null){
            mBinding.showCommentLayout.setOnClickListener((o)->{
                mBinding.showCommentLayout.setVisibility(View.INVISIBLE);
                mBinding.showCommentEditTextLayout.setVisibility(View.VISIBLE);
                mBinding.showCommentEditTextLayout.setFocusable(true);
                mBinding.showCommentEditTextLayout.setFocusableInTouchMode(true);
                mBinding.showCommentEditTextLayout.requestFocus();
            });
            mBinding.showCommentEditTextLayout.setOnFocusChangeListener((v,b)->{
                ShareImageAuxiliaryTool.log(String.valueOf(b));
            });
        }
    }

    private void initSwipeRefresh(){
        mBinding.swipeRefreshLayout.setProgressViewOffset(true,0, (int) (getResources().getDimension(R.dimen.viewpager_headerHeight)/2));
        mBinding.swipeRefreshLayout.setOnRefreshListener(this::requestData);
        mBinding.swipeRefreshLayout.setRefreshing(true);
    }

    private void requestData() {
        ShareImageApi.getComment(getUser().getToken(),0,item.getId(),1,(o)->{
            closeSwipeRefreshing();
            if(o.getCount()!=0){
                mDatas = o;
                mBinding.showCommentLayout.setVisibility(View.VISIBLE);
                mBinding.showCommentEditTextLayout.setVisibility(View.INVISIBLE);
                setAdapter();
            }
        },(e)-> closeSwipeRefreshing());
    }


    private void closeSwipeRefreshing(){
        if(mBinding.swipeRefreshLayout.isRefreshing()){
            mBinding.swipeRefreshLayout.setRefreshing(false);
        }
    }

    private void setAdapter(){
        if(mAdapter!=null){
            mAdapter.notifyDataSetChanged();
        }else{
            mAdapter = new CommentAdapter(this,mDatas.getDatas());
            mBinding.recyclerView.setAdapter(mAdapter);
        }
    }

    public void commit(View view){
        if(!TextUtils.isEmpty(mBinding.commentEd.getText().toString())){
            ShareImageApi.putComment(getUser().getToken(),item.getId(),0,mBinding.commentEd.getText().toString(),
                    (comment -> {
                        mDatas.getDatas().add(comment);
                        setAdapter();
                        ShareImageAuxiliaryTool.log(comment.getContent());
                    }),throwable -> ShareImageAuxiliaryTool.log(throwable.getMessage()));
        }
    }
}
