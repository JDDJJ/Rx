package rx.dong.com.rx.ui;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import rx.dong.com.rx.view.MvpView;

/**
 * Created by SkyEyesStion on 2016/3/14.
 */
public class BaseActivity extends AppCompatActivity implements MvpView {
    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg, View.OnClickListener onClickListener) {

    }

    @Override
    public void showEmpty(String msg, View.OnClickListener onClickListener) {

    }

    @Override
    public void showEmpty(String msg, View.OnClickListener onClickListener, int imageId) {

    }

    @Override
    public void showNetError(View.OnClickListener onClickListener) {

    }
}
