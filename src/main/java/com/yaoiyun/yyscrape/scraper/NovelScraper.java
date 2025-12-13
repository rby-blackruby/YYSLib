package com.yaoiyun.yyscrape.scraper;

import com.yaoiyun.yyscrape.model.Novel;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class NovelScraper extends GenericScraper<Novel>{
    public NovelScraper(WebDriver webDriver, short executionThreads, Novel assignedContent) {
        super(webDriver, executionThreads, assignedContent, Novel.class);
    }

    @Override
    public List<String> getContentUrls() {
        return List.of();
    }

    @Override
    public List<byte[]> getContents() {
        return List.of();
    }

    @Override
    public void close() throws Exception {

    }
}
