package cys.share.image.api;

import android.os.Handler;
import android.util.Log;

import com.google.common.eventbus.Subscribe;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cys.share.image.Constant;
import cys.share.image.api.custom.DefaultProgressListener;
import cys.share.image.api.custom.UploadFileRequestBody;
import cys.share.image.api.server.NaVTagsServer;
import cys.share.image.api.server.TagListServer;
import cys.share.image.api.server.UserRelevantServer;
import cys.share.image.entity.MyUploadImage;
import cys.share.image.entity.NavTag;
import cys.share.image.entity.ResponseMessage;
import cys.share.image.entity.TagContent;
import cys.share.image.entity.User;
import cys.share.image.entity.realm.MyUploadImageRealm;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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

    private static <T> Observable<T> creatObservable(Observable.OnSubscribe<T> onSubscribe, Subscriber<T> subscriber) {
        Observable<T> observable = Observable.create(onSubscribe)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(subscriber);
        return observable;
    }

    private static Retrofit createRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }

    /**
     * 获取导航Tag
     *
     * @param subscriber
     */
    public static void getNavTags(Subscriber<List<String>> subscriber) {
        Retrofit retrofit = createRetrofit();
        retrofit.create(NaVTagsServer.class)
                .getNavTags()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public static void getTagList(String token,String tag,int page,Subscriber<List<TagContent>> subscriber) {
        TagListServer server = createServer(TagListServer.class);
        server.getTagList(token,tag,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public static void register(String account, String nickName, String password, Subscriber<User> subscriber){
        UserRelevantServer relevantServer = createServer(UserRelevantServer.class);
        relevantServer.register(account,nickName,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public static void _public(String token,String ids,String content,String tags,Subscriber<TagContent> subscriber){
        UserRelevantServer relevantServer = createServer(UserRelevantServer.class);
        relevantServer.publicTagContent(token,ids,content,tags)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }

    public static void uploadImage(String filePath, String token, Subscriber<MyUploadImage> subscriber, Handler handler,int what){
        File file = new File(filePath);

        UploadFileRequestBody ufb = new UploadFileRequestBody(file,new DefaultProgressListener(handler, what));


        MultipartBody.Part body =
                MultipartBody.Part.createFormData("imageFile", file.getName(), ufb);

        RequestBody tokenBody =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), token);

        UserRelevantServer userRelevantServer = createServer(UserRelevantServer.class);
        userRelevantServer.uploadImage(tokenBody,body) .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    public static void uploadImages(List<String> filePath, String token, Subscriber<MyUploadImage> subscriber, Handler handler){
        List<Observable<MyUploadImage>> observableList = new ArrayList<>();
        UserRelevantServer userRelevantServer = createServer(UserRelevantServer.class);
        for (int i= 0;i<filePath.size();i++){
            File file = new File(filePath.get(i));
            UploadFileRequestBody ufb = new UploadFileRequestBody(file,new DefaultProgressListener(handler, i));
            MultipartBody.Part body =
                    MultipartBody.Part.createFormData("imageFile", file.getName(), ufb);
            RequestBody tokenBody =
                    RequestBody.create(
                            MediaType.parse("multipart/form-data"), token);
            observableList.add(userRelevantServer.uploadImage(tokenBody,body) .subscribeOn(Schedulers.io()));
        }

        Observable.merge(observableList).observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    public static void login(String account,String password,Subscriber<User> subscriber){
        UserRelevantServer relevantServer = createServer(UserRelevantServer.class);
        relevantServer.login(account,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    private static <T> T createServer(Class<T> t) {
        Retrofit retrofit = createRetrofit();
        return (T) retrofit.create(t);
    }
}
