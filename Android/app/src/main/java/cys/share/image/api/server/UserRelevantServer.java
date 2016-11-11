package cys.share.image.api.server;

import java.util.List;

import cys.share.image.entity.MyUploadImage;
import cys.share.image.entity.ResponseMessage;
import cys.share.image.entity.TagContent;
import cys.share.image.entity.User;
import cys.share.image.entity.realm.MyUploadImageRealm;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/10/30.
 */
public interface UserRelevantServer {


    @POST("api/user/register")
    Observable<User> register(@Query("account") String account, @Query("nickName")String nickName, @Query("password")String password);

    @POST("api/user/login")
    Observable<User> login(@Query("account") String account, @Query("password")String password);

    @Multipart
    @POST("api/timeline/uploadImage")
    Observable<MyUploadImage> uploadImage(@Part("token") RequestBody token,
                                          @Part MultipartBody.Part file);

    @POST("api/timeline/public")
    Observable<TagContent> publicTagContent(@Query("token") String token,@Query("imageIds") String imgIds,@Query("content")String content,@Query("tags")String tags);

    @Multipart
    @POST("api/user/avatar")
    Observable<User> modifyAvatar(@Part("token") RequestBody token,@Part MultipartBody.Part file);


    @POST("api/user/changeInfo")
    Observable<User> modifyUserNick(@Query("token")String token,@Query("nickName")String nickName);

    @POST("api/user/changePassword")
    Observable<ResponseMessage> modifyUserPassword(@Query("token")String token,@Query("password")String password);

    @GET("api/timeline/comment/list")
    Observable
}
