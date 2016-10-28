package cys.share.image.entity;

import cys.share.image.entity.realm.BaseBean;
import cys.share.image.entity.realm.NavTagRealm;
import cys.share.image.entity.realm.imp.RealmTransaction;

/**
 * Created by Administrator on 2016/10/27.
 */

public class NavTag extends BaseBean implements RealmTransaction<NavTagRealm,NavTag>{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public NavTag toObject(NavTagRealm navTagRealm) {
        this.name = navTagRealm.getName();
        return this;
    }

    @Override
    public NavTagRealm toRealmObject() {
        NavTagRealm navTagRealm = new NavTagRealm();
        navTagRealm.setName(this.name);
        return navTagRealm;
    }
}
