package ru.shortly.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.shortly.controller.schemas.NewLink;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UrlRepositoryTest {

    @Autowired
    private MockMvc mvc;

    UrlRepository urls = new UrlRepository();

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

    @Test
    void shouldProcessRequest() throws Exception {
        this.mvc.perform(post("/urls")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("""
                        {
                          "url": "https://ru.uefa.com/"
                        }
                        """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.url").value("https://ru.uefa.com/"))
                .andExpect(jsonPath("$.shortUrl").value("http://localhost:80/a5f4d9"));

    }
}
