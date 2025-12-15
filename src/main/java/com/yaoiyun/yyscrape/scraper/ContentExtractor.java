package com.yaoiyun.yyscrape.scraper;

import com.yaoiyun.yyscrape.content.ScrapableContent;
import org.openqa.selenium.WebDriver;

public abstract class ContentExtractor<T extends ScrapableContent> extends GenericScraper<T> implements ContentScraper<T> {

    public ContentExtractor(WebDriver webDriver, short executionThreads, T assignedContent, Class<T> assignedContentClass) {
        super(webDriver, executionThreads, assignedContent, assignedContentClass);
    }

}
