package cys.share.image.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cys.share.image.Constant;
import cys.share.image.activity.MainActivity;
import cys.share.image.adapter.GenericAdapter;
import cys.share.image.api.ShareImageApi;
import cys.share.image.auxiliary.ShareImageAuxiliaryTool;
import cys.share.image.database.ShareImageRealm;
import cys.share.image.entity.NavTag;
import cys.share.image.entity.TagContent;
import cys.share.image.entity.a;
import cys.share.image.fragment.base.BaseFragment;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/10/27.
 */
public class GenericFragment extends BaseFragment<TagContent> {

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
        ShareImageApi.getTagList("xFNuCZhGRwW5KE9tUA8tPndZOxChcAVY", tag, 1, new Subscriber<List<TagContent>>() {
            @Override
            public void onCompleted() {
                mDataBinding.recyclerView.setAdapter(new GenericAdapter(mData));
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(List<TagContent> tagContents) {
                mData = tagContents;
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
