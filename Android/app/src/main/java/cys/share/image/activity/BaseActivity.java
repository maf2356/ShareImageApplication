package cys.share.image.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cys.share.image.auxiliary.ShareImageAuxiliaryTool;
import cys.share.image.database.ShareImageRealm;
import cys.share.image.entity.User;

/**
 * Created by Administrator on 2016/11/7.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private User mUser;


    public User getUser() {
        if(mUser == null){
            mUser = ShareImageRealm.getInstance(this).queryUserInfo();
        }
        return mUser;
    }

    public void setUser(User mUser) {
        this.mUser = mUser;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        User user = ShareImageRealm.getInstance(this).queryUserInfo();
        setUser(user);
    }
}
