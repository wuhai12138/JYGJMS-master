package com.summ.model.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 2017/1/5.
 */
public class BaiDuMapRes {

    /**
     * status : 0
     * result : {"location":{"lng":121.39354924347269,"lat":31.182585099024056},"precise":1,"confidence":80,"level":"道路"}
     */

    @SerializedName("status")
    private int status;
    @SerializedName("result")
    private ResultBean result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * location : {"lng":121.39354924347269,"lat":31.182585099024056}
         * precise : 1
         * confidence : 80
         * level : 道路
         */

        @SerializedName("location")
        private LocationBean location;
        @SerializedName("precise")
        private int precise;
        @SerializedName("confidence")
        private int confidence;
        @SerializedName("level")
        private String level;

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public int getPrecise() {
            return precise;
        }

        public void setPrecise(int precise) {
            this.precise = precise;
        }

        public int getConfidence() {
            return confidence;
        }

        public void setConfidence(int confidence) {
            this.confidence = confidence;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public static class LocationBean {
            /**
             * lng : 121.39354924347269
             * lat : 31.182585099024056
             */

            @SerializedName("lng")
            private double lng;
            @SerializedName("lat")
            private double lat;

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }
        }
    }
}
