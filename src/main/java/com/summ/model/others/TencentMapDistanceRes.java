package com.summ.model.others;

import java.util.List;

public class TencentMapDistanceRes {

    /**
     * status : 0
     * message : query ok
     * result : {"elements":[{"from":{"lat":39.983171,"lng":116.308479},"to":{"lat":39.996059,"lng":116.353454},"distance":5354.7,"duration":1678},{"from":{"lat":39.983171,"lng":116.308479},"to":{"lat":39.949226,"lng":116.394309},"distance":10495.8,"duration":2635}]}
     */

    private int status;
    private String message;
    private ResultBean result;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private List<ElementsBean> elements;

        public List<ElementsBean> getElements() {
            return elements;
        }

        public void setElements(List<ElementsBean> elements) {
            this.elements = elements;
        }

        public static class ElementsBean {
            /**
             * from : {"lat":39.983171,"lng":116.308479}
             * to : {"lat":39.996059,"lng":116.353454}
             * distance : 5354.7
             * duration : 1678
             */

            private FromBean from;
            private ToBean to;
            private double distance;
            private int duration;

            public FromBean getFrom() {
                return from;
            }

            public void setFrom(FromBean from) {
                this.from = from;
            }

            public ToBean getTo() {
                return to;
            }

            public void setTo(ToBean to) {
                this.to = to;
            }

            public double getDistance() {
                return distance;
            }

            public void setDistance(double distance) {
                this.distance = distance;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public static class FromBean {
                /**
                 * lat : 39.983171
                 * lng : 116.308479
                 */

                private double lat;
                private double lng;

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }
            }

            public static class ToBean {
                /**
                 * lat : 39.996059
                 * lng : 116.353454
                 */

                private double lat;
                private double lng;

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }
            }
        }
    }
}
