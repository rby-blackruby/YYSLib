package com.yaoiyun.yyscrape.scraper.link;

import com.yaoiyun.yyscrape.content.Series;
import com.yaoiyun.yyscrape.scraper.LinkExtractor;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class SeriesLinkExtractor extends LinkExtractor<Series> {

    public SeriesLinkExtractor(WebDriver webDriver, short executionThreads, Series assignedContent) {
        super(webDriver, executionThreads, assignedContent, Series.class);
    }

    @Override
    public List<String> getContentUrls() {
        return List.of();
    }

    @Override
    public void close() throws Exception {

    }
}
