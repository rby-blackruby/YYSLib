package com.yaoiyun.yyscrape.scraper.content;

import com.yaoiyun.yyscrape.content.ScrapableContent;
import com.yaoiyun.yyscrape.content.ScrapableContentType;
import com.yaoiyun.yyscrape.scraper.AbstractScraperBase;
import com.yaoiyun.yyscrape.scraper.ContentScraper;
import org.openqa.selenium.WebDriver;

import java.awt.image.BufferedImage;
import java.util.List;

public class SeriesVideoContentScraper extends AbstractScraperBase implements ContentScraper {

    public SeriesVideoContentScraper(WebDriver webDriver, ScrapableContent assignedContent) {
        super(webDriver, assignedContent, ScrapableContentType.VIDEO);
    }

    @Override
    public List<BufferedImage> getContents(String contentUrl) {
        return List.of();
    }
}
