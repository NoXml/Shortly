package ru.shortly.controller.schemas;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NewLink {
    private final String url;

    @JsonCreator
    public NewLink(@JsonProperty("url") String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public static class Builder {
        private String url;

        public Builder withUrl(String url) {
            this.url = url;
            return this;
        }

        public NewLink build() {
            return new NewLink(url);
        }
    }

    @Override
    public String toString() {
        return "NewLink{" +
                "url='" + url + '\'' +
                '}';
    }
}
