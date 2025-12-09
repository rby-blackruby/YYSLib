package com.yaoiyun.yyscrape.scraper;

import org.openqa.selenium.WebDriver;

public class ScraperBuilder {
    private String outputFolder;
    private WebDriver webdriver;
    private short executionThreads = 1;

    private ScraperBuilder(String outputFolder, WebDriver webdriver) {
        this.outputFolder = outputFolder;
        this.webdriver = webdriver;

        if(executionThreads < 1) {
            throw new IllegalArgumentException("Thread count must be 1 or above.");
        }

        this.executionThreads = executionThreads;
    }

    public String getOutputFolder() { return outputFolder; }

    public void setOutputFolder(String outputFolder) {
        this.outputFolder = outputFolder;
    }

    public WebDriver getWebdriver() { return webdriver; }

    public void setWebdriver(WebDriver webdriver) {
        this.webdriver = webdriver;
    }

    public short getThreads() { return executionThreads; }

    public void setThreads(short executionThreads) {
        if(executionThreads < 1) {
            throw new IllegalArgumentException("Thread count must be 1 or above.");
        }

        this.executionThreads = executionThreads;
    }
}
