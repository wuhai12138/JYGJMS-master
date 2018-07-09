package com.summ.model.others;

public class ShowApiRes {

    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"ret_code":0,"belong":{"tel":"95568","brand":"借记卡普卡","bankName":"民生银行","cardType":"借记卡","url":"www.cmbc.com.cn","cardNum":"6216910211348864"},"code":0,"msg":"认证通过"}
     */

    private int showapi_res_code;
    private String showapi_res_error;

    /**
     * ret_code : 0
     * belong : {"tel":"95568","brand":"借记卡普卡","bankName":"民生银行","cardType":"借记卡","url":"www.cmbc.com.cn","cardNum":"6216910211348864"}
     * code : 0
     * msg : 认证通过
     */



    private ShowapiResBodyBean showapi_res_body;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyBean {
        private int ret_code;
        /**
         * tel : 95568
         * brand : 借记卡普卡
         * bankName : 民生银行
         * cardType : 借记卡
         * url : www.cmbc.com.cn
         * cardNum : 6216910211348864
         */

        private BelongBean belong;
        private int code;
        private String msg;

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public BelongBean getBelong() {
            return belong;
        }

        public void setBelong(BelongBean belong) {
            this.belong = belong;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public static class BelongBean {
            private String tel;
            private String brand;
            private String bankName;
            private String cardType;
            private String url;
            private String cardNum;
            private String area;

            @Override
            public String toString() {
                return "BelongBean{" +
                        "tel='" + tel + '\'' +
                        ", brand='" + brand + '\'' +
                        ", bankName='" + bankName + '\'' +
                        ", cardType='" + cardType + '\'' +
                        ", url='" + url + '\'' +
                        ", cardNum='" + cardNum + '\'' +
                        ", area='" + area + '\'' +
                        '}';
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public String getBankName() {
                return bankName;
            }

            public void setBankName(String bankName) {
                this.bankName = bankName;
            }

            public String getCardType() {
                return cardType;
            }

            public void setCardType(String cardType) {
                this.cardType = cardType;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getCardNum() {
                return cardNum;
            }

            public void setCardNum(String cardNum) {
                this.cardNum = cardNum;
            }
        }
    }
}
