package cys.share.image.auxiliary;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.icu.text.DateFormat;
import android.media.ExifInterface;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cys.share.image.Constant;
import cys.share.image.activity.LoginActivity;
import cys.share.image.view.TagsEditText;

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

    /**
     *
     * @param rootView
     * @param message
     * @param i  1 == long ,-1 short
     * @param callback
     */
    public static void showSnackBar(View rootView, String message,int i, Snackbar.Callback callback){
        Snackbar.make(rootView,message,i).setCallback(callback).show();
    }

    /**
     *
     * @param rootView
     * @param i 1 == long ,-1 short
     * @param message
     */
    public static void showSnackBar(View rootView, String message,int i){
        Snackbar.make(rootView,message,i).setCallback(null).show();
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

    public static void requestPermission(Activity context){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            int hasPermissions = context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            if(hasPermissions!= PackageManager.PERMISSION_GRANTED){
                context.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},0);
            }

            int hasWritePermissions = context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if(hasWritePermissions!= PackageManager.PERMISSION_GRANTED){
                context.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
            }
        }
    }


    public static String handleImages(Context context,String imgpath){
        String path = imgpath;
        String name = "";
        String[] split = imgpath.split("/");
        name = split[split.length-1];
        File file = new File(context.getApplicationContext().getExternalFilesDir(null)+File.separator+name);
        Bitmap bm = BitmapFactory.decodeFile(imgpath);
        int digree = 0;
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(imgpath);
        } catch (IOException e) {
            e.printStackTrace();
            exif = null;
        }
        if (exif != null) {
            // 读取图片中相机方向信息
            int ori = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_UNDEFINED);
            // 计算旋转角度
            switch (ori) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    digree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    digree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    digree = 270;
                    break;
                default:
                    digree = 0;
                    break;
            }
        }
        if (digree != 0) {
            // 旋转图片
            Matrix m = new Matrix();
            m.postRotate(digree);
            bm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(),
                    bm.getHeight(), m, true);
            try {
                file.createNewFile();
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                bm.compress(Bitmap.CompressFormat.JPEG,100,bos);
                bos.flush();
                bos.close();
                path = file.getPath();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return path;
    }


    public static String getTags(String sTags){
        String _publicTags = "";
        String [] separatorTags = sTags.split(TagsEditText.SEPARATOR);
        for (String tag:
                separatorTags) {
            _publicTags+= tag+",";
        }
        if(_publicTags.endsWith(",")){
            _publicTags = _publicTags.substring(0,_publicTags.length()-1);
        }
        ShareImageAuxiliaryTool.log(_publicTags);
        return _publicTags;
    }

}
