package cys.share.image.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Arrays;

import cys.share.image.R;
import cys.share.image.adapter.CommentAdapter;
import cys.share.image.api.ShareImageApi;
import cys.share.image.auxiliary.ShareImageAuxiliaryTool;
import cys.share.image.databinding.ActivityCommentBinding;
import cys.share.image.entity.TContent;

/**
 * Created by 陈尤帅 on 2016/11/11.
 */

public class CommentActivity extends BaseActivity {

    ActivityCommentBinding mBinding;
    TContent item;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_comment);
        item = (TContent) getIntent().getSerializableExtra("item");
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(this);
        mBinding.recyclerView.setLayoutManager(layoutManager);
        mBinding.recyclerView.setHasFixedSize(true);
        requestData();
    }

    public void requestData() {
        ShareImageApi.getComment(getUser().getToken(),0,item.getId(),1,(o)->{
            if(o.getCount()!=0){
                mBinding.recyclerView.setAdapter(new CommentAdapter(this,o.getDatas()));
            }
        },(e)->{
            ShareImageAuxiliaryTool.log(e.getMessage());
        });
    }


}
