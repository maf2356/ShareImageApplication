package cys.share.image.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

import java.util.List;

import cys.share.image.Constant;
import cys.share.image.R;
import cys.share.image.auxiliary.ShareImageAuxiliaryTool;
import cys.share.image.database.ShareImageRealm;
import cys.share.image.databinding.ActivityMainBinding;
import cys.share.image.entity.NavTag;
import cys.share.image.entity.User;
import cys.share.image.fragment.GenericFragment;
import cys.share.image.imagepicker.SelectModel;
import cys.share.image.imagepicker.intent.PhotoPickerIntent;
import cys.share.image.listener.ShareImageEventListener;


/**
 * TODO
 */
public class MainActivity extends AppCompatActivity {

    MaterialViewPager mMaterialViewPager;
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private List<NavTag> mNavtags;
    private View mDrawerLayout;
    ActivityMainBinding mBinding;
    ShareImageEventListener mEventListener;
    User mUser = new User();
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mEventListener = new ShareImageEventListener();
        mMaterialViewPager = mBinding.materialViewPager;
        mDrawer = mBinding.drawerLayout;
        mDrawerLayout = mBinding.otherRootView;
        ShareImageAuxiliaryTool.requestPermission(this);
//
//        mBinding.toolbar.setNavigationIcon(R.mipmap.ic_launcher);
//        setSupportActionBar(mBinding.toolbar);
//
//        final ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setDisplayShowHomeEnabled(true);
//            actionBar.setDisplayShowTitleEnabled(true);
//            actionBar.setDisplayUseLogoEnabled(false);
//            actionBar.setHomeButtonEnabled(true);
//        }
        mMaterialViewPager.getToolbar().inflateMenu(R.menu.menu_main);
        mMaterialViewPager.getToolbar().setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_settings:
                        PhotoPickerIntent intent = new PhotoPickerIntent(MainActivity.this);
                        intent.setSelectModel(SelectModel.MULTI);
                        intent.setShowCarema(true); // 是否显示拍照
                        intent.setMaxTotal(1); // 最多选择照片数量，默认为6
//                        intent.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
                        startActivity(intent);

//                        startActivity(new Intent(MainActivity.this,ReleaseActivity.class));
                        break;
                }
                return true;
            }
        });
//        toolbar = mMaterialViewPager.getToolbar();
//        if (toolbar != null) {
//            setSupportActionBar(toolbar);
//            final ActionBar actionBar = getSupportActionBar();
//            if (actionBar != null) {
//                actionBar.setDisplayHomeAsUpEnabled(true);
//                actionBar.setDisplayShowHomeEnabled(true);
//                actionBar.setDisplayShowTitleEnabled(true);
//                actionBar.setDisplayUseLogoEnabled(false);
//                actionBar.setHomeButtonEnabled(true);
//            }

//            toolbar.setLayoutParams(new RelativeLayout.LayoutParams(toolbar.getWidth(), getStatusBarHeight()));
//        }
        mNavtags = ShareImageRealm.getInstance(this.getApplicationContext()).queryNavTags();

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer,toolbar, 0, 0);
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
    //此方法定义Menu的布局样式，返回false则不显示Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                Toast.makeText(this, "123", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}