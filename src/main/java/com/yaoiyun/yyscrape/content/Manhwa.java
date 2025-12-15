package com.yaoiyun.yyscrape.content;

import com.yaoiyun.yyscrape.content.configs.ManhwaConfig;

public class Manhwa extends ScrapableContent {
    protected final String DEFAULT_CHAPTER_URL_REGEX = this.getUrl() + "chapter-(\\d+)(?:-(\\d+))?(?:-(\\d+))?";
    private final ManhwaConfig manhwaConfig;

    public Manhwa(String name, String url, ManhwaConfig manhwaConfig) {
        super(name, url, ScrapableContentType.IMAGE);
        this.manhwaConfig = manhwaConfig;
    }

    public ManhwaConfig getManhwaConfig() {
        return manhwaConfig;
    }

}
