package com.yaoiyun.yyscrape.utils;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UrlUtilsTest {

    @Test
    public void getBaseUrl() {
        String exampleLink = "https://mangabuddy.com";
        assertEquals("mangabuddy.com" , UrlUtils.getBaseUrl(exampleLink), "Expected base url is mangabuddy.com");
    }
}
