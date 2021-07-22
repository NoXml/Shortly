package ru.shortly.controller.schemas;

public class ShortLink {
    private final String host;
    private final String id;
    private final String url;

    private ShortLink(String host, String id, String url) {
        this.host = host;
        this.id = id;
        this.url = url;
    }

    public String getHost() {
        return host;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return host + id;
    }

    public static class Builder {
        private String host;
        private String id;
        private String url;

        public Builder withHost(String host) {
            this.host = host;
            return this;
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withUrl() {
            this.url = host + id;
            return this;
        }

        public ShortLink build() {
            return new ShortLink(host, id, url);
        }
    }

    @Override
    public String toString() {
        return "ShortLink{" +
                "host='" + host + '\'' +
                ", id='" + id + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
