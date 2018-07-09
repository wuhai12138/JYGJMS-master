package com.summ.model.others;

public class AliPayDownloadObj {

    /**
     * code : 10000
     * msg : Success
     * bill_download_url : http://dwbillcenter.alipay.com/downloadBillFile.resource?bizType=trade&userId=20887219543280340156&fileType=csv.zip&bizDates=20180509&downloadFileName=20887219543280340156_20180509.csv.zip&fileId=/trade/20887219543280340156/20180509.csv.zip&timestamp=1525935085&token=d773987652ce2803be0a6f4ffb8afa90
     */

    private AlipayDataDataserviceBillDownloadurlQueryResponseBean alipay_data_dataservice_bill_downloadurl_query_response;
    /**
     * alipay_data_dataservice_bill_downloadurl_query_response : {"code":"10000","msg":"Success","bill_download_url":"http://dwbillcenter.alipay.com/downloadBillFile.resource?bizType=trade&userId=20887219543280340156&fileType=csv.zip&bizDates=20180509&downloadFileName=20887219543280340156_20180509.csv.zip&fileId=/trade/20887219543280340156/20180509.csv.zip&timestamp=1525935085&token=d773987652ce2803be0a6f4ffb8afa90"}
     * sign : E70e4YUjUb8AKvnduHDE9miOl28Cng461VoTvHQKAfcMtvai+LzLnPF+pvwR2L/pQ9QA44DQ8bBGssPWwfH7HwBlenyvi+ZnFSq6F06gqKDuiI3UD2vybt+uYAbID8+QAFtYnNHSqo/7AGx2H2Vo55mHjwcygQg3/JAo+inmKqY6W/uEzkTPvXmr5z4Dks+qK7Mm9ABQFor7D44+b+HCbcMhJazFjc76eiLxa+7GsFZpGb9eDMd1fusXOoaboRkqADf1lbU4Q9+EZ5cXgF/tVg1xKmxsMBc7Dv1HWT+2rJn8CWgblvfokzl0jQhRX8tHDjkZKwcD3ZTWNgoOyWjTjw==
     */

    private String sign;

    public AlipayDataDataserviceBillDownloadurlQueryResponseBean getAlipay_data_dataservice_bill_downloadurl_query_response() {
        return alipay_data_dataservice_bill_downloadurl_query_response;
    }

    public void setAlipay_data_dataservice_bill_downloadurl_query_response(AlipayDataDataserviceBillDownloadurlQueryResponseBean alipay_data_dataservice_bill_downloadurl_query_response) {
        this.alipay_data_dataservice_bill_downloadurl_query_response = alipay_data_dataservice_bill_downloadurl_query_response;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public static class AlipayDataDataserviceBillDownloadurlQueryResponseBean {
        private String code;
        private String msg;
        private String bill_download_url;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getBill_download_url() {
            return bill_download_url;
        }

        public void setBill_download_url(String bill_download_url) {
            this.bill_download_url = bill_download_url;
        }
    }
}
