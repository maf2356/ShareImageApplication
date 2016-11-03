package cys.share.image.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.gson.Gson;

import java.util.List;

import cys.share.image.Constant;
import cys.share.image.R;
import cys.share.image.ShareImageApplication;
import cys.share.image.adapter.UploadImageAdapter;
import cys.share.image.api.ShareImageApi;
import cys.share.image.auxiliary.ShareImageAuxiliaryTool;
import cys.share.image.database.ShareImageRealm;
import cys.share.image.databinding.ActivityReleaseBinding;
import cys.share.image.entity.MyUploadImage;
import cys.share.image.entity.User;
import cys.share.image.entity.realm.MyUploadImageRealm;
import cys.share.image.imagepicker.PhotoPickerActivity;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/11/2.
 */

public class ReleaseActivity extends AppCompatActivity {

    private ActivityReleaseBinding mBinding;
    private List<String> mImagePaths;
    private UploadImageAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_release);
        mImagePaths = getIntent().getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
        adapter = new UploadImageAdapter(this,mImagePaths);
        mBinding.gridView.setAdapter(adapter);
        mBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareImageApi.uploadImages(mImagePaths, ((ShareImageApplication)getApplication()).getToken(), new Subscriber<MyUploadImage>() {
                    @Override
                    public void onCompleted() {
                        ShareImageAuxiliaryTool.log("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        ShareImageAuxiliaryTool.log(e.getMessage());
                    }

                    @Override
                    public void onNext(MyUploadImage myUploadImage) {
                        ShareImageAuxiliaryTool.log(new Gson().toJson(myUploadImage));
                    }
                },mHandler);

//                ShareImageApi.uploadImage(mImagePaths.get(0), "xFNuCZhGRwW5KE9tUA8tPndZOxChcAVY", new Subscriber<MyUploadImage>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        ShareImageAuxiliaryTool.log(mImagePaths.toString());
//                    }
//
//                    @Override
//                    public void onNext(MyUploadImage myUploadImageRealm) {
//                        ShareImageAuxiliaryTool.log(mImagePaths.toString());
//                    }
//                },mHandler,0);
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
//            final int msgWhat = Constant.UPLOAD_WHAT;
//            switch (msg.what) {
//                case msgWhat:
//                    if (msg.arg1 > 0) {
//                        ShareImageAuxiliaryTool.log(msg.arg1+"");
//                    }
//                    break;
//            }
            adapter.notifyUploadProgressSetChanged(msg.what,msg.arg1);





        }
    };


}
