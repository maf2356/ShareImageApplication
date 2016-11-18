package cys.share.image;

import android.app.Application;

/**
 * Created by Administrator on 2016/10/30.
 */
public class ShareImageApplication extends Application{

    private boolean isLogin = false;

    private String token;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
