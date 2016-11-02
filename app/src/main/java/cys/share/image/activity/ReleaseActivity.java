package cys.share.image.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.List;

import cys.share.image.Constant;
import cys.share.image.R;
import cys.share.image.api.ShareImageApi;
import cys.share.image.auxiliary.ShareImageAuxiliaryTool;
import cys.share.image.databinding.ActivityReleaseBinding;
import cys.share.image.entity.MyUploadImage;
import cys.share.image.entity.realm.MyUploadImageRealm;
import cys.share.image.imagepicker.PhotoPickerActivity;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/11/2.
 */

public class ReleaseActivity extends AppCompatActivity {

    private ActivityReleaseBinding mBinding;
    private List<String> mImagePaths;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_release);
        mImagePaths = getIntent().getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);

        mBinding.shareImageProgressBar.setImage(mImagePaths.get(0));
        mBinding.shareImageProgressBar.setOpacity(true);
        mBinding.shareImageProgressBar.setColor("#C9C9C9");
        mBinding.shareImageProgressBar.setProgress(0);
        mBinding.shareImageProgressBar.setWidth(8);
        mBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareImageApi.uploadImage(mImagePaths.get(0), "xFNuCZhGRwW5KE9tUA8tPndZOxChcAVY", new Subscriber<MyUploadImage>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ShareImageAuxiliaryTool.log(mImagePaths.toString());
                    }

                    @Override
                    public void onNext(MyUploadImage myUploadImageRealm) {
                        ShareImageAuxiliaryTool.log(mImagePaths.toString());
                    }
                },mHandler);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            final int msgWhat = Constant.UPLOAD_WHAT;
            switch (msg.what) {
                case msgWhat:
                    if (msg.arg1 > 0) {
                        mBinding.shareImageProgressBar.setProgress(msg.arg1);
                        ShareImageAuxiliaryTool.log(msg.arg1+"");
                    }
                    break;
            }
        }
    };
}
