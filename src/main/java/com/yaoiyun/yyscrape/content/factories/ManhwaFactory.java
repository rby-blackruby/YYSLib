package com.yaoiyun.yyscrape.content.factories;

import com.yaoiyun.yyscrape.content.Manhwa;
import com.yaoiyun.yyscrape.content.implementations.BattwoManhwa;
import com.yaoiyun.yyscrape.content.implementations.MangabuddyManhwa;
import com.yaoiyun.yyscrape.content.implementations.ZinchanmangasManhwa;
import com.yaoiyun.yyscrape.utils.UrlUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ManhwaFactory {
    private static Map<String, Class<? extends Manhwa>> manhwaRegistry = new ConcurrentHashMap<>();

    static {
        manhwaRegistry.put("mangabuddy.com", MangabuddyManhwa.class);
        manhwaRegistry.put("zinchanmangas.net", ZinchanmangasManhwa.class);
        manhwaRegistry.put("battwo.com", BattwoManhwa.class);
    }

    // TODO: more elaborate exception handling
    public static Manhwa getManhwa(String name, String url) {
        String baseUrl = UrlUtils.getBaseUrl(url);
        try {
            return manhwaRegistry.get(baseUrl)
                    .getDeclaredConstructor(String.class, String.class)
                    .newInstance(name, url);
        } catch (Exception e) {
            throw new RuntimeException("" + e);
        }
    }

    public static void register(String baseUrl, Class<? extends Manhwa> implementationClass) {
        manhwaRegistry.put(baseUrl, implementationClass);
    }
}
