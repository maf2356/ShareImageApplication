package cys.share.image.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cys.share.image.R;
import cys.share.image.databinding.ActivityReleaseBinding;

/**
 * Created by Administrator on 2016/11/2.
 */

public class ReleaseActivity extends AppCompatActivity {

    private ActivityReleaseBinding mBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_release);

    }
}
