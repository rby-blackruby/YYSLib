package com.yaoiyun.yyscrape.content;

import com.yaoiyun.yyscrape.content.scrapestrategy.ManhwaScrapeStrategy;

public class Manhwa extends ScrapableContent {
    protected final String DEFAULT_CHAPTER_URL_REGEX = this.getUrl() + "chapter-(\\d+)(?:-(\\d+))?(?:-(\\d+))?";
    private final ManhwaScrapeStrategy manhwaScrapeStrategy;

    public Manhwa(String name, String url, ManhwaScrapeStrategy manhwaScrapeStrategy) {
        super(name, url, ScrapableContentType.IMAGE);
        this.manhwaScrapeStrategy = manhwaScrapeStrategy;
    }

    public ManhwaScrapeStrategy getScrapeStrategy() {
        return manhwaScrapeStrategy;
    }

}
