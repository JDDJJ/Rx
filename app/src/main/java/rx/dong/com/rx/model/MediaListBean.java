package rx.dong.com.rx.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SkyEyesStion on 2016/3/14.
 */
public class MediaListBean {


    /**
     * success : 1
     * list : [{"media_id":"1","media_status":"0","media_width":"10","media_height":"10",
     * "media_name":"猫狗","media_info":"可爱的猫狗","media_tag":null,"media_tag_name":null,
     * "media_uid":"134","media_user":"Ruolin","media_avatar":"http://skyeyeslive.oss-cn-beijing
     * .aliyuncs.com/system_icon/avatar_default.jpg","post_time":"1442481366840",
     * "media_duration":"00:00:00","img_url":"http://skyeyeslive.oss-cn-beijing.aliyuncs
     * .com/maogou.jpg","video_url":null,"media_hotrate":"0","comment_sum":"0","collect_sum":"0",
     * "zan_sum":"0","watch_sum":"0","gps_x":"116.273","gps_y":"39.8593","media_country":null,
     * "media_city":null,"media_street":null,"share_status":"0","lucky_star":"0",
     * "most_popular":"0","my_favorite":"0","if_trade":"0","v_media":"0"}]
     */

    private int success;
    /**
     * media_id : 1
     * media_status : 0
     * media_width : 10
     * media_height : 10
     * media_name : 猫狗
     * media_info : 可爱的猫狗
     * media_tag : null
     * media_tag_name : null
     * media_uid : 134
     * media_user : Ruolin
     * media_avatar : http://skyeyeslive.oss-cn-beijing.aliyuncs.com/system_icon/avatar_default.jpg
     * post_time : 1442481366840
     * media_duration : 00:00:00
     * img_url : http://skyeyeslive.oss-cn-beijing.aliyuncs.com/maogou.jpg
     * video_url : null
     * media_hotrate : 0
     * comment_sum : 0
     * collect_sum : 0
     * zan_sum : 0
     * watch_sum : 0
     * gps_x : 116.273
     * gps_y : 39.8593
     * media_country : null
     * media_city : null
     * media_street : null
     * share_status : 0
     * lucky_star : 0
     * most_popular : 0
     * my_favorite : 0
     * if_trade : 0
     * v_media : 0
     */

    @SerializedName("list")
    private List<MediaList> mediaList;

    public void setSuccess(int success) {
        this.success = success;
    }

    public void setMediaList(List<MediaList> mediaList) {
        this.mediaList = mediaList;
    }

    public int getSuccess() {
        return success;
    }

    public List<MediaList> getMediaList() {
        return mediaList;
    }

    public static class MediaList {
        private String media_id;
        private String media_status;
        private String media_width;
        private String media_height;
        private String media_name;
        private String media_info;
        private Object media_tag;
        private Object media_tag_name;
        private String media_uid;
        private String media_user;
        private String media_avatar;
        private String post_time;
        private String media_duration;
        private String img_url;
        private String video_url;
        private String media_hotrate;
        private String comment_sum;
        private String collect_sum;
        private String zan_sum;
        private String watch_sum;
        private String gps_x;
        private String gps_y;
        private Object media_country;
        private Object media_city;
        private Object media_street;
        private String share_status;
        private String lucky_star;
        private String most_popular;
        private String my_favorite;
        private String if_trade;
        private String v_media;

        public void setMedia_id(String media_id) {
            this.media_id = media_id;
        }

        public void setMedia_status(String media_status) {
            this.media_status = media_status;
        }

        public void setMedia_width(String media_width) {
            this.media_width = media_width;
        }

        public void setMedia_height(String media_height) {
            this.media_height = media_height;
        }

        public void setMedia_name(String media_name) {
            this.media_name = media_name;
        }

        public void setMedia_info(String media_info) {
            this.media_info = media_info;
        }

        public void setMedia_tag(Object media_tag) {
            this.media_tag = media_tag;
        }

        public void setMedia_tag_name(Object media_tag_name) {
            this.media_tag_name = media_tag_name;
        }

        public void setMedia_uid(String media_uid) {
            this.media_uid = media_uid;
        }

        public void setMedia_user(String media_user) {
            this.media_user = media_user;
        }

        public void setMedia_avatar(String media_avatar) {
            this.media_avatar = media_avatar;
        }

        public void setPost_time(String post_time) {
            this.post_time = post_time;
        }

        public void setMedia_duration(String media_duration) {
            this.media_duration = media_duration;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public void setMedia_hotrate(String media_hotrate) {
            this.media_hotrate = media_hotrate;
        }

        public void setComment_sum(String comment_sum) {
            this.comment_sum = comment_sum;
        }

        public void setCollect_sum(String collect_sum) {
            this.collect_sum = collect_sum;
        }

        public void setZan_sum(String zan_sum) {
            this.zan_sum = zan_sum;
        }

        public void setWatch_sum(String watch_sum) {
            this.watch_sum = watch_sum;
        }

        public void setGps_x(String gps_x) {
            this.gps_x = gps_x;
        }

        public void setGps_y(String gps_y) {
            this.gps_y = gps_y;
        }

        public void setMedia_country(Object media_country) {
            this.media_country = media_country;
        }

        public void setMedia_city(Object media_city) {
            this.media_city = media_city;
        }

        public void setMedia_street(Object media_street) {
            this.media_street = media_street;
        }

        public void setShare_status(String share_status) {
            this.share_status = share_status;
        }

        public void setLucky_star(String lucky_star) {
            this.lucky_star = lucky_star;
        }

        public void setMost_popular(String most_popular) {
            this.most_popular = most_popular;
        }

        public void setMy_favorite(String my_favorite) {
            this.my_favorite = my_favorite;
        }

        public void setIf_trade(String if_trade) {
            this.if_trade = if_trade;
        }

        public void setV_media(String v_media) {
            this.v_media = v_media;
        }

        public String getMedia_id() {
            return media_id;
        }

        public String getMedia_status() {
            return media_status;
        }

        public String getMedia_width() {
            return media_width;
        }

        public String getMedia_height() {
            return media_height;
        }

        public String getMedia_name() {
            return media_name;
        }

        public String getMedia_info() {
            return media_info;
        }

        public Object getMedia_tag() {
            return media_tag;
        }

        public Object getMedia_tag_name() {
            return media_tag_name;
        }

        public String getMedia_uid() {
            return media_uid;
        }

        public String getMedia_user() {
            return media_user;
        }

        public String getMedia_avatar() {
            return media_avatar;
        }

        public String getPost_time() {
            return post_time;
        }

        public String getMedia_duration() {
            return media_duration;
        }

        public String getImg_url() {
            return img_url;
        }

        public String getVideo_url() {
            return video_url;
        }

        public String getMedia_hotrate() {
            return media_hotrate;
        }

        public String getComment_sum() {
            return comment_sum;
        }

        public String getCollect_sum() {
            return collect_sum;
        }

        public String getZan_sum() {
            return zan_sum;
        }

        public String getWatch_sum() {
            return watch_sum;
        }

        public String getGps_x() {
            return gps_x;
        }

        public String getGps_y() {
            return gps_y;
        }

        public Object getMedia_country() {
            return media_country;
        }

        public Object getMedia_city() {
            return media_city;
        }

        public Object getMedia_street() {
            return media_street;
        }

        public String getShare_status() {
            return share_status;
        }

        public String getLucky_star() {
            return lucky_star;
        }

        public String getMost_popular() {
            return most_popular;
        }

        public String getMy_favorite() {
            return my_favorite;
        }

        public String getIf_trade() {
            return if_trade;
        }

        public String getV_media() {
            return v_media;
        }
    }
}
