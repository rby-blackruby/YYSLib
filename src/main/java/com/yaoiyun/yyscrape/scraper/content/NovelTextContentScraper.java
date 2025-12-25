package com.yaoiyun.yyscrape.scraper.content;

import com.yaoiyun.yyscrape.content.ScrapableContent;
import com.yaoiyun.yyscrape.content.ScrapableContentType;
import com.yaoiyun.yyscrape.scraper.AbstractScraperBase;
import com.yaoiyun.yyscrape.scraper.ContentScraper;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class NovelTextContentScraper extends AbstractScraperBase implements ContentScraper {

    public NovelTextContentScraper(WebDriver webDriver, ScrapableContent assignedContent) {
        super(webDriver, assignedContent, ScrapableContentType.TEXT);
    }

    @Override
    public List<byte[]> getContents(String contentUrl) {
        return List.of();
    }
}
