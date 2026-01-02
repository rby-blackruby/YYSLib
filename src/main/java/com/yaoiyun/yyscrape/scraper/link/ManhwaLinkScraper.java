package com.yaoiyun.yyscrape.scraper.link;

import com.yaoiyun.yyscrape.content.ScrapableContent;
import com.yaoiyun.yyscrape.content.ScrapableContentType;
import com.yaoiyun.yyscrape.scraper.AbstractScraperBase;
import com.yaoiyun.yyscrape.scraper.LinkScraper;
import com.yaoiyun.yyscrape.scraper.exception.CloudflareBlockedException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManhwaLinkScraper extends AbstractScraperBase implements LinkScraper, AutoCloseable {
    protected final Pattern chapterUrlRegex;

    public ManhwaLinkScraper(WebDriver webDriver, ScrapableContent assignedContent) {
        super(webDriver, assignedContent, ScrapableContentType.IMAGE);
        chapterUrlRegex = Pattern.compile("chapter-(\\d+)(?:-(\\d+))?(?:-(\\d+))?");
    }

    public ManhwaLinkScraper(WebDriver webDriver, ScrapableContent assignedContent, String chapterUrlRegex) {
        super(webDriver, assignedContent, ScrapableContentType.IMAGE);
        this.chapterUrlRegex = Pattern.compile(chapterUrlRegex);
    }

    @Override
    public List<String> discoverContentUrls() {
        this.getWebDriver().get(this.getAssignedContent().getUrl());
        // TODO: use explicit wait instead of implicit x seconds
        this.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


        postLoadAction();


        List<WebElement> elements = findWebsiteElementsByCssSelector("a");
        for(var element : elements) {
            if(element.getText().contains("Cloudflare")) {
                throw new CloudflareBlockedException("Manhwa link scraper blocked by Cloudflare. URL: " + this.getAssignedContent().getUrl());
            }
        }

        return getFilteredChapterUrls(elements);
    }

    protected void postLoadAction() {
        new Actions(this.getWebDriver()).scrollByAmount(0, 20000).perform();
        this.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    protected List<WebElement> findWebsiteElementsByCssSelector(String selector) {
        return this.getWebDriver().findElements(By.cssSelector(selector));
    }

    protected List<String> getFilteredChapterUrls(List<WebElement> websiteElements) {
        return websiteElements.stream()
                .map(e -> e.getAttribute("href"))
                .filter(Objects::nonNull)
                .filter(href -> href.contains(this.getAssignedContent().getUrl()))
                .filter(href -> {
                    Matcher matcher = chapterUrlRegex.matcher(href);
                    return matcher.find();
                })
                .distinct()
                .toList();
    }

    @Override
    public void close() {
        this.getWebDriver().quit();
    }

}
