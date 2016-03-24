package rx.dong.com.rx.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.dong.com.rx.R;

/**
 * Created by SkyEyesStion on 2016/3/22.
 */
public class TestAdapter extends RecyclerView.Adapter {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_item, parent, false);
        holder = new ImgHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ImgHolder imgHolder = (ImgHolder) holder;
        imgHolder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgHolder.img.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class ImgHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.btn)
        Button btn;
        @Bind(R.id.img)
        ImageView img;

        public ImgHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
