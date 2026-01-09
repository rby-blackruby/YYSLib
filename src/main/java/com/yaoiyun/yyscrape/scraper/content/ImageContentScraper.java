package com.yaoiyun.yyscrape.scraper.content;

import com.yaoiyun.yyscrape.content.ScrapableContent;
import com.yaoiyun.yyscrape.content.ScrapableContentType;
import com.yaoiyun.yyscrape.scraper.AbstractScraperBase;
import com.yaoiyun.yyscrape.scraper.ContentScraper;
import com.yaoiyun.yyscrape.scraper.ScrapeResult;
import com.yaoiyun.yyscrape.scraper.action.ScrapeActions;
import com.yaoiyun.yyscrape.scraper.exception.CloudflareBlockedException;
import com.yaoiyun.yyscrape.scraper.filter.ImageUrlFilter;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.http.HttpClient;
import java.time.Duration;
import java.util.*;
import java.util.logging.Logger;

public class ImageContentScraper extends AbstractScraperBase implements ContentScraper {
    private static final Logger LOGGER = Logger.getLogger(ImageContentScraper.class.getName());
    private ImageUrlFilter imageUrlFilter = new ImageUrlFilter(List.of(""), List.of(".webp", ".jpeg", ".jpg", ".png"));
    private ScrapeActions scrapeActions = new ScrapeActions() {};

    public ImageContentScraper(WebDriver webDriver, ScrapableContent assignedContent) {
        super(webDriver, assignedContent, ScrapableContentType.IMAGE);
    }

    public ImageContentScraper(WebDriver webDriver, ScrapableContent assignedContent, ImageUrlFilter imageUrlFilter) {
        super(webDriver, assignedContent, ScrapableContentType.IMAGE);
        this.imageUrlFilter = imageUrlFilter;
    }

    public ImageContentScraper(WebDriver webDriver, ScrapableContent assignedContent, ScrapeActions scrapeActions) {
        super(webDriver, assignedContent, ScrapableContentType.IMAGE);
        this.scrapeActions = scrapeActions;
    }

    public ImageContentScraper(WebDriver webDriver, ScrapableContent assignedContent, ImageUrlFilter imageUrlFilter, ScrapeActions scrapeActions) {
        super(webDriver, assignedContent, ScrapableContentType.IMAGE);
        this.imageUrlFilter = imageUrlFilter;
        this.scrapeActions = scrapeActions;
    }

    @Override
    public List<ScrapeResult> getContents(String contentUrl) {
        LOGGER.info(() -> "Currently scraping content for " + this.getAssignedContent().getName());
        LOGGER.info(() -> "Opening website with contentUrl: " + contentUrl);
        this.getWebDriver().get(contentUrl);
        this.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        if(super.isCloudflareBlocked()) {
            throw new CloudflareBlockedException("Manhwa link scraper is possibly being " +
                    "blocked by Cloudflare. URL: " + this.getAssignedContent().getUrl());
        }

        scrapeActions.onPageLoadedAction(this.getWebDriver());

        LOGGER.info("Searching for image content urls");
        final List<WebElement> pageImageContentList = this.getWebDriver().findElements(By.cssSelector("img"));
        final List<String> manhwaImageUrls = filterImageUrls(pageImageContentList, imageUrlFilter);

        if(manhwaImageUrls.isEmpty()) {
            LOGGER.warning("Found 0 images. This might be caused by the website being empty or by an invalid ImageUrlFilter");
            return new ArrayList<>();
        }

        LOGGER.info(() -> "Found " + manhwaImageUrls.size() + " images with urls: ");
        manhwaImageUrls.forEach(LOGGER::info);

        ImageDownloadClient imageDownloadClient = new ImageDownloadClient.Builder()
                .assignHttpClient(HttpClient.newHttpClient())
                .assignImageUrls(manhwaImageUrls)
                .setCookies(this.getWebDriver().manage().getCookies())
                .setReferralUrl(this.getWebDriver().getCurrentUrl())
                .build();

        List<ScrapeResult> scrapedImages;
        try(imageDownloadClient) {
            scrapedImages = imageDownloadClient.retrieveImages();
        }

        LOGGER.info("Finished retrieving content from url: " + contentUrl);
        return scrapedImages;
    }

    protected List<String> filterImageUrls(List<WebElement> webElements, ImageUrlFilter imageUrlFilter) {
        return webElements.stream()
                .map(element -> element.getAttribute("src"))
                .filter(Objects::nonNull)
                .filter(imageUrl -> {
                    for(String pathKeyword : imageUrlFilter.pathKeywords()) {
                        if(imageUrl.contains(pathKeyword)) return true;
                    }
                    return false;
                })
                .filter(imageUrl -> {
                    for(String allowedImageExtension : imageUrlFilter.allowedImageExtensions()) {
                        if(imageUrl.contains(allowedImageExtension)) return true;
                    }
                    return false;
                })
                .toList();
    }

}
