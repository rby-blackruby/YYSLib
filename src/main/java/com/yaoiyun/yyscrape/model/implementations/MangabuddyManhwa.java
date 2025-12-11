package com.yaoiyun.yyscrape.model.implementations;

import com.yaoiyun.yyscrape.model.Manhwa;

import java.util.List;

public class MangabuddyManhwa extends Manhwa {

    public MangabuddyManhwa(String name, String url) {
        super(name, url);
    }

    @Override
    public List<String> getValidImageExtensions() {
        return List.of(".webp", ".jpeg", ".jpg", ".png");
    }

    @Override
    public List<String> getRequiredKeywordsInImageUrl() {
        return List.of("/manga");
    }
}
