package cys.share.image.auxiliary;

import android.content.Context;
import android.util.Log;
import android.view.WindowManager;

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
}
