package cys.share.image.api.custom.imp;

/**
 * Created by Administrator on 2016/11/2.
 */
public interface ProgressListener {
    void onProgress(long hasWrittenLen, long totalLen, boolean hasFinish);
}