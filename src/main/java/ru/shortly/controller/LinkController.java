package ru.shortly.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shortly.controller.schemas.Error;
import ru.shortly.controller.schemas.Link;
import ru.shortly.controller.schemas.NewLink;
import ru.shortly.controller.schemas.ShortLink;
import ru.shortly.repository.HashMapUrlRepository;


@RestController
@RequestMapping
public class LinkController {

    private final HashMapUrlRepository hashMapUrlRepository;

    public LinkController(HashMapUrlRepository hashMapUrlRepository) {
        this.hashMapUrlRepository = hashMapUrlRepository;
    }

    @PostMapping(value = "/urls", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object create(@RequestBody NewLink newLink) {
        if (newLink.getUrl().equals("")) {
            return new Error.Builder()
                    .withCode("BadRequest")
                    .withMessage("Parameter 'url' must be not blank")
                    .build();
        } else {
            return new Link.Builder()
                    .withShortLink(
                            new ShortLink.Builder()
                                    .withHost("http://localhost:80/")
                                    .withId("a5f4d9")
                                    .withUrl()
                                    .build())
                    .withLongLink(newLink.getUrl())
                    .build();
        }
    }
}
