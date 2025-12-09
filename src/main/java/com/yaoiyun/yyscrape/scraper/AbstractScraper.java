package com.yaoiyun.yyscrape.scraper;

import org.openqa.selenium.WebDriver;

public class AbstractScraper implements Scraper {
    private final String outputFolder;
    private final WebDriver webDriver;
    private final short executionThreads;

    public AbstractScraper(Builder builder) {
        this.outputFolder = builder.outputFolder;
        this.webDriver = builder.webDriver;
        this.executionThreads = builder.executionThreads;
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

    public static abstract class Builder<B extends Builder<B>> {
        private String outputFolder;
        private WebDriver webDriver;
        private short executionThreads;

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

        public AbstractScraper build() {
            return new AbstractScraper(this);
        }
    }
}
