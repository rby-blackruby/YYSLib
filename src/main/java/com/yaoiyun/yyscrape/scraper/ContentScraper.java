package com.yaoiyun.yyscrape.scraper;

import java.awt.image.BufferedImage;
import java.util.List;

public interface ContentScraper {

    List<byte[]> getContents(String contentUrl);
    String getAssignedContentUrl();
}
