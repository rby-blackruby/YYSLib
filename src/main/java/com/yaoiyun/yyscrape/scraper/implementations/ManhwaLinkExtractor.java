package com.yaoiyun.yyscrape.scraper.implementations;

import com.yaoiyun.yyscrape.content.Manhwa;
import com.yaoiyun.yyscrape.scraper.LinkExtractor;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class ManhwaLinkExtractor extends LinkExtractor<Manhwa> {

    public ManhwaLinkExtractor(WebDriver webDriver, short executionThreads, Manhwa assignedContent) {
        super(webDriver, executionThreads, assignedContent, Manhwa.class);
    }

    @Override
    public List<String> getContentUrls() {
        return List.of();
    }

    @Override
    public void close() throws Exception {

    }
}
