package ru.shortly.repository;

import org.junit.jupiter.api.Test;
import ru.shortly.controller.schemas.NewLink;

import static org.junit.jupiter.api.Assertions.assertEquals;


class HashMapUrlRepositoryTest {

    private final HashMapUrlRepository urls = new HashMapUrlRepository();

    @Test
    void putIsSameAsGet() {
        String newKey = urls.generateUrlPath();

        NewLink newLink = NewLink.builder().withUrl("https://ru.uefa.com/uefaeuro-2020/").build();
        urls.putLink(newKey, newLink);

        assertEquals(newLink, urls.getLink(newKey));
    }

    @Test
    void keyIsAlreadySaved() {
        String newKey = urls.generateUrlPath();
        NewLink newLink_1 = NewLink.builder().withUrl("https://ru.uefa.com/uefaeuro/").build();
        NewLink newLink_2 = NewLink.builder().withUrl("https://ru.uefa.com/").build();

        urls.putLink(newKey, newLink_1);

        try {
            urls.putLink(newKey, newLink_2);
        } catch (Exception exception) {
            final String expected = "This key is already saved";
            assertEquals(expected, exception.getMessage());
        }
    }
}
