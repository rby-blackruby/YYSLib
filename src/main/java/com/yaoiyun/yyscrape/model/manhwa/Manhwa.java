package com.yaoiyun.yyscrape.model.manhwa;

import com.yaoiyun.yyscrape.model.ScrapableContent;
import com.yaoiyun.yyscrape.model.ScrapableContentType;

import java.util.List;

public abstract class Manhwa extends ScrapableContent {
    protected final String DEFAULT_CHAPTER_URL_REGEX = this.getUrl() + "chapter-(\\d+)(?:-(\\d+))?(?:-(\\d+))?";

    public Manhwa(String name, String url) {
        super(name, url, ScrapableContentType.IMAGE);
    }

    public abstract List<String> getValidImageExtensions();
    public abstract List<String> getRequiredKeywordsInImageUrl();
    public String getChapterUrlRegex() {
        return DEFAULT_CHAPTER_URL_REGEX;
    }
}
