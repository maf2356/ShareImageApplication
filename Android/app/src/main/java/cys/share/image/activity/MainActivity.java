package cys.share.image.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

import java.util.List;

import cys.share.image.Constant;
import cys.share.image.R;
import cys.share.image.api.ShareImageApi;
import cys.share.image.auxiliary.ShareImageAuxiliaryTool;
import cys.share.image.database.ShareImageRealm;
import cys.share.image.databinding.ActivityMainBinding;
import cys.share.image.entity.NavTag;
import cys.share.image.entity.User;
import cys.share.image.fragment.GenericFragment;
import cys.share.image.listener.ShareImageEventListener;
import rx.Subscriber;


/**
 * TODO
 */
public class MainActivity extends AppCompatActivity {

    MaterialViewPager mMaterialViewPager;
    Toolbar toolbar;
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private List<NavTag> mNavtags;
    private View mDrawerLayout;
    ActivityMainBinding mBinding;
    ShareImageEventListener mEventListener;
    User mUser = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mEventListener = new ShareImageEventListener();
        mMaterialViewPager = mBinding.materialViewPager;
        mDrawer = mBinding.drawerLayout;
        mDrawerLayout = mBinding.otherRootView;
        toolbar = mMaterialViewPager.getToolbar();
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            final ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowHomeEnabled(true);
                actionBar.setDisplayShowTitleEnabled(true);
                actionBar.setDisplayUseLogoEnabled(false);
                actionBar.setHomeButtonEnabled(true);
            }

            toolbar.setLayoutParams(new RelativeLayout.LayoutParams(toolbar.getWidth(), getStatusBarHeight()));
        }
        mNavtags = ShareImageRealm.getInstance(this.getApplicationContext()).queryNavTags();

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, 0, 0);
        mDrawer.setDrawerListener(mDrawerToggle);
        mMaterialViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                return GenericFragment.newInstance(getPageTitle(position).toString());
            }

            @Override
            public int getCount() {
                return mNavtags.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if (position == 0) {
                    return Constant.ALLTAGS;
                }
                return mNavtags.get(position).getName();
            }
        });

        mMaterialViewPager.getViewPager().setOffscreenPageLimit(mMaterialViewPager.getViewPager().getAdapter().getCount());
        mMaterialViewPager.getPagerTitleStrip().setViewPager(mMaterialViewPager.getViewPager());
        mMaterialViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                return HeaderDesign.fromColorResAndUrl(
                        R.color.colorPrimaryDark,
                        "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
            }
        });

        User user = ShareImageRealm.getInstance(MainActivity.this).queryUserInfo();
        if(user!=null){
            mBinding.setUser(user);
        }
        mDrawer.openDrawer(mDrawerLayout);
        mBinding.setListener(mEventListener);
    }

    public int getStatusBarHeight() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT)
            return 0;

        Resources resources = this.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0)
            return resources.getDimensionPixelSize(resourceId);
        return 0;
    }

    public void mainLogin(View view){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivityForResult(intent,Constant.LOGIN_SUCCESS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Constant.LOGIN_SUCCESS){
            mUser = ShareImageRealm.getInstance(MainActivity.this).queryUserInfo();
            if(mUser!=null){
                mBinding.setUser(mUser);
            }

        }
    }
}
