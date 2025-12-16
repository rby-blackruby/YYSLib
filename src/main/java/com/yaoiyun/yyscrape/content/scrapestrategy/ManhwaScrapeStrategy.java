package com.yaoiyun.yyscrape.content.scrapestrategy;

import java.util.List;

public interface ManhwaScrapeStrategy {
    List<String> getValidImageExtensions();
    List<String> getRequiredKeywordsInImageUrl();
    String getChapterUrlRegex();
}
