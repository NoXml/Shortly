package ru.shortly.controller.schemas;

import java.util.Objects;

public class Error {
    private final String code;
    private final String message;

    private Error(String code, String message) {
        this.code = Objects.requireNonNull(code, "code");
        this.message = Objects.requireNonNull(message, "message");
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static class Builder {
        private String code;
        private String message;

        public Builder withCode(String code) {
            this.code = code;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Error build() {
            return new Error(code, message);
        }
    }

    @Override
    public String toString() {
        return "Error{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
