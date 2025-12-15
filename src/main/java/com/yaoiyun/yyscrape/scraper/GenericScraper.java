package com.yaoiyun.yyscrape.scraper;

import com.yaoiyun.yyscrape.content.ScrapableContent;
import org.openqa.selenium.WebDriver;

public abstract class GenericScraper<T extends ScrapableContent> implements ContentAssignable<T>, AutoCloseable {
    private final WebDriver webDriver;
    private final short executionThreads;
    private final T assignedContent;
    private final Class<T> assignedContentClass;

    public GenericScraper(WebDriver webDriver, short executionThreads, T assignedContent, Class<T> assignedContentClass) {
        this.webDriver = webDriver;
        this.executionThreads = executionThreads;
        this.assignedContent = assignedContent;
        this.assignedContentClass = assignedContentClass;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public short getExecutionThreads() {
        return executionThreads;
    }

    @Override
    public T getAssignedContent() {
        return assignedContent;
    }

    @Override
    public Class<T> getAssignedContentClass() {
        return assignedContentClass;
    }


}
