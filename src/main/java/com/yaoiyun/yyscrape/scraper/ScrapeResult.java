package com.yaoiyun.yyscrape.scraper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ScrapeResult {
    private final byte[] scrapedContentBytes;
    private final String contentType;

    private ScrapeResult(byte[] content, String contentType) {
        this.scrapedContentBytes = content;
        this.contentType = contentType;
    }

    public static ScrapeResult ofImage(byte[] imageBytes) {
       return ofImage(imageBytes, "png");
    }

    public static ScrapeResult ofImage(byte[] imageBytes, String imageExt) {
        return new ScrapeResult(imageBytes, "image/" + imageExt);
    }

    public static ScrapeResult ofText(String text) {
        return ofText(text, StandardCharsets.UTF_8);
    }

    public static ScrapeResult ofText(String text, Charset charset) {
        byte[] textBytes = text.getBytes(charset);
        return new ScrapeResult(textBytes, "text/plain");
    }

    public static ScrapeResult ofVideo(List<InputStream> videoInputStreamList) {
        throw new UnsupportedOperationException("Video content result Not implemented");
    }

    public byte[] getScrapedContentBytes() {
        return scrapedContentBytes;
    }

    public String getContentType() {
        return contentType;
    }

    public BufferedImage tryUnwrapAsImage() {
        if(!contentType.startsWith("image/")) {
            throw new IllegalArgumentException("Cannot convert non-image content type to buffered image: " + contentType);
        }

        ByteArrayInputStream bais = new ByteArrayInputStream(scrapedContentBytes);
        BufferedImage image;

        try {
            image = ImageIO.read(bais);
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert scrape result to image.", e);
        }

        if (image == null)
            throw new IllegalArgumentException("The provided byte[] does not contain valid image data.");

        return image;
    }

    public String tryUnwrapAsString() {
        return tryUnwrapAsString(StandardCharsets.UTF_8);
    }

    // this too
    public String tryUnwrapAsString(Charset charset) {
        if(!contentType.startsWith("text/")) {
            throw new IllegalArgumentException("Cannot convert non-text type content to text: " + contentType);
        }

        return new String(scrapedContentBytes, charset);
    }
}
