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

import java.util.List;

import cys.share.image.R;
import cys.share.image.api.ShareImageApi;
import cys.share.image.auxiliary.ShareImageAuxiliaryTool;
import cys.share.image.databinding.ActivityLargeviewingBinding;
import cys.share.image.databinding.ItemLargeviewBinding;
import cys.share.image.entity.Cover;
import cys.share.image.entity.TContent;
import cys.share.image.entity.TagContent;
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
                mAdapter = new LargeViewAdapter(item);
                mViewPager.setAdapter(mAdapter);
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
        mPhotoViewAttacher.cleanup();
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPhotoViewAttacher.cleanup();
    }

    private class LargeViewAdapter extends PagerAdapter{

        TContent mItem;

        public LargeViewAdapter(TContent item){
            mItem = item;
        }

        @Override
        public int getCount() {
            return mItem.getImages().size();
        }


        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ItemLargeviewBinding itemLargeviewBinding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.item_largeview,container,false);
            List<Cover> mItemImages = mItem.getImages();
            if(getCount()>0){
                itemLargeviewBinding.setImgUrl(mItemImages.get(position).getLargeUrl());
            }else{
                itemLargeviewBinding.setImgUrl(mItem.getCover().getLargeUrl());
            }
            mPhotoViewAttacher = new PhotoViewAttacher(itemLargeviewBinding.img);
            container.addView(itemLargeviewBinding.getRoot());
            return itemLargeviewBinding.getRoot();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }
}
