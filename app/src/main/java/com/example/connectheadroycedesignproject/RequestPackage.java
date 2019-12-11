package com.example.connectheadroycedesignproject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class RequestPackage {
        private String url;
        private String method = "GET";
        private Map<String, String> params = new HashMap<>();

        public String getUrl() {
            return url;
        }

        public void setUrlvoid(String url) {
            this.url = url;
        }


        public String getMethod() {
            return method;
        }

        public void setmethodvalue(String method) {
            this.method = method;
        }

        public Map<String, String> getParams() {
            return params;
        }

        public void setParams(Map<String, String> params) {
            this.params = params;
        }

        public void setParam2(String key, String value) {
            params.put(key, value);
        }


        public String GetEncodeParam() {
            StringBuilder SB = new StringBuilder();
            for (String key : params.keySet()) {
                String value = null;
                try {
                    value = URLEncoder.encode(params.get(key), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if (SB.length() > 0) {
                    SB.append("&");

                }

                SB.append(key + ("=") + value);


            }

            return SB.toString();
        }
}
