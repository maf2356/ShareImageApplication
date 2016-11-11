package cys.share.image.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import cys.share.image.R;

/**
 * Created by 陈尤帅 on 2016/11/11.
 */

public class CommentActivity extends BaseActivity {

    ActivityCommentBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_comment);
    }
}
