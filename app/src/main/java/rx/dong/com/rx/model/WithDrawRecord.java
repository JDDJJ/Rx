package rx.dong.com.rx.model;

import java.util.List;

/**
 * Created by SkyEyesStion on 2016/2/26.
 */
public class WithDrawRecord {

    /**
     * success : 1
     * list : [{"record_id":"107","account_no":"565445554585655857.5555","id_name":"hgfffg",
     * "mobile":"21255554455","out_trade_no":null,"trade_no":null,"record_name":"LiiiFE支付宝提现",
     * "user_id":"58","record_type":"1","record_way":"0","record_amount":"23333.00",
     * "application_date":null,"accomplish_date":"1454651454077","record_status":"2",
     * "record_info":"拒绝测试"}]
     */

    private int success;
    /**
     * record_id : 107
     * account_no : 565445554585655857.5555
     * id_name : hgfffg
     * mobile : 21255554455
     * out_trade_no : null
     * trade_no : null
     * record_name : LiiiFE支付宝提现
     * user_id : 58
     * record_type : 1
     * record_way : 0
     * record_amount : 23333.00
     * application_date : null
     * accomplish_date : 1454651454077
     * record_status : 2
     * record_info : 拒绝测试
     */

    private List<ListEntity> list;

    public void setSuccess(int success) {
        this.success = success;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public int getSuccess() {
        return success;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public static class ListEntity {
        private String record_id;
        private String account_no;
        private String id_name;
        private String mobile;
        private Object out_trade_no;
        private Object trade_no;
        private String record_name;
        private String user_id;
        private String record_type;
        private String record_way;
        private String record_amount;
        private Object application_date;
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

        public void setOut_trade_no(Object out_trade_no) {
            this.out_trade_no = out_trade_no;
        }

        public void setTrade_no(Object trade_no) {
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

        public void setApplication_date(Object application_date) {
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

        public Object getOut_trade_no() {
            return out_trade_no;
        }

        public Object getTrade_no() {
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

        public Object getApplication_date() {
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
