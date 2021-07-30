package ru.shortly.controller;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class LinkControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldProcessPostRequest() throws Exception {
        this.mvc.perform(post("/urls")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("""
                        {
                          "url": "https://ru.uefa.com/uefaeuro-2020/"
                        }
                        """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.shortLink").exists())
                .andExpect(jsonPath("$.shortLink.host").value("http://localhost:80/"))
                .andExpect(jsonPath("$.shortLink.url").value(startsWith("http://localhost:80/")))
                .andExpect(jsonPath("$.longLink").value("https://ru.uefa.com/uefaeuro-2020/"));
    }

    @Test
    void shouldPrintDefaultMessage() throws Exception {
        this.mvc.perform(post("/urls")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("""
                        {
                          "url": ""
                        }
                        """))
                .andDo(print())
                .andExpect(jsonPath("$.code").value("BadRequest"))
                .andExpect(jsonPath("$.message").value("Parameter 'url' must be not blank"));
    }

    @Test
    void shouldProcessGetRequest() throws Exception {
        this.mvc.perform(get("/{shortLinkId}", "a5f4d9"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Content from requested link")));
    }
}
