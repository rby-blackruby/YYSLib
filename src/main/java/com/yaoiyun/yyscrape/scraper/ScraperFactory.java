package com.yaoiyun.yyscrape.scraper;

import com.yaoiyun.yyscrape.model.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ScraperFactory {
    private final static Map<Class<? extends ScrapableContent>, Class<? extends Scraper<? extends ScrapableContent>>> scrapers = new ConcurrentHashMap<>();

    static {
        scrapers.put(Manhwa.class, ManhwaScraper.class);
        scrapers.put(Novel.class, NovelScraper.class);
        scrapers.put(Series.class, SeriesScraper.class);
    }

    public static void register(Class<? extends ScrapableContent> contentSuperClass, Class<? extends Scraper<? extends ScrapableContent>> scraperClass) {
        scrapers.put(contentSuperClass, scraperClass);
    }

    public static class Builder {
        private WebDriver webDriver;
        private short executionThreads = 1;

        public Builder initWebdriver(WebDriver webDriver) {
            this.webDriver = webDriver;
            return this;
        }

        public Builder setExecutionThreads(short executionThreads) {
            this.executionThreads = executionThreads;
            return this;
        }

        public Scraper<? extends ScrapableContent> buildScraperFor(ScrapableContent content) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
            Class<?> contentCategoryClass = content.getClass();

            while(!scrapers.containsKey(contentCategoryClass)) {
                if(contentCategoryClass == Object.class) {
                    throw new IllegalArgumentException("No scraper registered for the content's category class.");
                }

                contentCategoryClass = contentCategoryClass.getSuperclass();
            }

            if(webDriver == null) {
                throw new IllegalStateException("A webdriver must be provider for the scraper.");
            }

            Scraper<?> scraper = scrapers.get(contentCategoryClass)
                    .getDeclaredConstructor(WebDriver.class, short.class, contentCategoryClass)
                    .newInstance(webDriver, executionThreads, content);

            return scraper;
        }
    }
}
