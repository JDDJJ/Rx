package rx.dong.com.rx.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.jakewharton.scalpel.ScalpelFrameLayout;
import com.mxn.soul.flowingdrawer_core.FlowingView;
import com.mxn.soul.flowingdrawer_core.LeftDrawerLayout;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.dong.com.rx.R;
import rx.dong.com.rx.api.RxService;
import rx.dong.com.rx.model.WithDrawRecord;
import rx.dong.com.rx.util.AES;
import rx.schedulers.Schedulers;
import zhy.com.highlight.HighLight;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.btn)
    Button btn;
    @Bind(R.id.content)
    RelativeLayout content;
    @Bind(R.id.sv)
    FlowingView sv;
    @Bind(R.id.id_container_menu)
    FrameLayout idContainerMenu;
    @Bind(R.id.id_drawerlayout)
    LeftDrawerLayout idDrawerlayout;
    @Bind(R.id.main_scalpel)
    ScalpelFrameLayout mainScalpel;
    private LeftDrawerLayout mLeftDrawerLayout;
    private Subscription observable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initDrawer();
//        initRxCall();
//        initRxGson();
        String string = AES.encrypt("123456你好String",
                "dab85f0c65204c3f");
        btn.setText(string);
        System.err.println("!!!" + string);
        System.err.println("!!!" + AES.decrypt(string, "dab85f0c65204c3f"));
    }

    private void initRxGson() {
        RxService.createRxService().getWithDrawRecord("247", 1)
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
                        btn.setText(withDrawRecord.getList().get(0).getAccount_no().toString());
                        int success = withDrawRecord.getSuccess();
                        Logger.e("" + success);
                    }
                });
    }

    private void initRxCall() {
        RxService.createRxService().callWithDrawRecord("247", 1)
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

    @OnClick(R.id.btn)
    public void onClick() {
        new HighLight(MainActivity.this)//
                .anchor(findViewById(R.id.id_drawerlayout))
                .addHighLight(R.id.btn, R.layout.info_down,
                        (rightMargin, bottomMargin, rectF, marginInfo) -> {
                            marginInfo.leftMargin = rectF.right - rectF.width();
                            marginInfo.bottomMargin = bottomMargin + rectF.height();
                        }).show();
        mainScalpel.setLayerInteractionEnabled(true);

    }
}
