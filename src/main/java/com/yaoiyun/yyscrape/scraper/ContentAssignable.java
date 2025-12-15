package com.yaoiyun.yyscrape.scraper;

import com.yaoiyun.yyscrape.content.ScrapableContent;

public interface ContentAssignable<T extends ScrapableContent>{
    T getAssignedContent();
    Class<T> getAssignedContentClass();
}
