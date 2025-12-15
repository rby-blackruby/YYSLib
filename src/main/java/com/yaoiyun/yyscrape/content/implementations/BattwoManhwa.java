package com.yaoiyun.yyscrape.content.implementations;

import com.yaoiyun.yyscrape.content.Manhwa;

import java.util.List;

public class BattwoManhwa extends Manhwa {

    public BattwoManhwa(String name, String url) {
        super(name, url);
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
