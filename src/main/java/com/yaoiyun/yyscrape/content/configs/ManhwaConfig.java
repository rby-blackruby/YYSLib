package com.yaoiyun.yyscrape.content.configs;

import java.util.List;

public interface ManhwaConfig {
    List<String> getValidImageExtensions();
    List<String> getRequiredKeywordsInImageUrl();
    String getChapterUrlRegex();
}
