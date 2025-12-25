package com.yaoiyun.yyscrape.scraper.link;

import com.yaoiyun.yyscrape.content.ScrapableContent;
import com.yaoiyun.yyscrape.content.ScrapableContentType;
import com.yaoiyun.yyscrape.scraper.AbstractScraperBase;
import com.yaoiyun.yyscrape.scraper.LinkScraper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultManhwaLinkScraper extends AbstractScraperBase implements LinkScraper {
    private final Pattern chapterUrlRegex = Pattern.compile("chapter-(\\d+)(?:-(\\d+))?(?:-(\\d+))?");

    public DefaultManhwaLinkScraper(WebDriver webDriver, ScrapableContent assignedContent) {
        super(webDriver, assignedContent, ScrapableContentType.IMAGE);
    }

    @Override
    public List<String> discoverContentUrls() {
        this.getWebDriver().get(this.getAssignedContent().getUrl());
        this.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        List<WebElement> elements = this.getWebDriver().findElements(By.cssSelector("a"));
        List<String> hrefs = elements.stream()
                .map(e -> e.getAttribute("href"))
                .filter(Objects::nonNull)
                .filter(href -> href.contains(this.getAssignedContent().getUrl()))
                .filter(href -> {
                    Matcher matcher = chapterUrlRegex.matcher(href);
                    return matcher.find();
                })
                .distinct()
                .toList();

        return hrefs;
    }

}
