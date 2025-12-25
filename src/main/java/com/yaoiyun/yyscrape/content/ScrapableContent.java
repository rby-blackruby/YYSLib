package com.yaoiyun.yyscrape.content;

public interface ScrapableContent {
    String getName();
    String getUrl();
    ScrapableContentType getContentType();
}
