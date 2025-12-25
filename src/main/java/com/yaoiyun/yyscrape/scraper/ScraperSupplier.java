package com.yaoiyun.yyscrape.scraper;

import com.yaoiyun.yyscrape.content.ScrapableContent;

@FunctionalInterface
public interface ScraperSupplier<T> {
    T get(ScrapableContent content);
}
