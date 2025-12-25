package com.yaoiyun.yyscrape.scraper.scrapestrategy;

import java.util.List;

public class ZinchanmangasManhwaScrapeStrategy implements ManhwaScrapeStrategy {

    public ZinchanmangasManhwaScrapeStrategy() {

    }

    @Override
    public List<String> getValidImageExtensions() {
        return List.of("data:image/jpeg;base64,");
    }

    @Override
    public List<String> getRequiredKeywordsInImageUrl() {
        return List.of("/manga");
    }

}
