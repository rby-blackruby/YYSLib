package com.yaoiyun.yyscrape.scraper;

import java.util.List;

public interface ContentScraper {
    List<ScrapeResult> getContents(String contentUrl);
}
