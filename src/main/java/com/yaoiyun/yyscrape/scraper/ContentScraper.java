package com.yaoiyun.yyscrape.scraper;

import java.awt.image.BufferedImage;
import java.util.List;

public interface ContentScraper {
    List<BufferedImage> getContents(String contentUrl);
}
