package com.yaoiyun.yyscrape.scraper.content;

import com.yaoiyun.yyscrape.content.ScrapableContent;
import com.yaoiyun.yyscrape.content.ScrapableContentType;
import com.yaoiyun.yyscrape.scraper.AbstractScraperBase;
import com.yaoiyun.yyscrape.scraper.ContentScraper;
import com.yaoiyun.yyscrape.scraper.scrapestrategy.ManhwaScrapeStrategy;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class ManhwaImageContentScraper extends AbstractScraperBase implements ContentScraper {

    public ManhwaImageContentScraper(WebDriver webDriver, ScrapableContent assignedContent, ManhwaScrapeStrategy scrapeStrategy) {
        super(webDriver, assignedContent, ScrapableContentType.IMAGE);
    }

    @Override
    public List<byte[]> getContents(String contentUrl) {
        return List.of();
    }

}
