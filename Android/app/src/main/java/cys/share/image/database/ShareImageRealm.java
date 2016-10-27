package cys.share.image.database;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cys.share.image.auxiliary.ShareImageAuxiliaryTool;
import cys.share.image.entity.NavTag;
import cys.share.image.entity.realm.NavTagRealm;
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
