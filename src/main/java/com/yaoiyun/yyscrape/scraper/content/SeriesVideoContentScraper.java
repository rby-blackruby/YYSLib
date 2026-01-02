package com.yaoiyun.yyscrape.scraper.content;

import com.yaoiyun.yyscrape.content.ScrapableContent;
import com.yaoiyun.yyscrape.content.ScrapableContentType;
import com.yaoiyun.yyscrape.scraper.AbstractScraperBase;
import com.yaoiyun.yyscrape.scraper.ContentScraper;
import com.yaoiyun.yyscrape.scraper.ScrapeResult;
import org.openqa.selenium.WebDriver;

import java.io.InputStream;
import java.util.List;

// Apparently InputStream is what should be used for when downloading video media, needs more testing
// to see how it would work.
public class SeriesVideoContentScraper extends AbstractScraperBase implements ContentScraper {

    public SeriesVideoContentScraper(WebDriver webDriver, ScrapableContent assignedContent) {
        super(webDriver, assignedContent, ScrapableContentType.VIDEO);
    }

    @Override
    public List<ScrapeResult> getContents(String contentUrl) {
        return List.of();
    }
}
