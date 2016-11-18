package cys.share.image.fragment;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.MaterialViewPagerSettings;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;
import java.util.ArrayList;
import java.util.List;

import cys.share.image.Constant;
import cys.share.image.activity.MainActivity;
import cys.share.image.adapter.GenericAdapter;
import cys.share.image.api.ShareImageApi;
import cys.share.image.auxiliary.ShareImageAuxiliaryTool;
import cys.share.image.database.ShareImageRealm;
import cys.share.image.entity.NavTag;
import cys.share.image.entity.TContent;
import cys.share.image.entity.TagContent;
import cys.share.image.entity.a;
import cys.share.image.fragment.base.BaseFragment;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by 陈尤帅 on 2016/10/27.
 */
public class GenericFragment extends BaseFragment<TContent> {


    public static GenericFragment newInstance(String tag) {
        if(tag.equals(Constant.ALLTAGS)){
            tag = "";
        }
        GenericFragment fragment = new GenericFragment();
        Bundle args = new Bundle();
        args.putString("tag", tag);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void requestData() {
        String tag = getArguments().getString("tag");
        ShareImageApi.getTagList(getToken(),tag,1,(t)->{
            mData = t.getDatas();
            if(mData!=null&&mData.size()>0){
                GenericAdapter adapter = new GenericAdapter(getActivity(),mData);
                mDataBinding.recyclerView.setAdapter(adapter);
                doneRefresh();
            }
        });
    }

    @Override
    public void showUiResult() {

    }

    @Override
    public void onViewCreated(@Nullable Bundle savedInstanceState) {
    }
}
