package com.yaoiyun.yyscrape.content.factories;

import com.yaoiyun.yyscrape.content.ScrapableContent;
import com.yaoiyun.yyscrape.content.ScrapableContentType;
import com.yaoiyun.yyscrape.content.implementations.BattwoManhwa;
import com.yaoiyun.yyscrape.content.implementations.MangabuddyManhwa;

public class ScrapableContentFactory {

    private ScrapableContentFactory() {

    }

    public static <T extends ScrapableContent> T getContent(String name, String url, ScrapableContentType contentType) {
        ScrapableContent content = switch(contentType) {
            case ScrapableContentType.IMAGE -> ManhwaFactory.getManhwa(name, url);

            // TODO: switch these out to actually implemented factories
            //
            case ScrapableContentType.TEXT -> new BattwoManhwa("Name", "Url");
            case ScrapableContentType.VIDEO -> new MangabuddyManhwa("Name", "Url");
        };

        @SuppressWarnings("unchecked")
        T c = (T) content;
        return c;
    }
}
