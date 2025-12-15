# YYScrapeLib

A Java-based web scraping core library for extracting content from manga, manhwa, novels, and other serialized content platforms, designed with a modular, extensible architecture.

## Overview

YYScrape provides a clean abstraction over web scraping operations, separating concerns into:
- **Content Models**: Define what content is being scraped (Manhwa, Novel, Series)
- **Link Extraction**: Extract URLs/links from content pages
- **Content Extraction**: Download and process actual content (images, text, etc.)
- **Factories**: Create appropriate extractor implementations based on content type

## Key Features
- **Link Extraction**: Extract URLs from content pages independently
- **Content Extraction**: Download and process actual content (images, text, videos)
- **Content Types**: Support for Manhwa, Novels, Series, and extensible for new types
- **Factory Pattern**: Automatic instantiation of appropriate content implementations

### Extensibility
- **Generic Extractors**: Easy to add support for new extractor types and implementations
- **Factory-Based Dispatch**: Runtime type checking automatically selects correct implementation
- **Abstract Base Classes**: Inherit from `ContentExtractor`, `LinkExtractor` to create new implementations

## Dependencies

- **Selenium WebDriver** 4.39.0
- **JUnit Jupiter** 5.14.1
- **Java 21** 
