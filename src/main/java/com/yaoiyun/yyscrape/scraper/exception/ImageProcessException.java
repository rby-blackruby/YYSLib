package com.yaoiyun.yyscrape.scraper.exception;

public class ImageProcessException extends RuntimeException {
    public ImageProcessException(String message) {
        super(message);
    }
    public ImageProcessException(String message, Throwable cause) {
        super(message, cause);
    }
}
