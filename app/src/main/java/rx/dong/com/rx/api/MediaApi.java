package rx.dong.com.rx.api;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;
import rx.dong.com.rx.model.MediaListBean;

/**
 * Created by SkyEyesStion on 2016/3/14.
 */
public interface MediaApi {

    @FormUrlEncoded
    @POST("MappedTask/getMappedRecruit")
    Observable<MediaListBean> getMediaList
            (@Field("task_id") String task_id, @Field("page") int page);
}
