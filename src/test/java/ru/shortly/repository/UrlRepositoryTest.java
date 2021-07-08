package ru.shortly.repository;

import org.junit.jupiter.api.Test;
import ru.shortly.controller.schemas.NewLink;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

class UrlRepositoryTest {

    UrlRepository urls = new UrlRepository(new HashMap<>());

    @Test
    void checkClass() {
        String newKey = urls.generateUrlPath();
        NewLink newLink = new NewLink("https://ru.uefa.com/uefaeuro-2020/");
        urls.putLink(newLink, newKey);

        assertEquals(newLink, urls.getLink(newKey));
    }
}