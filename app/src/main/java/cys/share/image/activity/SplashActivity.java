package cys.share.image.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import cys.share.image.R;
import cys.share.image.api.ShareImageApi;
import cys.share.image.auxiliary.ShareImageAuxiliaryTool;
import cys.share.image.database.ShareImageRealm;
import cys.share.image.entity.NavTag;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/10/27.
 */

public class SplashActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_splash);
        init();
    }


    void init(){
        ShareImageApi.getNavTags(new Subscriber<List<String>>() {
            @Override
            public void onCompleted() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                SplashActivity.this.finish();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                ShareImageAuxiliaryTool.log(e.getMessage());
            }

            @Override
            public void onNext(List<String> navTags) {
                for (String name:navTags
                     ) {
                    NavTag tag = new NavTag();
                    tag.setName(name);
                    ShareImageRealm.getInstance(SplashActivity.this.getApplicationContext()).saveNavTags(tag);
                }
            }
        });
    }
}
