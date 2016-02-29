package rx.dong.com.rx.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;
import rx.dong.com.rx.model.WithDrawRecord;

/**
 * Created by SkyEyesStion on 2016/2/26.
 */
public interface RxApi {

    @FormUrlEncoded
    @POST("Record/getWithdrawCashRecord")
    Observable<WithDrawRecord> getWithDrawRecord(@Field("user_id") String userId, @Field("page")
    int pageNum);

    @FormUrlEncoded
    @POST("Record/getWithdrawCashRecord")
    Call<WithDrawRecord> callWithDrawRecord(@Field("user_id") String userId, @Field("page")
    int pageNum);
}
