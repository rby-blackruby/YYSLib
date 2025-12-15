package com.yaoiyun.yyscrape.scraper;

import com.yaoiyun.yyscrape.content.ScrapableContent;

import java.util.List;

public interface LinkScraper<T extends ScrapableContent> extends ContentAssignable<T>{
    public List<String> getContentUrls();
}
