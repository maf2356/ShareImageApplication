package cys.share.image.fragment.base;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cys.share.image.BaseFragmentDataBinding;
import cys.share.image.R;
import cys.share.image.auxiliary.MaterialViewPagerHeaderDecorator;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.FadeInAnimator;
import jp.wasabeef.recyclerview.animators.LandingAnimator;
import jp.wasabeef.recyclerview.animators.OvershootInLeftAnimator;

/**
 * Created by Administrator on 2016/10/26.
 */
public abstract class BaseFragment<T> extends Fragment{


    public List<T> mData  = new ArrayList<>();;


    public abstract void requestData();

    public abstract void showUiResult();

    public abstract void onViewCreated(@Nullable Bundle savedInstanceState);

    public BaseFragmentDataBinding mDataBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDataBinding =  DataBindingUtil.inflate(inflater,R.layout.fragment_recyclerview,container,false);
        mDataBinding.setFragment(this);
        return mDataBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(getActivity());
        mDataBinding.recyclerView.setLayoutManager(layoutManager);
        mDataBinding.recyclerView.setHasFixedSize(true);
        mDataBinding.recyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        mDataBinding.recyclerView.setItemAnimator(new FadeInAnimator());

        onViewCreated(savedInstanceState);
        requestData();
    }
}
