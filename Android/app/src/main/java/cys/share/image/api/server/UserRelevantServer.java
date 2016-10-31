package cys.share.image.api.server;

import java.util.List;

import cys.share.image.entity.ResponseMessage;
import cys.share.image.entity.User;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
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
}
