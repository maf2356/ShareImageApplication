package cys.share.image.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.kogitune.activity_transition.ActivityTransition;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import cys.share.image.R;
import cys.share.image.api.ShareImageApi;
import cys.share.image.auxiliary.ShareImageAuxiliaryTool;
import cys.share.image.databinding.ActivityLargeviewingBinding;
import cys.share.image.databinding.ItemLargeviewBinding;
import cys.share.image.entity.Cover;
import cys.share.image.entity.TContent;
import cys.share.image.entity.TagContent;
import cys.share.image.fragment.LargeViewFragment;
import cys.share.image.imagepicker.Image;
import rx.Subscriber;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Administrator on 2016/10/31.
 */
public class LargeViewActivity extends BaseActivity{

    ActivityLargeviewingBinding mBingding;
    public static final String EXTRA_TAGCONTENT = TagContent.class.getName();
    public static final String TRANSIT_PIC = "picture";
    private PhotoViewAttacher mPhotoViewAttacher;
    private ViewPager mViewPager;
    TContent item;
    LargeViewAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBingding = DataBindingUtil.setContentView(this, R.layout.activity_largeviewing);
        mViewPager = mBingding.viewpager;
        item = (TContent) getIntent().getSerializableExtra(EXTRA_TAGCONTENT);
        mBingding.setItem(item);
        getImagesDetail();
//        ViewCompat.setTransitionName(a.getImg(), TRANSIT_PIC);

//        mPhotoViewAttacher = new PhotoViewAttacher(mBingding.img);
    }

    public static Intent newIntent(Context context, TContent item) {
        Intent intent = new Intent(context, LargeViewActivity.class);
        intent.putExtra(LargeViewActivity.EXTRA_TAGCONTENT, item);
        return intent;
    }



    private void getImagesDetail(){
        ShareImageApi.getTContent(getUser().getToken(), item.getId()+"", new Subscriber<TContent>() {
            @Override
            public void onCompleted() {
//                Picasso.with(LargeViewActivity.this).load(item.getCover().getLargeUrl()).into(mBingding.img);
//                mAdapter = new LargeViewAdapter(item);
                mViewPager.setAdapter(new LargeViewAdapter(getSupportFragmentManager()));
            }

            @Override
            public void onError(Throwable e) {
                ShareImageAuxiliaryTool.log(e.getMessage());
            }

            @Override
            public void onNext(TContent tContent) {
                item.setImages(tContent.getImages());
                ShareImageAuxiliaryTool.log(tContent.toString());
            }
        });
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    private  class LargeViewAdapter extends FragmentStatePagerAdapter {

        public LargeViewAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return LargeViewFragment.newInstance(item.getImages().get(position).getLargeUrl());
        }

        @Override
        public int getCount() {
            int i = 0;
            for (;(item.getImages().size()>i&&item.getImages().get(i)!=null);i++);
            return i;
        }
    }
}
