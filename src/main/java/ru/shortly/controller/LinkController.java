package ru.shortly.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.shortly.controller.schemas.Link;
import ru.shortly.controller.schemas.NewLink;
import ru.shortly.controller.schemas.ShortLink;
import ru.shortly.repository.HashMapUrlRepository;

import javax.validation.Valid;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping
public class LinkController {

    private final HashMapUrlRepository hashMapUrlRepository;

    public LinkController(HashMapUrlRepository hashMapUrlRepository) {
        this.hashMapUrlRepository = hashMapUrlRepository;
    }

    @PostMapping(value = "/urls", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object create(@Valid @RequestBody NewLink newLink) {
        String newKey = hashMapUrlRepository.generateUrlPath();
        hashMapUrlRepository.putLink(newKey, newLink);
        return new Link.Builder()
                .withShortLink(
                        new ShortLink.Builder()
                                .withHost("http://localhost:80/")
                                .withId(newKey)
                                .withUrl()
                                .build())
                .withLongLink(newLink.getUrl())
                .build();
    }

    @GetMapping("/{shortLinkId}")
    public String getContentFromRequestedLink(@PathVariable(value = "shortLinkId", required = true) String id) throws Exception {
        if (hashMapUrlRepository.getLink(id) != null) {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(hashMapUrlRepository.getLink(id).getUrl()))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } else {
            return "There is no link with this ID in the repository";
        }
    }
}
