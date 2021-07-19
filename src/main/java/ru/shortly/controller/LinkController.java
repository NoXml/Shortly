package ru.shortly.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shortly.controller.schemas.Error;
import ru.shortly.controller.schemas.Link;
import ru.shortly.controller.schemas.NewLink;
import ru.shortly.repository.UrlRepository;


@RestController
@RequestMapping
public class LinkController {

    private final UrlRepository urlRepository;

    public LinkController(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
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
                    .withUrl(newLink.getUrl())
                    .withShortUrl("http://localhost:80/a5f4d9")
                    .build();
        }
    }
}
