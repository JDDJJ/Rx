package rx.dong.com.rx.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.FrameLayout;

import com.mxn.soul.flowingdrawer_core.FlowingView;
import com.mxn.soul.flowingdrawer_core.LeftDrawerLayout;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.dong.com.rx.R;
import rx.dong.com.rx.api.RxApi;
import rx.dong.com.rx.api.RxService;
import rx.dong.com.rx.injector.component.DaggerMainActivityComponent;
import rx.dong.com.rx.injector.module.MainActivityModule;
import rx.dong.com.rx.model.MediaListBean;
import rx.dong.com.rx.model.WithDrawRecord;
import rx.dong.com.rx.presenter.MediaPresenter;
import rx.dong.com.rx.ui.adapter.MediaAdapter;
import rx.dong.com.rx.util.AutoLoadRecylerView;
import rx.dong.com.rx.view.ExploreListView;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity implements ExploreListView,
        AutoLoadRecylerView.loadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.recyler_view)
    AutoLoadRecylerView recylerView;
    @Bind(R.id.content)
    SwipeRefreshLayout content;
    @Bind(R.id.sv)
    FlowingView sv;
    @Bind(R.id.id_container_menu)
    FrameLayout idContainerMenu;
    private LeftDrawerLayout mLeftDrawerLayout;
    private Subscription observable;
    @Inject
    MediaPresenter mediaPresenter;
    private int page = 1;
    private List<MediaListBean.MediaList> mediaList = new ArrayList<>();
    private MediaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initDrawer();
//        initRxCall();
//        initRxGson();
        DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(this,1))
                .build()
                .inject(this);

        initRecyclerView();

    }

    private void initDrawer() {
        mLeftDrawerLayout = (LeftDrawerLayout) findViewById(R.id.id_drawerlayout);
        FragmentManager fm = getSupportFragmentManager();
        MyMenuFragment mMenuFragment = (MyMenuFragment) fm.findFragmentById(R.id.id_container_menu);
        FlowingView mFlowingView = (FlowingView) findViewById(R.id.sv);
        if (mMenuFragment == null) {
            fm.beginTransaction().add(R.id.id_container_menu, mMenuFragment = new MyMenuFragment
                    ()).commit();
        }
        mFlowingView.isStartAuto(0);
        mLeftDrawerLayout.setFluidView(mFlowingView);
        mLeftDrawerLayout.setMenuFragment(mMenuFragment);

    }

    private void initRecyclerView() {
        content.setOnRefreshListener(this);
        recylerView.setLayoutManager(new LinearLayoutManager(this));
        recylerView.setHasFixedSize(true);
        adapter = new MediaAdapter(this, mediaList);
        recylerView.setAdapter(adapter);
        mediaPresenter.attachView(this);
        mediaPresenter.loadList(page);
    }

    private void initRxGson() {
        RxService.createApi(RxApi.class).getWithDrawRecord("247", 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WithDrawRecord>() {
                    @Override
                    public void onCompleted() {
                        Logger.e("gg");

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            if (e instanceof HttpException) {
                                ResponseBody body = ((HttpException) e).response().errorBody();
                                try {
                                    Logger.e(body.string());
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                    }

                    @Override
                    public void onNext(WithDrawRecord withDrawRecord) {
                        int success = withDrawRecord.getSuccess();
                        Logger.e("" + success);
                    }
                });
    }

    private void initRxCall() {
        RxService.createApi(RxApi.class).callWithDrawRecord("247", 1)
                .enqueue(new Callback<WithDrawRecord>() {
                    @Override
                    public void onResponse(Call<WithDrawRecord> call, Response<WithDrawRecord>
                            response) {
                        Logger.e("j");
                    }

                    @Override
                    public void onFailure(Call<WithDrawRecord> call, Throwable t) {
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPresenter.detachView();
    }


    @Override
    public void refresh(List<MediaListBean.MediaList> data) {
        mediaList.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadMore(List<MediaListBean.MediaList> data) {
        mediaList.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMore() {
        page++;
        mediaPresenter.loadList(page);
    }

    @Override
    public void onRefresh() {
        page = 1;
        mediaList.clear();
        mediaPresenter.loadList(page);
    }
}
