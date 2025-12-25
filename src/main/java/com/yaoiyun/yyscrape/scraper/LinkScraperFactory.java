package com.yaoiyun.yyscrape.scraper;

import com.yaoiyun.yyscrape.content.ScrapableContent;
import com.yaoiyun.yyscrape.scraper.scrapestrategy.ManhwaScrapeStrategy;
import com.yaoiyun.yyscrape.utils.UrlUtils;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class LinkScraperFactory {
    private static final Map<String, ScraperSupplier<? extends LinkScraper>> LINK_SCRAPER_REGISTRY = new ConcurrentHashMap<>();

    private LinkScraperFactory() {
    }

    public static void register(String websiteBaseUrl, ScraperSupplier<? extends LinkScraper> contentScraperClass) {
        LINK_SCRAPER_REGISTRY.putIfAbsent(websiteBaseUrl, contentScraperClass);
    }

    public static LinkScraper getLinkScraperFor(ScrapableContent content) {
        String websiteBaseUrl = UrlUtils.getBaseUrl(content.getUrl());

        if (!LINK_SCRAPER_REGISTRY.containsKey(websiteBaseUrl)) {
            throw new IllegalArgumentException("The provided scrapable content is not associated with any link scrapper: " + websiteBaseUrl);
        }

        return LINK_SCRAPER_REGISTRY.get(websiteBaseUrl).get(content);
    }
}