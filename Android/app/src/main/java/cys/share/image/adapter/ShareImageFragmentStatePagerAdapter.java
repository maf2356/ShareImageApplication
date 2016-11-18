package cys.share.image.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import cys.share.image.Constant;
import cys.share.image.entity.NavTag;
import cys.share.image.fragment.GenericFragment;

/**
 * Created by Administrator on 2016/11/6.
 */
public class ShareImageFragmentStatePagerAdapter extends FragmentStatePagerAdapter{

    private List<GenericFragment> genericFragmentList;

    private  List<NavTag> mTags;

    public ShareImageFragmentStatePagerAdapter(FragmentManager fm) {
        super(fm);
        genericFragmentList = new ArrayList<>();
    }

    public ShareImageFragmentStatePagerAdapter(FragmentManager fm, List<NavTag> tags){
        this(fm);
        this.mTags = tags;
        //add all tag
        genericFragmentList.add(GenericFragment.newInstance(Constant.ALLTAGS));
        for (NavTag tag:
             tags) {
            genericFragmentList.add(GenericFragment.newInstance(tag.getName()));
        }
    }

    @Override
    public GenericFragment getItem(int position) {
        return genericFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return genericFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return Constant.ALLTAGS;
        }
        return mTags.get(position-1).getName();
    }
}
