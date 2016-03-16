package rx.dong.com.rx.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import rx.Subscriber;
import rx.dong.com.rx.api.MediaApi;
import rx.dong.com.rx.api.RxService;
import rx.dong.com.rx.model.MediaListBean;
import rx.dong.com.rx.view.ExploreListView;
import rx.schedulers.Schedulers;

/**
 * Created by SkyEyesStion on 2016/3/14.
 */
public class MediaPresenter extends BasePresenter<ExploreListView> {

    private MediaApi exploreListService;
    private int page;

    @Inject
    public MediaPresenter(Context context,int i) {
        //construct
        exploreListService = RxService.createApi(MediaApi.class);
        page =i;
    }

    public void loadList(final int page) {
        exploreListService.getMediaList("0", page)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<MediaListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e.getMessage());
                    }

                    @Override
                    public void onNext(MediaListBean mediaListBean) {
                        if (page == 1) {
                            getMvpView().refresh(mediaListBean.getMediaList());
                        } else {
                            getMvpView().loadMore(mediaListBean.getMediaList());
                        }
                    }
                });
    }
}
