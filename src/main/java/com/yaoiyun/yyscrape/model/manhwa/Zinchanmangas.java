package com.yaoiyun.yyscrape.model.manhwa;

import java.util.List;

public class Zinchanmangas extends Manhwa {

    public Zinchanmangas(String name, String url) {
        super(name, url);
    }

    @Override
    public List<String> getValidImageExtensions() {
        return List.of("data:image/jpeg;base64,");
    }

    @Override
    public List<String> getRequiredKeywordsInImageUrl() {
        return List.of("");
    }

}
