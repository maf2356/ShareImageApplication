package cys.share.image.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cys.share.image.entity.a;
import cys.share.image.fragment.base.BaseFragment;

/**
 * Created by Administrator on 2016/10/27.
 */
public class GenericFragment extends BaseFragment<a> {

    private String tag;

    private List<Object> mContentItems = new ArrayList<>();

    public static GenericFragment newInstance(String tag) {
        GenericFragment fragment = new GenericFragment();
        Bundle args = new Bundle();
        args.putString("tag", tag);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void requestData() {

    }

    @Override
    public void showUiResult() {

    }

    @Override
    public void onViewCreated(@Nullable Bundle savedInstanceState) {
        for (int i = 0; i < 50; ++i) {
            mContentItems.add(new Object());
        }
        mDataBinding.recyclerView.setAdapter(new TestRecyclerViewAdapter(mContentItems));

    }
}
