package com.yaoiyun.yyscrape.scraper;

import com.yaoiyun.yyscrape.model.Manhwa;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class ManhwaScraper extends GenericScraper<Manhwa> {

    public ManhwaScraper(WebDriver webDriver, short executionThreads, Manhwa assignedContent) {
        super(webDriver, executionThreads, assignedContent, Manhwa.class);
    }

    @Override
    public List<String> getContentUrls() {
        this.getWebDriver().get("https://" + this.getAssignedContent().getUrl());
        return List.of("url 1", "url 2");
    }

    @Override
    public List<byte[]> getContents() {
        return List.of();
    }

    @Override
    public void close() {
        this.getWebDriver().close();
    }

}
