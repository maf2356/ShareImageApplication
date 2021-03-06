package cys.share.image.listener;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import com.kogitune.activity_transition.ActivityTransitionLauncher;
import com.kogitune.activity_transition.fragment.FragmentTransitionLauncher;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import cys.share.image.Constant;
import cys.share.image.R;
import cys.share.image.activity.CommentActivity;
import cys.share.image.activity.LargeViewActivity;
import cys.share.image.activity.MainActivity;
import cys.share.image.activity.ShareImageUserModifyActivity;
import cys.share.image.adapter.GenericAdapter;
import cys.share.image.api.ShareImageApi;
import cys.share.image.auxiliary.ShareImageAuxiliaryTool;
import cys.share.image.entity.CommentDatas;
import cys.share.image.entity.TContent;
import cys.share.image.entity.TagContent;
import cys.share.image.fragment.LargeViewFragment;
import cys.share.image.imagepicker.SelectModel;
import cys.share.image.imagepicker.intent.PhotoPickerIntent;

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

    public void showLargeView(final View view, final FragmentActivity activity, final TContent item){

        Picasso.with(view.getContext()).load(item.getCover().getMiddleUrl()).fetch(new Callback() {

            @Override public void onSuccess() {
                Intent intent = LargeViewActivity.newIntent(activity, item);
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        activity, view, LargeViewActivity.TRANSIT_PIC);
//                try {
//                    ActivityCompat.startActivity(activity, intent, optionsCompat.toBundle());
//                } catch (IllegalArgumentException e) {
//                    e.printStackTrace();
                    activity.startActivity(intent);
//                }
            }


            @Override public void onError() {
            }
        });
    }

    public void motifyAvatar(final View view,final FragmentActivity activity){
        PhotoPickerIntent intent = new PhotoPickerIntent(view.getContext());
        intent.setSelectModel(SelectModel.SINGLE);
        intent.setShowCarema(true);
        intent.setFrom(Constant.PHOTOPICKER_AVATAR);
        activity.startActivityForResult(intent, Constant.MODIFYCODE);
    }

    public void modifyUserNick(final View view,FragmentActivity activity){
        Intent intent = new Intent(activity, ShareImageUserModifyActivity.class);
        intent.putExtra(Constant.MODIFY,Constant.MODIFY_NICKNAME);
        activity.startActivityForResult(intent,Constant.MODIFYCODE);
    }

    public void modifyUserPassword(final View view,FragmentActivity activity){
        Intent intent = new Intent(activity, ShareImageUserModifyActivity.class);
        intent.putExtra(Constant.MODIFY,Constant.MODIFY_PASSWORD);
        activity.startActivityForResult(intent,Constant.MODIFYCODE);
    }

    public void go2Comment(View view,TContent item){
        Intent intent = new Intent(view.getContext(), CommentActivity.class);
        intent.putExtra("item",item);
        view.getContext().startActivity(intent);
    }

    public void go2MoreComment(View view , CommentDatas commentDatas){
        ShareImageAuxiliaryTool.showSnackBar(view,"1123321", Snackbar.LENGTH_LONG);
    }

    public void commitLike(View view, String token, TContent item, GenericAdapter adapter){
        ShareImageApi.commitLike(token,item.getId(),(o)->{
            if(o!=null&&o.isLiked()){
                item.setLiked(o.isLiked());
                item.setLikeCount(o.getLikeCount());
                adapter.notifyDataSetChanged();
                ShareImageAuxiliaryTool.showSnackBar(view,"点赞成功",Snackbar.LENGTH_SHORT);
            }
        });
    }
}
