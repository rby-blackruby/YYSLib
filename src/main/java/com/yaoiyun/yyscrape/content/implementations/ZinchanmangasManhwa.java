package com.yaoiyun.yyscrape.content.implementations;

import com.yaoiyun.yyscrape.content.Manhwa;

import java.util.List;

public class ZinchanmangasManhwa extends Manhwa {

    public ZinchanmangasManhwa(String name, String url) {
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
