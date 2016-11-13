package cys.share.image.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
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
import cys.share.image.entity.TagContent;
import cys.share.image.entity.User;
import cys.share.image.entity.realm.MyUploadImageRealm;
import cys.share.image.imagepicker.PhotoPickerActivity;
import cys.share.image.view.TagsEditText;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/2.
 */

public class ReleaseActivity extends BaseActivity {

    private ActivityReleaseBinding mBinding;
    private List<String> mImagePaths, mRealImagePaths;
    private UploadImageAdapter adapter;
    String imgsId = "";
    private Observable mObservable;
    private Subscriber mSubscreiber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_release);
        mImagePaths = getIntent().getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
        adapter = new UploadImageAdapter(this, mImagePaths);
        mBinding.gridView.setAdapter(adapter);
        mBinding.toolbar.inflateMenu(R.menu.menu_release);
        mBinding.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_public:
                        ShareImageAuxiliaryTool.showSnackBar(mBinding.getRoot(), "正在发布，请稍等...", -1);
                        mObservable = createObservable();
                        mObservable.subscribe(new Subscriber<String>() {
                            @Override
                            public void onCompleted() {
                                _uploadImg();
                            }

                            @Override
                            public void onError(Throwable e) {
                            }

                            @Override
                            public void onNext(String o) {
                            }
                        });
                        break;
                }
                return false;
            }
        });
//        mBinding.button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View view) {
//                mObservable = createObservable();
//                mObservable.subscribe(new Subscriber<String>() {
//                    @Override
//                    public void onCompleted() {
//                        Snackbar.make(mBinding.getRoot(),"图片处理成功",Snackbar.LENGTH_SHORT).show();
//                        _uploadImg();
//                    }
//                    @Override
//                    public void onError(Throwable e) {}
//                    @Override
//                    public void onNext(String o) { }
//                });
//            }
//        });

        mBinding.etTag.setTagsTextColor(R.color.tag_text_color);
        mBinding.etTag.setTagsWithSpacesEnabled(true);
        mBinding.etTag.setTagsBackground(R.color.tag_background);
//        ShareImageAuxiliaryTool.handleImages(mImagePaths.get(0));
    }



    private Observable createObservable() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                mRealImagePaths = new ArrayList<String>();
                for (String path : mImagePaths) {
                    mRealImagePaths.add(ShareImageAuxiliaryTool.handleImages(ReleaseActivity.this, path));
                }
                subscriber.onCompleted();
            }
        }).observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread());

    }

    private void _uploadImg() {
        ShareImageAuxiliaryTool.showSnackBar(mBinding.getRoot(), "正在上传图片，请稍等...", 0);
        imgsId = "";
        if (mRealImagePaths == null) {
            return;
        }
        ShareImageApi.uploadImages(mRealImagePaths, getUser().getToken(), new Subscriber<MyUploadImage>() {
            @Override
            public void onCompleted() {
                _public();
                ShareImageAuxiliaryTool.log("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                ShareImageAuxiliaryTool.log(e.getMessage());
            }

            @Override
            public void onNext(MyUploadImage myUploadImage) {
                imgsId += (myUploadImage.getId() + ",");
                ShareImageAuxiliaryTool.log(new Gson().toJson(myUploadImage));
            }
        }, mHandler);
    }

    private void _public() {
        String content = mBinding.etContext.getText().toString();
        String tags = ShareImageAuxiliaryTool.getTags(mBinding.etTag.getText().toString());


        ShareImageApi._public(getUser().getToken(), imgsId, content, tags, new Subscriber<TagContent>() {
            @Override
            public void onCompleted() {
                ShareImageAuxiliaryTool.showSnackBar(mBinding.getRoot(), "发布成功", -1, new Snackbar.Callback() {
                    @Override
                    public void onDismissed(Snackbar snackbar, int event) {
                        super.onDismissed(snackbar, event);
                        sendBroadcast(new Intent(Constant.REFRESH_ACTION));
                        ReleaseActivity.this.finish();
                    }
                });

            }

            @Override
            public void onError(Throwable e) {
                ShareImageAuxiliaryTool.log(e.getMessage());
            }

            @Override
            public void onNext(TagContent tagContent) {
                ShareImageAuxiliaryTool.log(tagContent.toString());
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
            adapter.notifyUploadProgressSetChanged(msg.what, msg.arg1);
        }
    };


    @Override
    public void onBackPressed() {
        setResult(111);
        this.finish();
    }
}
