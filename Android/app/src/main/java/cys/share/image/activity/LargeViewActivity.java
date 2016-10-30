package cys.share.image.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.kogitune.activity_transition.ActivityTransition;

import cys.share.image.R;
import cys.share.image.databinding.ActivityLargeviewingBinding;

/**
 * Created by Administrator on 2016/10/31.
 */
public class LargeViewActivity extends AppCompatActivity{

    ActivityLargeviewingBinding mBingding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBingding = DataBindingUtil.setContentView(this, R.layout.activity_largeviewing);
        ActivityTransition.with(getIntent()).to(mBingding.img).start(savedInstanceState);
    }
}
