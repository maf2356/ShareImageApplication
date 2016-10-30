package cys.share.image.auxiliary;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.DateFormat;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;

import java.text.SimpleDateFormat;
import java.util.Date;

import cys.share.image.Constant;
import cys.share.image.activity.LoginActivity;

/**
 * Created by Administrator on 2016/10/27.
 */

public class ShareImageAuxiliaryTool {

    private static final boolean DEBUG = true;
    private static final String TAG = "ShareImageLog";

    public static void log(String info){
        if(DEBUG){
            Log.i(TAG,info);
        }
    }

    public static int getWindowsWidth(Context ctx){
        WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }

    public static String transformationTime(long time){
        return new SimpleDateFormat("MM-dd HH:mm").format(new Date(time));
    }

    public static String showLikeCountAndCommentCount(int likeCount,int commentCount){
        return "总共有"+likeCount+"个人喜欢这张图片，一共有"+commentCount+"条评论";
    }

    public static void checkUserState(Context ctx){
//        SharedPreferences sharedPreferences = ctx.getSharedPreferences(Constant.PREFS_NAME, 0);
//        String token = sharedPreferences.getString(Constant.TOKEN, "");
//        if(TextUtils.isEmpty(token)){
//            //go to login
//            Intent loginIntent = new Intent(ctx, LoginActivity.class);
//            ctx.startActivity(loginIntent,Constant.LOGIN_SUCCESS);
//            return;
//        }
    }

    public static void saveToken(Context ctx,String token){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(Constant.PREFS_NAME, 0);
        sharedPreferences.edit().putString(Constant.TOKEN,token);
    }

    public static ProgressDialog createProgressDialog(Context ctx,String title,String message){
        return ProgressDialog.show(ctx,title,message);
    }
}
