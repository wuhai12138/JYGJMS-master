package com.summ.model.others;

import java.util.List;

public class BaiDuApiObj {

    /**
     * status : 0
     * result : [{"distance":{"text":"17.6公里","value":17553},"duration":{"text":"4.9小时","value":17553}}]
     * message : 成功
     */

    private int status;
    private String message;
    /**
     * distance : {"text":"17.6公里","value":17553}
     * duration : {"text":"4.9小时","value":17553}
     */

    private List<ResultBean> result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * text : 17.6公里
         * value : 17553
         */

        private DistanceBean distance;
        /**
         * text : 4.9小时
         * value : 17553
         */

        private DurationBean duration;

        public DistanceBean getDistance() {
            return distance;
        }

        public void setDistance(DistanceBean distance) {
            this.distance = distance;
        }

        public DurationBean getDuration() {
            return duration;
        }

        public void setDuration(DurationBean duration) {
            this.duration = duration;
        }

        public static class DistanceBean {
            private String text;
            private int value;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }
        }

        public static class DurationBean {
            private String text;
            private int value;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }
        }
    }
}
