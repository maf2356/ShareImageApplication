package cys.share.image.api.server;

import java.util.List;

import cys.share.image.entity.TagContent;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/10/27.
 */
public interface TagListServer {

    @GET("api/timeline/list")
    Observable<List<TagContent>> getTagList(@Query("token") String token, @Query("tag")String tag, @Query("page") int page);
}
