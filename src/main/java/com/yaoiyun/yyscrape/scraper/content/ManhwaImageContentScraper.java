package com.yaoiyun.yyscrape.scraper.content;

import com.yaoiyun.yyscrape.content.ScrapableContent;
import com.yaoiyun.yyscrape.content.ScrapableContentType;
import com.yaoiyun.yyscrape.scraper.AbstractScraperBase;
import com.yaoiyun.yyscrape.scraper.ContentScraper;
import com.yaoiyun.yyscrape.scraper.ScrapeResult;
import com.yaoiyun.yyscrape.scraper.exception.CloudflareBlockedException;
import com.yaoiyun.yyscrape.scraper.exception.HttpResponseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManhwaImageContentScraper extends AbstractScraperBase implements ContentScraper {
    private static final Logger LOGGER = Logger.getLogger(ManhwaImageContentScraper.class.getName());
    private final ImageUrlFilter imageUrlFilter;

    public ManhwaImageContentScraper(WebDriver webDriver, ScrapableContent assignedContent) {
        super(webDriver, assignedContent, ScrapableContentType.IMAGE);
        // Default setting for filtering image urls for mangabuddy.
        this.imageUrlFilter = new ImageUrlFilter("res/manga", List.of(".webp", ".jpeg", ".jpg", ".png"));
    }

    public ManhwaImageContentScraper(WebDriver webDriver, ScrapableContent assignedContent, ImageUrlFilter imageUrlFilter) {
        super(webDriver, assignedContent, ScrapableContentType.IMAGE);
        this.imageUrlFilter = imageUrlFilter;
    }

    @Override
    public List<ScrapeResult> getContents(String contentUrl) {
        LOGGER.info(() -> "Currently scraping content for " + this.getAssignedContent().getName());
        LOGGER.info(() -> "Opening website with contentUrl: " + contentUrl);
        this.getWebDriver().get(contentUrl);
        this.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        onPageLoadedAction();

        LOGGER.info("Searching for image content urls");
        final List<WebElement> imageContents = this.getWebDriver().findElements(By.cssSelector("img"));
        final List<String> manhwaImageUrls = imageContents.stream()
                .map(element -> element.getAttribute("src"))
                .filter(Objects::nonNull)
                .filter(imageUrl -> imageUrl.contains(imageUrlFilter.pathKeyword()))
                .filter(imageUrl -> {
                    for(String allowedImageExtension : imageUrlFilter.allowedImageExtensions()) {
                        if(imageUrl.contains(allowedImageExtension)) return true;
                    }
                    return false;
                })
                .toList();

        if(manhwaImageUrls.isEmpty()) {
            boolean isCloudflareBlocked = this.getWebDriver().findElements(By.cssSelector("a")).stream()
                    .map(WebElement::getText)
                    .anyMatch(pageText -> pageText.contains("Verify you are human by completing the action below.")
                            || pageText.contains("Cloudflare"));

            if(isCloudflareBlocked){
                throw new CloudflareBlockedException("Found 0 images. Manhwa link scraper is possibly being " +
                        "blocked by Cloudflare. URL: " + this.getAssignedContent().getUrl());
            }

            LOGGER.warning("Found 0 images. This might be caused by the website being empty or by an invalid ImageUrlFilter");
            // Prematurely return empty ScrapeResult array to stop the scrape logic from progressing to
            // creating HTTP clients and collecting cookies and etc.
            return new ArrayList<>();
        }

        LOGGER.info(() -> "Found " + manhwaImageUrls.size() + " images with urls: ");
        manhwaImageUrls.forEach(LOGGER::info);

        Set<Cookie> cookies = this.getWebDriver().manage().getCookies();

        List<ScrapeResult> scrapedImages = new ArrayList<>();

        try(HttpClient client = HttpClient.newHttpClient()) {
            for(String imageUrl : manhwaImageUrls) {
                LOGGER.info(() -> "Trying to collect image data from " + imageUrl);
                final URI imageURI = URI.create(imageUrl);

                HttpRequest.Builder requestBuilder = HttpRequest.newBuilder();
                for(Cookie cookie : cookies) {
                    requestBuilder.header(cookie.getName(), cookie.getValue());
                }

                HttpRequest request = requestBuilder.header("User-Agent",
                                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) " +
                                        "Chrome/91.0.4472.124 Safari/537.36")
                        .header("Referer", this.getWebDriver().getCurrentUrl())
                        .uri(imageURI)
                        .build();

                final HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
                LOGGER.log(Level.INFO, "Received response with status code: {0}", response.statusCode());

                if(response.statusCode() < 200 || response.statusCode() >= 300) {
                    throw new HttpResponseException(response.statusCode(),
                            "Response returned non-200 status code while trying to download " + imageUrl);
                }

                // TODO: get extension from image url:  >> url.split "." reverse array get index 0 <<
                final String imageExtension = Arrays.stream(imageUrl.split(".")).toList().reversed().getFirst();
                scrapedImages.add(ScrapeResult.ofImage(response.body()));
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Failed to download image from website: " + contentUrl, e);
        }

        return scrapedImages;
    }

    protected void onPageLoadedAction() {
        // Empty post load action to allow child classes to inject actions into the scrape logic.
    }

}
