package com.yaoiyun.yyscrape.scraper.content;

import com.yaoiyun.yyscrape.scraper.ScrapeResult;
import com.yaoiyun.yyscrape.scraper.exception.HttpResponseException;
import com.yaoiyun.yyscrape.utils.ImageUtils;
import org.openqa.selenium.Cookie;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImageDownloadClient implements AutoCloseable{
    private static final Logger LOGGER = Logger.getLogger(ImageDownloadClient.class.getName());
    private static final int IMAGE_SPLITTING_THRESHOLD_PX = 15000; //px

    private final HttpClient httpClient;
    private final List<String> imageUrls;
    private final Set<Cookie> cookies;
    private String referralUrl;

    private ImageDownloadClient(Builder builder) {
        this.httpClient = Objects.requireNonNullElseGet(builder.client, HttpClient::newHttpClient);

        if(builder.imageUrls != null && !builder.imageUrls.isEmpty()) {
            this.imageUrls = builder.imageUrls;
        } else {
            throw new RuntimeException("You must assign an image url list to use the download client.");
        }

        this.cookies = Objects.requireNonNullElseGet(builder.cookies, HashSet::new);

        if(builder.referralUrl != null) {
            this.referralUrl = builder.referralUrl;
        }

        if(referralUrl != null) {
            this.referralUrl = builder.referralUrl;
        }
    }

    public List<ScrapeResult> retrieveImages() {
        List<ScrapeResult> retrievedImages = new ArrayList<>();

        for(var imageUrl : imageUrls) {
            LOGGER.info(() -> "Trying to collect image data from " + imageUrl);
            HttpRequest request = buildHttpRequestFor(URI.create(imageUrl), cookies);
            byte[] result = retrieveBody(httpClient, request);

            // Detect image format name from the image url
            final String imageExtension = ImageUtils.detectImageExtensionFromUrl(imageUrl);
            LOGGER.info(() -> "Detected image extension for image: " + imageExtension + " with url: " + imageUrl);

            // Check image size if it needs splitting then return ScrapeResult objects
            BufferedImage image;
            try {
                image = ImageIO.read(new ByteArrayInputStream(result));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if(image.getHeight() > IMAGE_SPLITTING_THRESHOLD_PX) {
                LOGGER.info("Image exceeds splitting threshold. Trying to split image into subimages...");
                List<byte[]> splitImages = ImageUtils.splitLargeImage(image, imageExtension, IMAGE_SPLITTING_THRESHOLD_PX);
                splitImages.forEach(splitImage -> retrievedImages.add(ScrapeResult.ofImage(splitImage, imageExtension)));
            } else {
                retrievedImages.add(ScrapeResult.ofImage(result, imageExtension));
            }
        }

        return retrievedImages;
    }

    private HttpRequest buildHttpRequestFor(URI imageURI, Set<Cookie> cookies) {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder();
        cookies.forEach(cookie -> requestBuilder.header(cookie.getName(), cookie.getValue()));

        if(referralUrl != null) {
            requestBuilder.header("Referer", referralUrl);
        }

        return requestBuilder.header("User-Agent",
                        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) " +
                                "Chrome/91.0.4472.124 Safari/537.36")
                .uri(imageURI)
                .build();
    }

    private byte[] retrieveBody(HttpClient client, HttpRequest request) {
        final HttpResponse<byte[]> response;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
            // TODO: add custom exceptions
        } catch (IOException e) {
            throw new RuntimeException("Failed to download image:", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Failed to download image.", e);
        }

        LOGGER.log(Level.INFO, "Received response with status code: {0}", response.statusCode());

        if(response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new HttpResponseException(response.statusCode(),
                    "Response returned non-200 status code while trying to download " + request.uri());
        }

        return response.body();
    }

    @Override
    public void close() {
        try {
            httpClient.close();
        } catch (Exception e) {
            // TODO: create better exception
            throw new RuntimeException("Failed to close ImageDownloadClient httpClient.", e);
        }
    }

    public static class Builder {
        private HttpClient client;
        private List<String> imageUrls;
        private Set<Cookie> cookies;
        private String referralUrl;

        public Builder assignHttpClient(HttpClient client) {
            this.client = client;
            return this;
        }

        public Builder assignImageUrls(List<String> imageUrls) {
            this.imageUrls = imageUrls;
            return this;
        }

        public Builder setCookies(Set<Cookie> cookies) {
            this.cookies = cookies;
            return this;
        }

        public Builder setReferralUrl(String referralUrl) {
            this.referralUrl = referralUrl;
            return this;
        }

        public ImageDownloadClient build() {
            return new ImageDownloadClient(this);
        }
    }
}
