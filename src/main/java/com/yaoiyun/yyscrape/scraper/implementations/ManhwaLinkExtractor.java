package com.yaoiyun.yyscrape.scraper.implementations;

import com.yaoiyun.yyscrape.content.Manhwa;
import com.yaoiyun.yyscrape.scraper.LinkExtractor;
import com.yaoiyun.yyscrape.utils.UrlUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ManhwaLinkExtractor extends LinkExtractor<Manhwa> {

    public ManhwaLinkExtractor(WebDriver webDriver, short executionThreads, Manhwa assignedContent) {
        super(webDriver, executionThreads, assignedContent, Manhwa.class);
    }

    @Override
    public List<String> getContentUrls() {
        this.getWebDriver().get(this.getAssignedContent().getUrl());
        this.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        List<WebElement> elements = this.getWebDriver().findElements(By.cssSelector("a"));
        List<String> hrefs = elements.stream()
                .map(e -> e.getAttribute("href"))
                .filter(Objects::nonNull)
                .filter(href -> href.contains(UrlUtils.getSanitizedUrl(this.getAssignedContent().getUrl()) + this.getAssignedContent().getManhwaConfig().getChapterUrlRegex()))
                .distinct()
                .toList();

        for(var href : hrefs) {
            System.out.println(href);
        }

        return List.of();
    }

    @Override
    public void close() throws Exception {

    }
}
