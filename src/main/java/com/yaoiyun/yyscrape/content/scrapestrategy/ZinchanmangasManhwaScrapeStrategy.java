package com.yaoiyun.yyscrape.content.scrapestrategy;

import java.util.List;

public class ZinchanmangasManhwaScrapeStrategy implements ManhwaScrapeStrategy {

    @Override
    public List<String> getValidImageExtensions() {
        return List.of("data:image/jpeg;base64,");
    }

    @Override
    public List<String> getRequiredKeywordsInImageUrl() {
        return List.of("");
    }

    @Override
    public String getChapterUrlRegex() {
        return "";
    }

}
