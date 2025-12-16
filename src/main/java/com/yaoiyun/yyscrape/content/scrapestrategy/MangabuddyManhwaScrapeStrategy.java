package com.yaoiyun.yyscrape.content.scrapestrategy;

import java.util.List;

public class MangabuddyManhwaScrapeStrategy implements ManhwaScrapeStrategy {

    @Override
    public List<String> getValidImageExtensions() {
        return List.of(".webp", ".jpeg", ".jpg", ".png");
    }

    @Override
    public List<String> getRequiredKeywordsInImageUrl() {
        return List.of("/manga");
    }

    @Override
    public String getChapterUrlRegex() {
        return "";
    }

}
