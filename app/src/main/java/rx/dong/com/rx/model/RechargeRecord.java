package rx.dong.com.rx.model;

import java.util.List;

/**
 * Created by SkyEyesStion on 2016/2/23.
 */
public class RechargeRecord {
    /**
     * success : 1
     * list : [{"record_id":"122","account_no":null,"id_name":null,"mobile":null,
     * "out_trade_no":"14546716527296","trade_no":"1008960221201602053109795486",
     * "record_name":"微信充值","user_id":"58","record_type":"0","record_way":"1","record_amount":"0
     * .02","application_date":null,"accomplish_date":"1454671664","record_status":"1",
     * "record_info":null}]
     */

    private int success;
    /**
     * record_id : 122
     * account_no : null
     * id_name : null
     * mobile : null
     * out_trade_no : 14546716527296
     * trade_no : 1008960221201602053109795486
     * record_name : 微信充值
     * user_id : 58
     * record_type : 0
     * record_way : 1
     * record_amount : 0.02
     * application_date : null
     * accomplish_date : 1454671664
     * record_status : 1
     * record_info : null
     */

    private List<RechargeItem> list;

    public void setSuccess(int success) {
        this.success = success;
    }

    public void setList(List<RechargeItem> list) {
        this.list = list;
    }

    public int getSuccess() {
        return success;
    }

    public List<RechargeItem> getList() {
        return list;
    }

    public static class RechargeItem {
        private String record_id;
        private String account_no;
        private String id_name;
        private String mobile;
        private String out_trade_no;
        private String trade_no;
        private String record_name;
        private String user_id;
        private String record_type;
        private String record_way;
        private String record_amount;
        private String application_date;
        private String accomplish_date;
        private String record_status;
        private String record_info;

        public void setRecord_id(String record_id) {
            this.record_id = record_id;
        }

        public void setAccount_no(String account_no) {
            this.account_no = account_no;
        }

        public void setId_name(String id_name) {
            this.id_name = id_name;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public void setOut_trade_no(String out_trade_no) {
            this.out_trade_no = out_trade_no;
        }

        public void setTrade_no(String trade_no) {
            this.trade_no = trade_no;
        }

        public void setRecord_name(String record_name) {
            this.record_name = record_name;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public void setRecord_type(String record_type) {
            this.record_type = record_type;
        }

        public void setRecord_way(String record_way) {
            this.record_way = record_way;
        }

        public void setRecord_amount(String record_amount) {
            this.record_amount = record_amount;
        }

        public void setApplication_date(String application_date) {
            this.application_date = application_date;
        }

        public void setAccomplish_date(String accomplish_date) {
            this.accomplish_date = accomplish_date;
        }

        public void setRecord_status(String record_status) {
            this.record_status = record_status;
        }

        public void setRecord_info(String record_info) {
            this.record_info = record_info;
        }

        public String getRecord_id() {
            return record_id;
        }

        public String getAccount_no() {
            return account_no;
        }

        public String getId_name() {
            return id_name;
        }

        public String getMobile() {
            return mobile;
        }

        public String getOut_trade_no() {
            return out_trade_no;
        }

        public String getTrade_no() {
            return trade_no;
        }

        public String getRecord_name() {
            return record_name;
        }

        public String getUser_id() {
            return user_id;
        }

        public String getRecord_type() {
            return record_type;
        }

        public String getRecord_way() {
            return record_way;
        }

        public String getRecord_amount() {
            return record_amount;
        }

        public String getApplication_date() {
            return application_date;
        }

        public String getAccomplish_date() {
            return accomplish_date;
        }

        public String getRecord_status() {
            return record_status;
        }

        public String getRecord_info() {
            return record_info;
        }
    }
}
