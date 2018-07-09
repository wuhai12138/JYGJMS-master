package com.summ.model.others;

public class TencentMapRes {

    /**
     * status : 0
     * message : query ok
     * result : {"title":"金园一路1359弄","location":{"lng":121.31652,"lat":31.24221},"address_components":{"province":"上海市","city":"上海市","district":"嘉定区","street":"金园一路","street_number":"1359"},"similarity":0.8,"deviation":1000,"reliability":7,"level":9}
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
        /**
         * title : 金园一路1359弄
         * location : {"lng":121.31652,"lat":31.24221}
         * address_components : {"province":"上海市","city":"上海市","district":"嘉定区","street":"金园一路","street_number":"1359"}
         * similarity : 0.8
         * deviation : 1000
         * reliability : 7
         * level : 9
         */

        private String title;
        private LocationBean location;
        private AddressComponentsBean address_components;
        private double similarity;
        private int deviation;
        private int reliability;
        private int level;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public AddressComponentsBean getAddress_components() {
            return address_components;
        }

        public void setAddress_components(AddressComponentsBean address_components) {
            this.address_components = address_components;
        }

        public double getSimilarity() {
            return similarity;
        }

        public void setSimilarity(double similarity) {
            this.similarity = similarity;
        }

        public int getDeviation() {
            return deviation;
        }

        public void setDeviation(int deviation) {
            this.deviation = deviation;
        }

        public int getReliability() {
            return reliability;
        }

        public void setReliability(int reliability) {
            this.reliability = reliability;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public static class LocationBean {
            /**
             * lng : 121.31652
             * lat : 31.24221
             */

            private double lng;
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

        public static class AddressComponentsBean {
            /**
             * province : 上海市
             * city : 上海市
             * district : 嘉定区
             * street : 金园一路
             * street_number : 1359
             */

            private String province;
            private String city;
            private String district;
            private String street;
            private String street_number;

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public String getStreet() {
                return street;
            }

            public void setStreet(String street) {
                this.street = street;
            }

            public String getStreet_number() {
                return street_number;
            }

            public void setStreet_number(String street_number) {
                this.street_number = street_number;
            }
        }
    }
}
