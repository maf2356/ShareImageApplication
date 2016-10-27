package cys.share.image.entity.realm.imp;

/**
 * Created by Administrator on 2016/10/27.
 */
public interface RealmTransaction<T,R> {

    R toObject(T t);

    T toRealmObject();

}
