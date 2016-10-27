package cys.share.image.api;

import java.util.List;

import cys.share.image.api.server.NaVTagsServer;
import cys.share.image.entity.NavTag;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/27.
 */

public class ShareImageApi {

    private final static String BASEURL = "http://tu42.com/";

    private static<T> Observable<T> creatObservable(Observable.OnSubscribe<T> onSubscribe, Subscriber<T> subscriber){
        Observable<T>  observable =  Observable.create(onSubscribe)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(subscriber);
        return observable;
    }

    private static Retrofit createRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }

    public static void getNavTags(Subscriber<List<String>> subscriber){
        Retrofit retrofit = createRetrofit();
        retrofit.create(NaVTagsServer.class)
                .getNavTags()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
