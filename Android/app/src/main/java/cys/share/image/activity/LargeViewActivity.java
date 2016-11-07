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
import cys.share.image.entity.TContent;
import cys.share.image.entity.TagContent;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Administrator on 2016/10/31.
 */
public class LargeViewActivity extends AppCompatActivity{

    ActivityLargeviewingBinding mBingding;
    public static final String EXTRA_TAGCONTENT = TagContent.class.getName();
    public static final String TRANSIT_PIC = "picture";
    private PhotoViewAttacher mPhotoViewAttacher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBingding = DataBindingUtil.setContentView(this, R.layout.activity_largeviewing);

        ViewCompat.setTransitionName(mBingding.img, TRANSIT_PIC);
        TContent item = (TContent) getIntent().getSerializableExtra(EXTRA_TAGCONTENT);
        mBingding.setItem(item);
        mPhotoViewAttacher = new PhotoViewAttacher(mBingding.img);
    }

    public static Intent newIntent(Context context, TContent item) {
        Intent intent = new Intent(context, LargeViewActivity.class);
        intent.putExtra(LargeViewActivity.EXTRA_TAGCONTENT, item);
        return intent;
    }

    @Override
    public void onBackPressed() {
        mPhotoViewAttacher.cleanup();
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPhotoViewAttacher.cleanup();
    }
}
