package com.yaoiyun.yyscrape.scraper;

import com.yaoiyun.yyscrape.model.ScrapableContent;
import org.openqa.selenium.WebDriver;

public abstract class AbstractScraper implements Scraper {
    private final String outputFolder;
    private final WebDriver webDriver;
    private final short executionThreads;
    private final ScrapableContent assignedContent;

    public AbstractScraper(Builder builder) {
        this.outputFolder = builder.outputFolder;
        this.webDriver = builder.webDriver;
        this.executionThreads = builder.executionThreads;
        this.assignedContent = builder.assignedContent;
    }

    public String getOutputFolder() {
        return outputFolder;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public short getExecutionThreads() {
        return executionThreads;
    }

    public ScrapableContent getAssignedContent() {
        return assignedContent;
    }

    @Override
    public abstract void run();

    public static abstract class Builder<B extends Builder<B>> {
        private String outputFolder;
        private WebDriver webDriver;
        private short executionThreads;
        private ScrapableContent assignedContent;

        public Builder() {

        }

        @SuppressWarnings("unchecked")
        public B setOutputFolder(String outputFolder) {
            this.outputFolder = outputFolder;
            return (B) this;
        }

        @SuppressWarnings("unchecked")
        public B initWebdriver(WebDriver webDriver) {
            this.webDriver = webDriver;
            return (B) this;
        }

        @SuppressWarnings("unchecked")
        public B setExecutionThreads(short executionThreads) {
            if(executionThreads < 1) {
                throw new IllegalArgumentException("Execution threads cannot be smaller than 1. Current value: " + executionThreads);
            }
            this.executionThreads = executionThreads;
            return (B) this;
        }

        @SuppressWarnings("unchecked")
        public B assignContent(ScrapableContent content) {
            this.assignedContent = content;
            return (B) this;
        }

        public abstract AbstractScraper build();
    }
}
