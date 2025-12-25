package com.yaoiyun.yyscrape.scraper.scrapestrategy;

import java.util.List;

public interface ManhwaScrapeStrategy {
    List<String> getValidImageExtensions();
    List<String> getRequiredKeywordsInImageUrl();
    default String getChapterUrlRegex() {
        return "chapter-(\\d+)(?:-(\\d+))?(?:-(\\d+))?";
    }
}
