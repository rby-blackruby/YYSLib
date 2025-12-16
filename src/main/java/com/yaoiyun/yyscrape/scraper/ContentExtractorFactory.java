package com.yaoiyun.yyscrape.scraper;

import com.yaoiyun.yyscrape.content.Manhwa;
import com.yaoiyun.yyscrape.content.Novel;
import com.yaoiyun.yyscrape.content.ScrapableContent;
import com.yaoiyun.yyscrape.content.Series;

import com.yaoiyun.yyscrape.scraper.content.ManhwaContentExtractor;
import com.yaoiyun.yyscrape.scraper.content.NovelContentExtractor;
import com.yaoiyun.yyscrape.scraper.content.SeriesContentExtractor;
import org.openqa.selenium.WebDriver;

public class ContentExtractorFactory {

    private ContentExtractorFactory() {
    }

    public static ContentExtractor<? extends ScrapableContent> createFrom(WebDriver webDriver, short executionThreads, ScrapableContent content) {
        if (content instanceof Manhwa manhwa) {
            return new ManhwaContentExtractor(webDriver,executionThreads, manhwa);
        } else if (content instanceof Novel novel) {
            return new NovelContentExtractor(webDriver, executionThreads, novel);
        } else if (content instanceof Series series) {
            return new SeriesContentExtractor(webDriver, executionThreads, series);
        }
        throw new IllegalArgumentException("Unsupported content type: " + content.getClass());
    }
}
