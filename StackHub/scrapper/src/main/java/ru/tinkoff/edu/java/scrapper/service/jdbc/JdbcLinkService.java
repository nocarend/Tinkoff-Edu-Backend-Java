package ru.tinkoff.edu.java.scrapper.service.jdbc;


import java.net.URI;
import java.sql.Timestamp;
import java.util.List;
import lombok.RequiredArgsConstructor;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.repository.jdbc.JdbcLinkRepository;
import ru.tinkoff.edu.java.scrapper.service.LinkService;

@RequiredArgsConstructor
public class JdbcLinkService implements LinkService {

    private final JdbcLinkRepository repository;

    @Override
    public Link getLinkById(long id) {
        var link = repository.findById(id).orElseThrow().getUrl();
        return new Link().setUrl(link);
    }

    @Override
    public Link getLinkByUrl(URI url) {
        var linkList = repository.findByUrl(url.toString());
        if (linkList.isEmpty()) {
            return new Link().setUrl(null);
        }
        return new Link().setId(linkList.orElseThrow().getId()).setUrl(linkList.orElseThrow()
            .getUrl());
    }

    @Override
    public Link add(URI url) {
        repository.add(url.toString(), new Timestamp(System.currentTimeMillis()));
        return new Link().setUrl(url);
    }

    @Override
    public Link remove(URI url) {
        repository.remove(url.toString());
        //надо у юзеров тоже удалять
        return new Link().setUrl(url);
    }

    @Override
    public List<Link> findAll(Timestamp timeBeforeUpdates) {
        return repository.findAllLinks().stream().map(
                response -> new Link().setUrl(response.getUrl()).setId(response.getId())
                    .setUpdatedAt(response.getUpdatedAt()))
            .filter(link -> link.getUpdatedAt().before(timeBeforeUpdates)).toList();
    }

    @Override
    public void setCurrentUpdateTime(List<Long> links) {
        links.forEach(
            link -> repository.setNewUpdateTime(link,
                new Timestamp(System.currentTimeMillis())));
    }

    @Override
    public boolean contains(URI url) {
        return getLinkByUrl(url).getUrl() != null;
    }
}
