package ru.shortly.repository;

import ru.shortly.controller.schemas.NewLink;

import java.util.HashMap;
import java.util.Random;

public class UrlRepository {

    public final HashMap<String, NewLink> urls;
    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final int urlPathLength = 6;

    public UrlRepository() {
        this.urls = new HashMap<String, NewLink>();
    }


    public String generateUrlPath() {
        Random random = new Random();
        char[] charsUrlPath = new char[urlPathLength];

        for (int i = 0; i <= charsUrlPath.length - 1; i++) {
            charsUrlPath[i] = alphabet.charAt(random.nextInt(alphabet.length()));
        }
        return new String(charsUrlPath);
    }

    public void putLink(String newKey, NewLink newLink) {
        if (urls.containsKey(newKey)) {
            throw new IllegalArgumentException("This key is already saved");
        } else {
            urls.put(newKey, newLink);
        }
    }

    public NewLink getLink(String key) {
        return urls.get(key);
    }
}
