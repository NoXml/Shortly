package ru.shortly.controller.schemas;

public class Link {
    private final String url;
    private final String shortUrl;

    private Link(String url, String shortUrl) {
        this.url = url;
        this.shortUrl = shortUrl;
    }

    public String getUrl() {
        return url;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public static class Builder {
        private String url;
        private String shortUrl;

        public Builder withUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder withShortUrl(String shortUrl) {
            this.shortUrl = shortUrl;
            return this;
        }

        public Link build() {
            return new Link(url, shortUrl);
        }
    }

    @Override
    public String toString() {
        return "Link{" +
                "url='" + url + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                '}';
    }
}
