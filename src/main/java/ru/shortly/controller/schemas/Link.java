package ru.shortly.controller.schemas;

public class Link extends NewLink {
    private String shortUrl;

    public Link(String url, String shortUrl) {
        super(url);
        this.shortUrl = shortUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
