package cys.share.image.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kogitune.activity_transition.ActivityTransition;
import com.squareup.picasso.Picasso;

import cys.share.image.R;
import cys.share.image.databinding.ActivityLargeviewingBinding;
import cys.share.image.entity.TContent;
import cys.share.image.entity.TagContent;
import cys.share.image.imagepicker.Image;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Administrator on 2016/10/31.
 */
public class LargeViewActivity extends AppCompatActivity{

    ActivityLargeviewingBinding mBingding;
    public static final String EXTRA_TAGCONTENT = TagContent.class.getName();
    public static final String TRANSIT_PIC = "picture";
    private PhotoViewAttacher mPhotoViewAttacher;
    private ViewPager mViewPager;
    TContent item;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBingding = DataBindingUtil.setContentView(this, R.layout.activity_largeviewing);
        mViewPager = mBingding.viewpager;
        item = (TContent) getIntent().getSerializableExtra(EXTRA_TAGCONTENT);
        mBingding.setItem(item);
        Adapter a = new Adapter();
        mViewPager.setAdapter(a);
//        ViewCompat.setTransitionName(a.getImg(), TRANSIT_PIC);

//        mPhotoViewAttacher = new PhotoViewAttacher(mBingding.img);
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

    private class Adapter extends PagerAdapter{

        ImageView img;
        @Override
        public int getCount() {
            return item.getImageCount();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(LargeViewActivity.this).inflate(R.layout.item_largeview, container, false);
            ImageView img = (ImageView) view.findViewById(R.id.img);
            if(position==0){
                Adapter.this.img = img;
            }
            mPhotoViewAttacher = new PhotoViewAttacher(img);
            Picasso.with(container.getContext()).load(item.getCover().getMiddleUrl()).into(img);
            ViewCompat.setTransitionName(img, TRANSIT_PIC);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        public ImageView getImg(){
            return img;
        }
    }
}
