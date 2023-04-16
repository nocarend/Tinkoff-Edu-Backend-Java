package ru.tinkoff.edu.java.scrapper.service;


import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.dto.response.LinkResponse;
import ru.tinkoff.edu.java.scrapper.repository.LinkRepository;
import ru.tinkoff.edu.java.scrapper.repository.dto.AddLinkRequest;

@Service
@RequiredArgsConstructor
public class LinkService {

    private final LinkRepository repository;


    public LinkResponse getByLinkId(long id) {
        var link = repository.findById(id);
        return new LinkResponse(link.id(), link.url());
    }

    public LinkResponse getByUrl(URI url) {
        var link = repository.findByURL(url);
        return new LinkResponse(link.id(), link.url());
    }

    public boolean contains(URI url) {
        return getByUrl(url) != null;
    }

    public void add(URI url) {
        repository.add(new AddLinkRequest(url, null));
    }
}
