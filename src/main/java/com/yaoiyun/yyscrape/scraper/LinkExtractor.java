package com.yaoiyun.yyscrape.scraper;

import com.yaoiyun.yyscrape.content.ScrapableContent;
import org.openqa.selenium.WebDriver;

public abstract class LinkExtractor<T extends ScrapableContent> extends GenericScraper<T> implements LinkScraper<T>, AutoCloseable {

    public LinkExtractor(WebDriver webDriver, short executionThreads, T assignedContent, Class<T> assignedContentClass) {
        super(webDriver, executionThreads, assignedContent, assignedContentClass);
    }
}
