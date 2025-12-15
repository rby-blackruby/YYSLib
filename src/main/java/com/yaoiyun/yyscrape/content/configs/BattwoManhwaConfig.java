package com.yaoiyun.yyscrape.content.configs;

import java.util.List;

public class BattwoManhwaConfig implements ManhwaConfig {

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
