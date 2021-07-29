package ru.shortly.controller.schemas;

import java.util.Objects;

public class Link {
    private final ShortLink shortLink;
    private final String longLink;

    private Link(ShortLink shortLink, String longLink) {
        this.shortLink = Objects.requireNonNull(shortLink, "shortLink");
        this.longLink = Objects.requireNonNull(longLink, "longLink");
    }

    public ShortLink getShortLink() {
        return shortLink;
    }

    public String getLongLink() {
        return longLink;
    }

    public static class Builder {
        private ShortLink shortLink;
        private String longLink;

        public Builder withShortLink(ShortLink shortLink) {
            this.shortLink = shortLink;
            return this;
        }

        public Builder withLongLink(String longLink) {
            this.longLink = longLink;
            return this;
        }

        public Link build() {
            return new Link(shortLink, longLink);
        }
    }

    @Override
    public String toString() {
        return "Link{" +
                "shortLink=" + shortLink +
                ", longLink='" + longLink + '\'' +
                '}';
    }
}
