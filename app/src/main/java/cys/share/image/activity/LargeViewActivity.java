package cys.share.image.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;

import com.kogitune.activity_transition.ActivityTransition;
import com.squareup.picasso.Picasso;

import cys.share.image.R;
import cys.share.image.databinding.ActivityLargeviewingBinding;

/**
 * Created by Administrator on 2016/10/31.
 */
public class LargeViewActivity extends AppCompatActivity{

    ActivityLargeviewingBinding mBingding;
    public static final String EXTRA_IMAGE_URL = "image_url";
    public static final String EXTRA_IMAGE_TITLE = "image_title";
    public static final String TRANSIT_PIC = "picture";
    String mImageUrl, mImageTitle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBingding = DataBindingUtil.setContentView(this, R.layout.activity_largeviewing);
        mImageUrl = getIntent().getStringExtra(EXTRA_IMAGE_URL);
        mImageTitle = getIntent().getStringExtra(EXTRA_IMAGE_TITLE);

        ViewCompat.setTransitionName(mBingding.img, TRANSIT_PIC);
        Picasso.with(this).load(mImageUrl).into(mBingding.img);
    }

    public static Intent newIntent(Context context, String url, String desc) {
        Intent intent = new Intent(context, LargeViewActivity.class);
        intent.putExtra(LargeViewActivity.EXTRA_IMAGE_URL, url);
        intent.putExtra(LargeViewActivity.EXTRA_IMAGE_TITLE, desc);
        return intent;
    }
}
