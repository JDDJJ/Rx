package rx.dong.com.rx;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mxn.soul.flowingdrawer_core.FlowingView;
import com.mxn.soul.flowingdrawer_core.LeftDrawerLayout;
import com.orhanobut.logger.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.btn)
    Button btn;
    @Bind(R.id.content)
    CoordinatorLayout content;
    @Bind(R.id.sv)
    FlowingView sv;
    @Bind(R.id.id_container_menu)
    FrameLayout idContainerMenu;
    @Bind(R.id.id_drawerlayout)
    LeftDrawerLayout idDrawerlayout;
    private LeftDrawerLayout mLeftDrawerLayout;
    private Subscription observable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initDrawer();
        String json = "{\n" +
                "          \"success\": 1,\n" +
                "          \"list\": [\n" +
                "          {\n" +
                "            \"record_id\": \"122\",\n" +
                "            \"account_no\": null,\n" +
                "            \"id_name\": null,\n" +
                "            \"mobile\": null,\n" +
                "            \"out_trade_no\": \"14546716527296\",\n" +
                "            \"trade_no\": \"1008960221201602053109795486\",\n" +
                "            \"record_name\": \"微信充值\",\n" +
                "            \"user_id\": \"58\",\n" +
                "            \"record_type\": \"0\",\n" +
                "            \"record_way\": \"1\",\n" +
                "            \"record_amount\": \"0.02\",\n" +
                "            \"application_date\": null,\n" +
                "            \"accomplish_date\": \"1454671664\",\n" +
                "            \"record_status\": \"1\",\n" +
                "            \"record_info\": null\n" +
                "          }\n" +
                "          ]\n" +
                "       }";
        //                        Gson gson = new GsonBuilder().create();
        observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Logger.e("create");
                subscriber.onNext(json);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .map(new Func1<String, RechargeRecord>() {
                    @Override
                    public RechargeRecord call(String s) {
                        Gson gson = new GsonBuilder().serializeNulls().create();
//                        Gson gson = new GsonBuilder().create();
                        RechargeRecord rechargeRecord = gson.fromJson(json, RechargeRecord.class);
                        Logger.e("else");
                        return rechargeRecord;
                    }
                })

                .observeOn(Schedulers.io())
                .map(new Func1<RechargeRecord, RechargeRecord>() {
                    @Override
                    public RechargeRecord call(RechargeRecord rechargeRecord) {
                        Logger.e("map");
                        return rechargeRecord;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RechargeRecord>() {
                    @Override
                    public void call(RechargeRecord rechargeRecord) {
                        if (rechargeRecord.getList().get(0).getRecord_info() == null)
                            Logger.e("null");
                        else if (rechargeRecord.getList().get(0).getRecord_info().equals(""))
                            Logger.e("no");
                        else if (rechargeRecord.getList().get(0).getRecord_info().equals("null"))
                            Logger.e("e_no");
                        else Logger.e("else");
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!observable.isUnsubscribed()) {
            observable.unsubscribe();
        }
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

    private void exampleOne() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    SystemClock.sleep(1000);
                }
            }
        }.start();
    }

}
