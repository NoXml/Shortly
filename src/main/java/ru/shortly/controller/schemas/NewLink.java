package ru.shortly.controller.schemas;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class NewLink {

    @NotBlank
    private final String url;

    @JsonCreator
    private NewLink(@JsonProperty("url") String url) {
        this.url = Objects.requireNonNull(url, "url");
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

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "NewLink{" +
                "url='" + url + '\'' +
                '}';
    }
}
