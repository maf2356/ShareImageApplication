package cys.share.image.entity;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import cys.share.image.BR;
import cys.share.image.entity.realm.BaseBean;
import cys.share.image.entity.realm.TagContentRealm;
import cys.share.image.entity.realm.UserRealm;
import cys.share.image.entity.realm.imp.RealmTransaction;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Administrator on 2016/10/27.
 * {
 "id": 26,
 "account": "yusongying1",
 "nickName": "房管局规划部1",
 "avatar": null,
 "followCount": 0,
 "fansCount": 0,
 "registerTime": 1477837956,
 "token": "drAAtWDZu26ZzH0gGW94bmN5HuXgmPEP",
 "thirdParty": []
 }
 */
public class User extends BaseObservable implements RealmTransaction<UserRealm,User> {

    private int id;

    private String nickName;

    private String avatar;

    private int followCount;

    private int fansCount;

    private long registerTime;

    private String token;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    @Bindable
    public void setNickName(String nickName) {
        this.nickName = nickName;
        this.notifyPropertyChanged(BR.nickName);
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getFollowCount() {
        return followCount;
    }

    public void setFollowCount(int followCount) {
        this.followCount = followCount;
    }

    public int getFansCount() {
        return fansCount;
    }

    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }

    public long getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(long registerTime) {
        this.registerTime = registerTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public User toObject(UserRealm userRealm) {
        this.avatar = userRealm.getAvatar();
        this.fansCount = userRealm.getFansCount();
        this.followCount = userRealm.getFollowCount();
        this.id = userRealm.getId();
        this.nickName = userRealm.getNickName();
        this.token = userRealm.getToken();
        this.registerTime = userRealm.getRegisterTime();
        return this;
    }

    @Override
    public UserRealm toRealmObject() {
        UserRealm userRealm = new UserRealm();
        userRealm.setId(this.id);
        userRealm.setAvatar(this.avatar);
        userRealm.setFansCount(this.fansCount);
        userRealm.setFollowCount(this.followCount);
        userRealm.setNickName(this.nickName);
        userRealm.setRegisterTime(this.registerTime);
        userRealm.setToken(this.token);
        return userRealm;
    }
}
