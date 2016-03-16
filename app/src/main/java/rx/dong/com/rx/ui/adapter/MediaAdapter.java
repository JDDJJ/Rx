package rx.dong.com.rx.ui.adapter;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.dong.com.rx.R;
import rx.dong.com.rx.model.MediaListBean;
import rx.dong.com.rx.view.TextureVideoView;

/**
 * Created by SkyEyesStion on 2016/3/15.
 */
public class MediaAdapter extends RecyclerView.Adapter {

    private List<MediaListBean.MediaList> mediaList;
    private Context context;
    private static final int IMG = 0;
    private static final int VIDEO = 1;
    private VideoHolder lastPlayVideo = null;


    @Override
    public int getItemViewType(int position) {
        boolean flag = mediaList.get(position).getVideo_url().equals("") || mediaList.get(position)
                .getVideo_url().contains("gif");
        if (flag) {
            return IMG;
        } else {
            return VIDEO;
        }
    }

    public MediaAdapter(Context context, List<MediaListBean.MediaList> mediaList) {
        //construct
        this.context = context;
        this.mediaList = mediaList;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        if (viewType == IMG) {
            view = LayoutInflater.from(context).inflate(R.layout.comment_item, parent, false);
            holder = new ImgHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.video_item, parent, false);
            holder = new VideoHolder(view);
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ImgHolder) {
            initImg((ImgHolder) holder, position);
        } else {
            initVideo((VideoHolder) holder, position);
        }

    }

    private void initImg(ImgHolder holder, int position) {
        ImgHolder imgHolder = holder;
        Glide.with(context).load(mediaList.get(position).getImg_url()).into(imgHolder.media);
    }

    private void initVideo(VideoHolder holder, int position) {
        VideoHolder videoHolder = holder;
        Glide.with(context).load(mediaList.get(position).getImg_url()).fitCenter().into(videoHolder
                .imvPreview);
        videoHolder.textureview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mediaList.get(position).getVideo_url().length() == 0) {
                    Toast.makeText(context, "视频地址不能为空，请在Activity中设置视频地址哦", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                if (lastPlayVideo == null) {
                    lastPlayVideo = videoHolder;
                } else {
                    if (!videoHolder.equals(lastPlayVideo)) {
                        lastPlayVideo.textureview.stop();
                        lastPlayVideo.pbWaiting.setVisibility(View.GONE);
                        lastPlayVideo.imvVideoPlay.setVisibility(View.VISIBLE);
                        lastPlayVideo = videoHolder;
                    }
                }
                TextureVideoView textureView = (TextureVideoView) v;
                if (textureView.getState() == TextureVideoView.MediaState.INIT ||
                        textureView.getState() == TextureVideoView.MediaState.RELEASE) {
                    textureView.play(mediaList.get(position).getVideo_url());
                    videoHolder.pbWaiting.setVisibility(View.VISIBLE);
                    videoHolder.imvVideoPlay.setVisibility(View.GONE);
                } else if (textureView.getState() == TextureVideoView.MediaState.PAUSE) {
                    textureView.start();
                    videoHolder.pbWaiting.setVisibility(View.GONE);
                    videoHolder.imvVideoPlay.setVisibility(View.GONE);
                } else if (textureView.getState() == TextureVideoView.MediaState.PLAYING) {
                    textureView.pause();
                    videoHolder.pbWaiting.setVisibility(View.GONE);
                    videoHolder.imvVideoPlay.setVisibility(View.VISIBLE);
                } else if (textureView.getState() == TextureVideoView.MediaState.PREPARING) {
                    textureView.stop();
                    videoHolder.pbWaiting.setVisibility(View.GONE);
                    videoHolder.imvVideoPlay.setVisibility(View.VISIBLE);
                }
            }
        });
        videoHolder.textureview.setOnStateChangeListener(new TextureVideoView
                .OnStateChangeListener() {
            @Override
            public void onSurfaceTextureDestroyed(SurfaceTexture surface) {
                videoHolder.textureview.stop();
                videoHolder.pbWaiting.setVisibility(View.GONE);
                videoHolder.imvVideoPlay.setVisibility(View.VISIBLE);
                videoHolder.progressProgressbar.setMax(100);
                videoHolder.progressProgressbar.setProgress(0);
                videoHolder.imvPreview.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPlaying() {
                videoHolder.pbWaiting.setVisibility(View.GONE);
                videoHolder.imvVideoPlay.setVisibility(View.GONE);
            }

            @Override
            public void onBuffering() {
                videoHolder.pbWaiting.setVisibility(View.VISIBLE);
                videoHolder.imvVideoPlay.setVisibility(View.GONE);
            }

            @Override
            public void onSeek(int max, int progress) {
                videoHolder.imvPreview.setVisibility(View.GONE);
                videoHolder.progressProgressbar.setMax(max);
                videoHolder.progressProgressbar.setProgress(progress);
            }

            @Override
            public void onStop() {
                videoHolder.progressProgressbar.setMax(100);
                videoHolder.progressProgressbar.setProgress(0);
                videoHolder.pbWaiting.setVisibility(View.GONE);
                videoHolder.imvVideoPlay.setVisibility(View.VISIBLE);
                videoHolder.imvPreview.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPause() {
                videoHolder.pbWaiting.setVisibility(View.GONE);
                videoHolder.imvVideoPlay.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTextureViewAvaliable() {

            }

            @Override
            public void playFinish() {
                videoHolder.progressProgressbar.setMax(100);
                videoHolder.progressProgressbar.setProgress(0);
                videoHolder.imvVideoPlay.setVisibility(View.GONE);
                videoHolder.imvPreview.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPrepare() {

            }
        });
    }


    @Override
    public int getItemCount() {
        return mediaList.size();
    }

    static class ImgHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.media)
        ImageView media;
        @Bind(R.id.task_status)
        TextView taskStatus;
        @Bind(R.id.txt_share_money)
        TextView txtShareMoney;
        @Bind(R.id.txt_most_popular)
        TextView txtMostPopular;
        @Bind(R.id.txt_my_favorite)
        TextView txtMyFavorite;
        @Bind(R.id.txt_lucky_txt)
        TextView txtLuckyTxt;
        @Bind(R.id.imageView3)
        ImageView imageView3;
        @Bind(R.id.image_camera)
        ImageView imageCamera;
        @Bind(R.id.image_gif)
        ImageView imageGif;
        @Bind(R.id.commentsBlank)
        LinearLayout commentsBlank;

        public ImgHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class VideoHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imv_preview)
        ImageView imvPreview;
        @Bind(R.id.textureview)
        TextureVideoView textureview;
        @Bind(R.id.imv_video_play)
        ImageView imvVideoPlay;
        @Bind(R.id.pb_waiting)
        ProgressBar pbWaiting;
        @Bind(R.id.progress_progressbar)
        ProgressBar progressProgressbar;
        @Bind(R.id.media)
        ImageView media;
        @Bind(R.id.task_status)
        TextView taskStatus;
        @Bind(R.id.txt_share_money)
        TextView txtShareMoney;
        @Bind(R.id.txt_most_popular)
        TextView txtMostPopular;
        @Bind(R.id.txt_my_favorite)
        TextView txtMyFavorite;
        @Bind(R.id.txt_lucky_txt)
        TextView txtLuckyTxt;
        @Bind(R.id.commentsBlank)
        LinearLayout commentsBlank;

        public VideoHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
