package com.summ.model.others;

/**
 * Created by admin on 2017/3/13.
 */
public class GetWXResult {
    //
    private String attach   //用户id
            , total_fee      //充值金额
            , result_code    //返回状态
            , return_code, transaction_id//流水号
            , trade_type, time_end//充值时间
            , out_trade_no;//商户订单号

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    @Override
    public String toString() {
        return "GetWXResult{" +
                "attach='" + attach + '\'' +
                ", total_fee='" + total_fee + '\'' +
                ", result_code='" + result_code + '\'' +
                ", return_code='" + return_code + '\'' +
                ", transaction_id='" + transaction_id + '\'' +
                ", trade_type='" + trade_type + '\'' +
                ", time_end='" + time_end + '\'' +
                '}';
    }
}
