package rx.dong.com.rx.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;

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
import rx.dong.com.rx.util.DividerItemDecoration;
import rx.dong.com.rx.util.ScrollToHide;
import rx.dong.com.rx.util.ScrollToHideListener;
import rx.dong.com.rx.view.ExploreListView;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity implements ExploreListView,
        AutoLoadRecylerView.loadMoreListener, SwipeRefreshLayout.OnRefreshListener,
        ScrollToHideListener {

    //
//    @Bind(R.id.toolbar)
//    Toolbar toolbar;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.sv)
    FlowingView sv;
    @Bind(R.id.id_container_menu)
    FrameLayout idContainerMenu;
    @Bind(R.id.cv_spinner)
    CardView cvSpinner;
    @Bind(R.id.recyler_view)
    AutoLoadRecylerView recylerView;
    @Bind(R.id.m_left_drawerlayout)
    LeftDrawerLayout mLeftDrawerlayout;
    @Bind(R.id.topic_spinner)
    Spinner topicSpinner;
    @Bind(R.id.content)
    SwipeRefreshLayout content;

    private Subscription observable;
    @Inject
    MediaPresenter mediaPresenter;
    private int page = 1;
    private List<MediaListBean.MediaList> mediaList = new ArrayList<>();
    private MediaAdapter adapter;
    private LeftDrawerLayout mLeftDrawerLayout;
    private LinearLayoutManager linearLayoutManager;
    private TranslateAnimation mShowAction;
    private TranslateAnimation mHiddenAction;
    private TranslateAnimation mShowAction2;
    private TranslateAnimation mHiddenAction2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initDrawer();
//        initToolbar();
//        initRxCall();
//        initRxGson();
        DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(this, 1))
                .build()
                .inject(this);
        viewAction();
        initViewPage();
//        initSpinner();
//        initRecyclerView();

    }

    private void initViewPage() {
    }

    private void initSpinner() {
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.topic_spinner_item,
                getResources().getStringArray(R.array.topic_select));
        adapter.setDropDownViewResource(R.layout.drop_down_item);
        topicSpinner.setAdapter(adapter);
    }

    private void initToolbar() {
//        toolbar.setTitle("A");
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void initDrawer() {
        mLeftDrawerLayout = (LeftDrawerLayout) findViewById(R.id.m_left_drawerlayout);
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
        linearLayoutManager = new LinearLayoutManager(this);
        recylerView.setLayoutManager(linearLayoutManager);
        recylerView.setHasFixedSize(true);
        adapter = new MediaAdapter(this, mediaList);
        recylerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration
                .VERTICAL_LIST));
        recylerView.setAdapter(adapter);
        recylerView.setLoadMoreListener(this);
        recylerView.setOnScrollListener(new ScrollToHide(linearLayoutManager, this));
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
                            ResponseBody body = ((HttpException) e).response().errorBody();
                            try {
                                Logger.e(body.string());
                            } catch (IOException e1) {
                                e1.printStackTrace();

                            }
                        }
                    }

                    @Override
                    public void onNext(WithDrawRecord withDrawRecord) {
                        int success = withDrawRecord.getSuccess();
                        Logger.e("" + success);
                    }
                });
//        new Request.Builder().tag("1").build();
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
        adapter.changeData(data);
        if (content.isRefreshing())
            content.setRefreshing(false);
    }

    @Override
    public void loadMore(List<MediaListBean.MediaList> data) {
        recylerView.setLoading(false);
        adapter.addData(data);
    }

    @Override
    public void onLoadMore() {
        page++;
        System.err.println("=======" + page);
        mediaPresenter.loadList(page);
    }

    @Override
    public void onRefresh() {
        page = 1;
        mediaList.clear();
        mediaPresenter.loadList(page);
    }

    @Override
    public void hideView() {
//        fab.startAnimation(mHiddenAction2);
        cvSpinner.startAnimation(mHiddenAction);
        fab.setVisibility(View.GONE);
        cvSpinner.setVisibility(View.GONE);
    }

    @Override
    public void showView() {
//        fab.startAnimation(mShowAction2);
        cvSpinner.startAnimation(mShowAction);
        fab.setVisibility(View.VISIBLE);
        cvSpinner.setVisibility(View.VISIBLE);
    }

    private void viewAction() {
        mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -2.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(500);
        mShowAction2 = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                2.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction2.setDuration(500);
        mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f);
        mHiddenAction.setDuration(500);
        mHiddenAction2 = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                2.0f);
        mHiddenAction2.setDuration(500);
    }
}
