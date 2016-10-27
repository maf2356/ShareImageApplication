package cys.share.image.api.server;

import java.util.List;

import cys.share.image.entity.NavTag;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Administrator on 2016/10/27.
 */

public interface NaVTagsServer {

    @GET("api/timeline/navTags")
    Observable<List<String>> getNavTags();
}
