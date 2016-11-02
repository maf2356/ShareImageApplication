package cys.share.image.api.custom;

import android.os.Handler;
import android.os.Message;

import cys.share.image.api.custom.imp.ProgressListener;
import cys.share.image.auxiliary.ShareImageAuxiliaryTool;

/**
 * Created by Administrator on 2016/11/2.
 */
public class DefaultProgressListener implements ProgressListener{

    private Handler mHandler;

    private int mIndex;

    public DefaultProgressListener(Handler mHandler, int mIndex) {
        this.mHandler = mHandler;
        this.mIndex = mIndex;
    }

    @Override
    public void onProgress(long hasWrittenLen, long totalLen, boolean hasFinish) {
        ShareImageAuxiliaryTool.log("----the current " + hasWrittenLen + "----" + totalLen + "-----" + (hasWrittenLen * 100 / totalLen));

        int percent = (int) (hasWrittenLen * 100 / totalLen);
        if (percent > 100) percent = 100;
        if (percent < 0) percent = 0;

        Message msg = Message.obtain();
        msg.what = mIndex;
        msg.arg1 = percent;
        mHandler.sendMessage(msg);
    }

}
