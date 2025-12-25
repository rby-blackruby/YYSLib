package com.yaoiyun.yyscrape.scraper.scrapestrategy;

import java.util.List;

public class BattwoManhwaScrapeStrategy implements ManhwaScrapeStrategy {

    public BattwoManhwaScrapeStrategy() {

    }

    @Override
    public List<String> getValidImageExtensions() {
        return List.of(".webp", ".jpeg", ".png");
    }

    @Override
    public List<String> getRequiredKeywordsInImageUrl() {
        return List.of("media/mbch", "media/7006", "media/mbup");
    }

    @Override
    public String getChapterUrlRegex() {
        return "ch_(\\d+)(?:[-\\.](\\d+))?(?:[-\\.](\\d+))?";
    }

}
