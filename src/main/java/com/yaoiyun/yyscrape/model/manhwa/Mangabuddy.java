package com.yaoiyun.yyscrape.model.manhwa;

import java.util.List;

public class Mangabuddy extends Manhwa {

    public Mangabuddy(String name, String url) {
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
