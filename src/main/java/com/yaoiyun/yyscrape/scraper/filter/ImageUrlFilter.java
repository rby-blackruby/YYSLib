package com.yaoiyun.yyscrape.scraper.filter;

import java.util.List;

public record ImageUrlFilter(String pathKeyword, List<String> allowedImageExtensions) {
}
