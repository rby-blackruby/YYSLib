package com.yaoiyun.yyscrape.scraper.action;

import org.openqa.selenium.WebDriver;

public interface ScrapeActions {
    default void onPageLoadedAction(WebDriver webDriver) {
        // intentionally empty for no action done
    }
}
