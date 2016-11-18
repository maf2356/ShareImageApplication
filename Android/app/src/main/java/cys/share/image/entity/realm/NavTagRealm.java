package cys.share.image.entity.realm;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import cys.share.image.entity.NavTag;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Administrator on 2016/10/27.
 */
public class NavTagRealm extends RealmObject{

    @PrimaryKey
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
