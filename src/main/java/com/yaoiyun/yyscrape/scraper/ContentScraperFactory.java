package com.yaoiyun.yyscrape.scraper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public final class ContentScraperFactory {
    private static final Map<String, Supplier<? extends ContentScraper>> CONTENT_SCRAPER_REGISTRY = new ConcurrentHashMap<>();

    private ContentScraperFactory() {
    }

    public static void register(String websiteBaseUrl, Supplier<? extends ContentScraper> contentScraperClass) {
        CONTENT_SCRAPER_REGISTRY.putIfAbsent(websiteBaseUrl, contentScraperClass);
    }

    public static ContentScraper getContentScraper(String websiteBaseUrl) {
        if (!CONTENT_SCRAPER_REGISTRY.containsKey(websiteBaseUrl)) {
            throw new IllegalArgumentException("The provided url is not associated with any scrapper: " + websiteBaseUrl);
        }

        return CONTENT_SCRAPER_REGISTRY.get(websiteBaseUrl).get();
    }
}
