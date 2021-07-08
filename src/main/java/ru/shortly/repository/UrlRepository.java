package ru.shortly.repository;

import ru.shortly.controller.schemas.NewLink;

import java.util.HashMap;
import java.util.Random;

public class UrlRepository {

    public final HashMap<String, NewLink> urls;

    public UrlRepository(HashMap<String, NewLink> urls) {
        this.urls = urls;
    }


    public String generateUrlPath() {
        Random random = new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789";
        char[] charsUrlPath = new char[6];

        for (int i = 0; i <= charsUrlPath.length - 1; i++) {
            charsUrlPath[i] = alphabet.charAt(random.nextInt(alphabet.length()));
        }
        return new String(charsUrlPath);
    }

    public void putLink(NewLink newLink, String newKey) {
        if (urls.containsKey(newKey)) {
            putLink(newLink, newKey);
        } else {
            urls.put(newKey, newLink);
        }
    }

    public NewLink getLink(String key){
        if (urls.containsKey(key)) {
            return urls.get(key);
        }
        return null;
    }
}
