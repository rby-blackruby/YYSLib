package com.yaoiyun.yyscrape.scraper.filter;

import java.util.List;

public record ImageUrlFilter(List<String> pathKeywords, List<String> allowedImageExtensions) {
}
