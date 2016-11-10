package cys.share.image.api.server;

import java.util.List;

import cys.share.image.entity.TContent;
import cys.share.image.entity.TagContent;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/10/27.
 */
public interface TagListServer {

    @GET("api/timeline/list")
    Observable<TagContent> getTagList(@Query("token") String token, @Query("tag")String tag, @Query("page") int page);

    @GET("api/timeline/detail/{timelineId}")
    Observable<TContent> getTContent(@Path("timelineId") String timelineId,@Query("token")String token);

}
