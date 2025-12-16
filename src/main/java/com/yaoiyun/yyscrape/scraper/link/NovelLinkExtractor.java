package com.yaoiyun.yyscrape.scraper.link;

import com.yaoiyun.yyscrape.content.Novel;
import com.yaoiyun.yyscrape.scraper.LinkExtractor;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class NovelLinkExtractor extends LinkExtractor<Novel> {

    public NovelLinkExtractor(WebDriver webDriver, short executionThreads, Novel assignedContent) {
        super(webDriver, executionThreads, assignedContent, Novel.class);
    }

    @Override
    public List<String> getContentUrls() {
        return List.of();
    }

    @Override
    public void close() throws Exception {

    }
}
