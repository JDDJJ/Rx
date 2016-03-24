package rx.dong.com.rx.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.dong.com.rx.R;

/**
 * Created by SkyEyesStion on 2016/3/18.
 */
public class NewDiscoverAdapter extends RecyclerView.Adapter {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newdiscover_item, parent,
                false);
        RecyclerView.ViewHolder holder = null;
        holder = new TagViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    static class TagViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imageView)
        ImageView imageView;
        @Bind(R.id.imageView2)
        ImageView imageView2;
        @Bind(R.id.imageView4)
        ImageView imageView4;
        @Bind(R.id.linearLayout)
        LinearLayout linearLayout;
        @Bind(R.id.textView)
        TextView textView;
        @Bind(R.id.imageView5)
        ImageView imageView5;
        @Bind(R.id.imageView6)
        ImageView imageView6;
        @Bind(R.id.textView2)
        TextView textView2;
        @Bind(R.id.textView3)
        TextView textView3;

        public TagViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
