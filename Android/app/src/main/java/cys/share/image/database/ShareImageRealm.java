package cys.share.image.database;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cys.share.image.auxiliary.ShareImageAuxiliaryTool;
import cys.share.image.entity.NavTag;
import cys.share.image.entity.TContent;
import cys.share.image.entity.User;
import cys.share.image.entity.realm.NavTagRealm;
import cys.share.image.entity.realm.UserRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by Administrator on 2016/10/27.
 */
public class ShareImageRealm {

    private Realm mRealm;

    private static ShareImageRealm ourInstance = null;

    public static synchronized ShareImageRealm getInstance(Context ctx) {
        if (ourInstance == null) {
            ourInstance = new ShareImageRealm(ctx);
        }
        return ourInstance;
    }


    private ShareImageRealm(Context ctx) {
        buildRealm(ctx);
    }

    public <T extends RealmObject> void saveToDatabase(Context ctx, T t) {
        buildRealm(ctx);
    }

    private void buildRealm(Context ctx) {
        if (mRealm == null) {
            mRealm = Realm.getInstance(new RealmConfiguration.Builder(ctx).name("shareimage.realm").build());
        }
    }

    public void saveTContent(TContent tContent){

    }

    public void saveUserInfo(User user){
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(user.toRealmObject());
        mRealm.commitTransaction();
    }

    public User queryUserInfo(){
        UserRealm userRealm = mRealm.where(UserRealm.class).findFirst();
        User user = null;
        if(userRealm!=null){
            user = new User();
            user.toObject(userRealm);
        }
        return user;
    }

    public void saveNavTags(NavTag data) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(data.toRealmObject());
        mRealm.commitTransaction();
    }

    public List<NavTag> queryNavTags() {
        RealmResults<NavTagRealm> navTags = mRealm.where(NavTagRealm.class).findAll();
        List<NavTag> data = new ArrayList<>();
        for (NavTagRealm tag:navTags
             ) {
            NavTag navTag = new NavTag();
            data.add(navTag.toObject(tag));
            ShareImageAuxiliaryTool.log(tag.getName());
        }
        return data;
    }
}
