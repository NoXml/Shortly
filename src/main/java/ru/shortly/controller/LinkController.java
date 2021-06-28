package ru.shortly.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shortly.controller.schemas.Error;
import ru.shortly.controller.schemas.Link;
import ru.shortly.controller.schemas.NewLink;

@RestController
@RequestMapping
public class LinkController {

    @PostMapping(value = "/urls", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object create(@RequestBody NewLink newLink) {
        if (newLink.getUrl().equals("")) {
            return new Error("BadRequest", "Parameter 'url' must be not blank");
        } else {
            return new Link(newLink.getUrl(), "http://localhost:80/a5f4d9");
        }
    }
}
