package cys.share.image.fragment;

import android.database.DatabaseUtils;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.kogitune.activity_transition.fragment.ExitFragmentTransition;
import com.kogitune.activity_transition.fragment.FragmentTransition;

import cys.share.image.R;
import cys.share.image.databinding.FragmentLargeviewingBinding;
import cys.share.image.entity.TContent;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Administrator on 2016/10/31.
 */
public class LargeViewFragment extends Fragment{

    private PhotoViewAttacher mAttacher;
    private FragmentLargeviewingBinding mBingding;
    private String mUrl;

    public static LargeViewFragment newInstance(String url) {
        LargeViewFragment f = new LargeViewFragment();
        Bundle args = new Bundle();
        args.putString("tag", url);
        f.setArguments(args);
        return f;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUrl = getArguments().getString("tag");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBingding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()),R.layout.fragment_largeviewing,container,false);
        mBingding.setFragment(this);
        mAttacher = new PhotoViewAttacher(mBingding.img);
        Glide.with(getActivity()).load(mUrl).crossFade().into(new GlideDrawableImageViewTarget(mBingding.img) {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                super.onResourceReady(resource, animation);
                mAttacher.update();
            }
        });
        return mBingding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if(mAttacher!=null){
            mAttacher.cleanup();
        }
        super.onDestroyView();
    }

}
