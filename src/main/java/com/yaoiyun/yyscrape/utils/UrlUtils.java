package com.yaoiyun.yyscrape.utils;

import java.net.URI;
import java.util.Arrays;

public class UrlUtils {

    public static String getBaseUrl(String url) {
        URI uri = URI.create(url);
        String protocol = uri.getScheme();

        if(!(protocol.equals("http") || protocol.equals("https"))) {
            throw new IllegalArgumentException("Protocol not supported.");
        }

        return uri.getHost();
    }

    public static String getSanitizedUrl(String url) {
        if(url.endsWith("/")) {
            return url.toLowerCase();
        }

        return url.toLowerCase() + "/";
    }
}
