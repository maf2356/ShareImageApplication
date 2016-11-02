package cys.share.image.listener;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import com.kogitune.activity_transition.ActivityTransitionLauncher;
import com.kogitune.activity_transition.fragment.FragmentTransitionLauncher;

import cys.share.image.R;
import cys.share.image.activity.LargeViewActivity;
import cys.share.image.auxiliary.ShareImageAuxiliaryTool;
import cys.share.image.entity.TagContent;
import cys.share.image.fragment.LargeViewFragment;

/**
 * Created by Administrator on 2016/10/29.
 */
public class ShareImageEventListener {

    //go to login activity
    public void login(View view){
        ShareImageAuxiliaryTool.checkUserState(view.getContext());
    }

    //login account ,now just for test
    public void go2Login(View view){

    }

    public void showLargeView(View view, FragmentActivity activity, TagContent item){
        Intent intent = LargeViewActivity.newIntent(activity, item);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity, view, LargeViewActivity.TRANSIT_PIC);
        try {
            ActivityCompat.startActivity(activity, intent, optionsCompat.toBundle());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            activity.startActivity(intent);
        }
    }
}
