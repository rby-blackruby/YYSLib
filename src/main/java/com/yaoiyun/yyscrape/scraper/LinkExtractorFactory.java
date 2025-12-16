package com.yaoiyun.yyscrape.scraper;

import com.yaoiyun.yyscrape.content.Manhwa;
import com.yaoiyun.yyscrape.content.Novel;
import com.yaoiyun.yyscrape.content.ScrapableContent;
import com.yaoiyun.yyscrape.content.Series;
import com.yaoiyun.yyscrape.scraper.link.ManhwaLinkExtractor;
import com.yaoiyun.yyscrape.scraper.link.NovelLinkExtractor;
import com.yaoiyun.yyscrape.scraper.link.SeriesLinkExtractor;
import org.openqa.selenium.WebDriver;

public class LinkExtractorFactory {

    private LinkExtractorFactory() {
    }

    public static LinkExtractor<? extends ScrapableContent> createFrom(WebDriver webDriver, short executionThreads, ScrapableContent content) {
        if (content instanceof Manhwa manhwa) {
            return new ManhwaLinkExtractor(webDriver,executionThreads, manhwa);
        } else if (content instanceof Novel novel) {
            return new NovelLinkExtractor(webDriver, executionThreads, novel);
        } else if (content instanceof Series series) {
            return new SeriesLinkExtractor(webDriver, executionThreads, series);
        }
        throw new IllegalArgumentException("Unsupported content type: " + content.getClass());
    }
}