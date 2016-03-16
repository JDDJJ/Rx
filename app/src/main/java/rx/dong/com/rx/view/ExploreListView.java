package rx.dong.com.rx.view;

import java.util.List;

import rx.dong.com.rx.model.MediaListBean;

/**
 * Created by fangxiao on 16/1/28.
 */
public interface ExploreListView extends MvpView {

    void refresh(List<MediaListBean.MediaList> data);

    void loadMore(List<MediaListBean.MediaList> data);

}