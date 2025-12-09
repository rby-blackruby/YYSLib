package com.yaoiyun.yyscrape.scraper;

public class ManhwaScraper extends AbstractScraper {
    private final String manhwaScraperSpecificStringExample;

    private ManhwaScraper(Builder builder) {
        super(builder);
        manhwaScraperSpecificStringExample = builder.manhwaScraperSpecificStringExample;
    }

    public String getManhwaScraperSpecificStringExample() {
        return manhwaScraperSpecificStringExample;
    }

    @Override
    public void run() {
        this.getWebDriver().get(this.getAssignedContent().url());
    }

    public static class Builder extends AbstractScraper.Builder<Builder> {
        private String manhwaScraperSpecificStringExample;

        public Builder setManhwaScraperSpecificExample(String value) {
            this.manhwaScraperSpecificStringExample = value;
            return this;
        }

        @Override
        public ManhwaScraper build() {
            return new ManhwaScraper(this);
        }
    }
}
