package cys.share.image.entity;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

import cys.share.image.entity.realm.BaseBean;
import cys.share.image.entity.realm.TagContentRealm;
import cys.share.image.entity.realm.imp.RealmTransaction;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Administrator on 2016/10/27.
 */
public class TagContent extends BaseBean {

    private List<TContent> datas;

    private long dataTime;

    public List<TContent> getDatas() {
        return datas;
    }

    public void setDatas(List<TContent> datas) {
        this.datas = datas;
    }

    public long getDataTime() {
        return dataTime;
    }

    public void setDataTime(long dataTime) {
        this.dataTime = dataTime;
    }

}
