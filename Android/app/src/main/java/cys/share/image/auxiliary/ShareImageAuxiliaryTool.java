package cys.share.image.auxiliary;

import android.util.Log;

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
}
