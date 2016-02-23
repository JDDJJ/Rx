package rx.dong.com.rx;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mxn.soul.flowingdrawer_core.MenuFragment;

/**
 * Created by SkyEyesStion on 2016/2/23.
 */
public class MyMenuFragment extends MenuFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container,
                false);
        return setupReveal(view);
    }
}
